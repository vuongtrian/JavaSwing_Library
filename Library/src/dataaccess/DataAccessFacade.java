package dataaccess;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import business.Book;
import business.BookCopy;
import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;
import service.Service;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS, CHECKOUT_RECORD;
	}
	
//	public static final String OUTPUT_DIR = System.getProperty("user.dir")
//			+ "/Library/src/dataaccess/storage";

	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "\\src\\dataaccess\\storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	//implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
		System.out.println(mems);

	}
	
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(
				StorageType.MEMBERS);
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}


	@Override
	public LibraryMember getLibraryMemberById(String id) {
		HashMap<String, LibraryMember> members = readMemberMap();
		try {
			if (members.get(id) != null)
				return members.get(id);
			else
				throw new Exception("User doesn't exist");
		} catch (Exception e) {
			System.out.println("User doesn't exist");
		}
		return null;
	}

	@Override
	public boolean updateMember(LibraryMember member) {
		HashMap<String, LibraryMember> members = readMemberMap();
		LibraryMember oldMember = members.get(member.getMemberId());
		if (oldMember != null) {
			System.out.println("entered ");
			members.replace(member.getMemberId(), oldMember, member);
			saveToStorage(StorageType.MEMBERS, members);
			return true;
		}
		return false;
	}

	@Override
	public void addBook(Book newBook) {
		HashMap<String, Book> books = readBooksMap();
		books.put(newBook.getIsbn(), newBook);
		saveToStorage(StorageType.BOOKS, books);
	}

	
	@Override
	public Book getBookByIsbn(String isbn) {
		HashMap<String, Book> books = readBooksMap();
		if (books.get(isbn) != null)
			return books.get(isbn);
		return null;
	}
	
	@Override
	public void addBookCopy(String isbn) {
		HashMap<String, Book> books = readBooksMap();
		try {
			books.get(isbn).addCopy();
			saveToStorage(StorageType.BOOKS, books);
		} catch (Exception e) {
			System.out.println("something wrong with addBookCopy!");
		}
	}
	
	public String createCheckoutRecord(String isbn, String memberId) {       
		BookCopy bookCopy = getBookCopy(isbn);
		bookCopy.changeAvailability();
        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(bookCopy);
        LibraryMember member = getLibraryMember( memberId );
        CheckoutRecord checkoutRecord = new CheckoutRecord( memberId );
        checkoutRecord.getCheckoutRecordEntries().add(checkoutRecordEntry);
        addNewCheckoutRecord( checkoutRecord );
        updateBookCopyAvailability( bookCopy.getCopyNum() );
        member.getRecords().addAll( Collections.singletonList( checkoutRecord ) );
        updateMember( member );
        //System.out.println(checkoutRecord);
        
        return Service.findProperties(Arrays.asList("checkoutId", "memberId", "bookCopyId", "checkoutDate", "dueDate" ),
                checkoutRecord.toString() );
	}
	
	@SuppressWarnings("unchecked")
	public BookCopy getBookCopy( String isbn ) {
		HashMap<String, Book> books = ( HashMap<String, Book> )readFromStorage( StorageType.BOOKS );
        for ( Book b : books.values() ) {
            if ( b.getIsbn() != null && b.getIsbn().equals( isbn.trim() ) ) {
                for ( BookCopy bc : b.getCopies() ) {
                    if ( bc.isAvailable() ) {
                        return bc;
                    }
                }
            }
        }
        return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public LibraryMember getLibraryMember( String LibraryMemberId ) {
		HashMap<String, LibraryMember> members = ( HashMap<String, LibraryMember> )readFromStorage(StorageType.MEMBERS );
        if ( members.containsKey( LibraryMemberId ) ) {
            return members.get( LibraryMemberId );
        }else {
        	System.out.println(LibraryMemberId + "doesn't exit");
        	return null;
        }
	}
	

	public void addNewCheckoutRecord(CheckoutRecord record){
		try {
			if (!Files.exists( Paths.get(DataAccessFacade.OUTPUT_DIR+ File.separator+StorageType.CHECKOUT_RECORD.toString()))) {
	            Files.createFile(Paths.get(DataAccessFacade.OUTPUT_DIR+ File.separator+StorageType.CHECKOUT_RECORD.toString()));
	        }
		}catch(IOException ioe) {
			System.err.println(ioe);
		}
		
		HashMap<String, CheckoutRecord> checkoutRecord = Optional.ofNullable(readCheckoutRecordMap()).orElseGet(()-> new HashMap<String, CheckoutRecord>());
		checkoutRecord.put(record.getCheckoutId(), record);
		saveToStorage(StorageType.CHECKOUT_RECORD, checkoutRecord);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		return (HashMap<String, CheckoutRecord>)readFromStorage(StorageType.CHECKOUT_RECORD);
	}
	
	@SuppressWarnings("unchecked")
	public boolean updateBookCopyAvailability( int bookCopyId ) {	
		HashMap<String, Book> books = ( HashMap<String, Book> )readFromStorage( StorageType.BOOKS );
        for ( Book b : books.values() ) {
            for ( BookCopy bc : b.getCopies()) {
                if (bc.getCopyNum() == bookCopyId) {
                    //bc.changeAvailability();
                    bc.setAvailable(false);
                    books.replace(b.getIsbn(), b);
                    saveToStorage( StorageType.BOOKS, books );
                    return true;
                }
            }
        }
        return false;
	}

	
	public String getBookCopiesWithCheckoutRecord( String isbn ) {
		StringBuilder responseBuilder = new StringBuilder();
        if ( Service.isIsbnExist( isbn ) ) {
            //does not exist
            return null;
        }
        else {
            Book foundBook = getBookByIsbn( isbn );
            responseBuilder
                    .append( "book Isbn = " + foundBook.getIsbn() + "\n" )
                    .append( "book Title = " + foundBook.getTitle() + "\n" )
                    .append( "Total Copies = " + foundBook.getCopies().length + "\n" );

            List<BookCopy> bookCopies = Arrays.asList(foundBook.getCopies());
            bookCopies.forEach( bookCopy -> {
                HashMap<String, String> bookCopyCheckoutRecord = getBookCopyCheckoutRecord(bookCopy );
                if ( bookCopyCheckoutRecord.isEmpty() ) {
                    responseBuilder
                            .append( "CopyId =" + bookCopy.getCopyNum() + "\n" )
                            .append( "CheckoutBy = Available \n" )
                            .append( "Due Date   =  Available\n" );
                }
                else {
                    bookCopyCheckoutRecord.forEach( ( memberName, dueDate ) -> {
                        responseBuilder
                                .append( "CopyId =" + bookCopy.getCopyNum() + "\n" )
                                .append( "CheckoutBy =" + memberName + "\n" )
                                .append( "Due Date =" + dueDate + "\n" );
                    } );
                }
            } );
            return responseBuilder.toString();
        }
	}
	
	
	@Override
    public HashMap<String, String> getBookCopyCheckoutRecord( BookCopy bookCopy ) {
        HashMap<String, String> foundBookCopyCheckoutRecord = new HashMap<>();
        HashMap<String, CheckoutRecord> checkoutRecordMap = readCheckoutRecordMap();
        //System.out.println(checkoutRecordMap);
        checkoutRecordMap.forEach( ( key, record ) -> record.getCheckoutRecordEntries().forEach( entry -> {
            if ( entry.getBookCopy().getCopyNum() == bookCopy.getCopyNum()) {
                LibraryMember member = getLibraryMember( record.getMemberId() );
                foundBookCopyCheckoutRecord.put(member.getFirstName()
                		.concat(" ")
                		.concat(member.getLastName()), entry.getDueDate().toString());
            }
        }));
       // System.out.println(checkoutRecordMap);

        return foundBookCopyCheckoutRecord;
    }
	
}
