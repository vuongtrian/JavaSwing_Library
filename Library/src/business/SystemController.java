package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.Data;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
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
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public HashMap<String, LibraryMember> allMembers() {
		DataAccess da = new DataAccessFacade();
		return da.readMemberMap();
	}

	@Override
	public HashMap<String, Book> allBooks() {
		DataAccess da = new DataAccessFacade();
		return da.readBooksMap();
	}

	@Override
	public void addNewMemberController(LibraryMember member) {
		DataAccess da = new DataAccessFacade();
		System.out.println("before save");
		da.saveNewMember(member);
		System.out.println("after save");

	}
	@Override
	public LibraryMember getLibraryMemberByIdController(String id) {
		DataAccess da = new DataAccessFacade();
		LibraryMember lm = da.getLibraryMemberById(id);
		return lm;
	}
	@Override
	public boolean updateMemberController(LibraryMember member) {
		System.out.println("entered controller");
		DataAccess da = new DataAccessFacade();
		boolean flag = da.updateMember(member);
		System.out.println(flag);
		return flag;
	}
	@Override
	public void addBookController(Book newBook) {
		DataAccess da = new DataAccessFacade();
		da.addBook(newBook);	
	}
	@Override
	public void addBookCopyController(BookCopy newBookCopy) {
		DataAccess da = new DataAccessFacade();
		da.addBookCopy(newBookCopy);
		
	}
	@Override
	public Book getBookByIsbnController(String isbn) {
		DataAccess da = new DataAccessFacade();

		return da.getBookByIsbn(isbn);
	}
	
	
	
}
