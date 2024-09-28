import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import data.model.book.Book;
import data.model.library.Hold;
import data.model.member.Member;
import database.catalog.Catalog;
import database.holdProcesses.HoldProcesses;
import database.memberOperations.MembersOperations;

public class HoldOpsTest {
	Member member =null;
	Book book=null;
	Hold hold =null;
	
	@BeforeEach
	void init() {
		member = MembersOperations.findMember("LM000022024");
		book = Catalog.findBook("BK00032024");
		 hold = new Hold(member,book,3);
	}
	@Disabled
	@Test
	void testHoldgetNextId() {
		int value=HoldProcesses.getNextTableGeneratorValue();
		Assertions.assertEquals(1, value);
	}
	@Disabled
	@Test
	void testBookHasHold() {
		boolean value = HoldProcesses.bookHasHold(book);
		Assertions.assertEquals(true, value);
	}
	@Disabled 
	@Test
	void testHoldExists() {
		boolean value = HoldProcesses.holdExists(book, member);
		Assertions.assertEquals(true, value);
	}
	@Disabled
	@Test
	void testPlaceHold() {
	 Hold h = HoldProcesses.placeHold(hold);
		Assertions.assertEquals(h, hold);
	}
	
}
