package business;

import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.DataAccess;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public HashMap<String, LibraryMember> allMembers();
	public HashMap<String, Book> allBooks();
	public void addNewMemberController(LibraryMember member);
	public LibraryMember getLibraryMemberByIdController(String id);
	public boolean updateMemberController(LibraryMember member);
	public void addBookController(Book newBook);
	public Book getBookByIsbnController(String isbn);
	public void addBookCopyController(String isbn);

	
}
