import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import data.model.member.Member;
import database.memberOperations.MembersOperations;

public class MemberOpsTest {
	List<Member>members = new LinkedList<>();
	@Test
	void testgetNextId() {
		int id = MembersOperations.getNextTableGeneratorValue();
		Assertions.assertEquals(2, id);
	}
	

}
