import org.junit.jupiter.api.BeforeEach;

import data.model.book.Book;
import data.model.library.Hold;
import data.model.member.Member;
import database.catalog.Catalog;
import database.memberOperations.MembersOperations;

public class HoldOpsTest {
	Member member =null;
	Book book=null;
	Hold hold =null;
	@BeforeEach
	void init() {
		member = MembersOperations.findMember(null);
		book = Catalog.findBook(null);
		 hold = new Hold(member,book,3);
	}

}
