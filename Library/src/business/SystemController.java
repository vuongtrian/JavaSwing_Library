package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.Data;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import service.Service;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	private static DataAccess da = new DataAccessFacade();
	public void login(String id, String password) throws LoginException {
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public HashMap<String, LibraryMember> allMembers() {
		return da.readMemberMap();
	}

	@Override
	public HashMap<String, Book> allBooks() {
		return da.readBooksMap();
	}

	@Override
	public void addNewMemberController(LibraryMember member) {
		System.out.println("before save");
		da.saveNewMember(member);
		System.out.println("after save");

	}
	@Override
	public LibraryMember getLibraryMemberByIdController(String id) {
		LibraryMember lm = da.getLibraryMemberById(id);
		return lm;
	}
	@Override
	public boolean updateMemberController(LibraryMember member) {
		System.out.println("entered controller");
		boolean flag = da.updateMember(member);
		System.out.println(flag);
		return flag;
	}

	@Override
	public void addBookController(Book newBook) {
		da.addBook(newBook);	
	}
	@Override
	public void addBookCopyController(String isbn) {
		da.addBookCopy(isbn);
		
	}
	@Override
	public Book getBookByIsbnController(String isbn) {
		return da.getBookByIsbn(isbn);
	}
	
	@Override
	public String makeCheckout(String memberId, String isbn) {
		String msg = null;
		if(Service.isMember( memberId ) ) {
            msg = memberId + " is  not yet a member \n";
        }
        else if(Service.isIsbnExist( isbn ) ) {
        	msg = "There is no book match this ISBN:"+ isbn +" ! \n";
        }
        else if(Service.isBookAvailable( isbn ) ) {
        	msg = "No available copy at the moment for  Isbn " + isbn + "\n";
        }
        else {
           // System.out.println(da.createCheckoutRecord( isbn, memberId ));
            msg = "Checkout made successfully ! \n";
            msg+=da.createCheckoutRecord( isbn, memberId );
        }
		return msg;
	}
	public List<CheckoutRecord> findMemberCheckoutRecord(String memberId){
		return da.getLibraryMemberById(memberId).getRecords();
	}
	public String overDueCheckout(String isbn) {
		return da.getBookCopiesWithCheckoutRecord(isbn);
	}
	
	
}
