package service;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class Service {
	
	public static final DataAccess da = new DataAccessFacade();
    
	public static boolean isMember( String memberId ) {

        return Objects.isNull(da.getLibraryMemberById( memberId ) );
    }

    public static boolean isIsbnExist(String isbn ) {

        return Objects.isNull(da.getBookByIsbn(isbn ) );
    }

    public static boolean isBookAvailable(String Isbn ) {

        return Objects.isNull(da.getBookCopy( Isbn ) );
    }

	public static String findProperties(List<String> properties, String source) {
		AtomicReference<String> response = new AtomicReference<>("");
		properties.forEach(property -> {
            Matcher m = Pattern.compile(property+"=.*?(?=,|})").matcher(source);
            while (m.find())
                response.set(response.get().concat(m.group(0).replace("'","").concat("\n")));
        });
        return response.get();
	}

}
