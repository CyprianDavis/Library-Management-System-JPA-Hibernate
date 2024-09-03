import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import data.model.member.Member;
import database.memberOperations.MembersOperations;

public class MemberOpsTest {
	Member member;
	List<Member>members = new LinkedList<>();
	@Disabled
	@BeforeEach
	void init() {
		Member member = new Member("SSEREMBA","CYPRIAN","DAVIS");
		member.setContact("256 757 790730");
		member.setAddress("BBUNGA");
		
	}
	@Disabled
	@Test
	void testgetNextId() {
		int id = MembersOperations.getNextTableGeneratorValue();
		Assertions.assertEquals(2, id);
	}
	@Disabled
	@Test
	void testViewMembers() {
		members.addAll(MembersOperations.viewMembers());
		Assertions.assertEquals(5,members.size());
	}
	@Disabled
	@Test
	void testFindMember() {
		Member member = MembersOperations.findMember("LM00001202");
		Assertions.assertEquals(null, member);
	}
	@Disabled
	@Test
	void testMemberExists() {
		boolean value = MembersOperations.memberExists("LM000052024");
		Assertions.assertEquals(true, value);
	}
	@Disabled
	@Test
	void testGetNumberOfMember() {
		int num = MembersOperations.totalNumberOfMembers();
		Assertions.assertEquals(5, num);
	}
	@Test
	void testMemberIssuedBks() {
		Member member = MembersOperations.findMember("LM000012024");
		Assertions.assertEquals(0, member.getIssuedBooks().size());	
	}
	@Test
	void testMemberHolds() {
		Member member = MembersOperations.findMember("LM000012024");
		Assertions.assertEquals(10, member.getHolds().size());	
		
	}

}
