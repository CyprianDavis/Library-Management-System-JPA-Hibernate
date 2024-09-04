import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import data.model.book.Book;
import data.model.member.Member;
import database.catalog.Catalog;
import database.library.LibraryOperations;
import database.memberOperations.MembersOperations;

public class LibraryOpsTest {
	Member member =null;
	Book book=null;
	@Disabled
	@BeforeEach
	void intial() {
		book =  Catalog.findBook("BK0001012024");
		member = MembersOperations.findMember("LM000022024");
	}
	@Test
	void testIssueBook() {
		String date =LibraryOperations.issueBook(member, book);
		System.out.print(date);
		
	}
	
}
