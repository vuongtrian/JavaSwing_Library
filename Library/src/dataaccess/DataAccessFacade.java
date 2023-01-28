package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BookCopy;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS;
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
}
