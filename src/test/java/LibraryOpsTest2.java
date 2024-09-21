import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import data.model.book.Book;
import data.model.member.Member;
import database.catalog.Catalog;
import database.library.LibraryOperations;
import database.memberOperations.MembersOperations;
import database.transactions.TransactionsOps;

public class LibraryOpsTest2 {
	Member member =null;
	Book book=null;
	
	@BeforeEach
	void intial() {
		book =  Catalog.findBook("BK00012024");
		member = MembersOperations.findMember("LM000012024");
	}
	
	@Test
	void testIssueBook() {
		String date =LibraryOperations.issueBook(member, book);
		System.out.print(date);	
	}
	@Disabled
	@Test
	void testGetRemainingDays() {
		int days = LibraryOperations.getDaysRemaining(book);
		Assertions.assertEquals(21, days);
	}
	@Disabled
	@Test
	void testrenewBook() {
		String value = LibraryOperations.renewBook(book,member);
		Assertions.assertEquals(true, value);	
	}
	@Disabled
	@Test
	void testReturnBook() {
		int value = LibraryOperations.returnBook(book, member);
		Assertions.assertEquals(1,value);
	}
	@Disabled
	@Test
	void testTransactionNextId() {
		Assertions.assertEquals(9,TransactionsOps.getNextTableGeneratorValue());
		
	}

	
}
