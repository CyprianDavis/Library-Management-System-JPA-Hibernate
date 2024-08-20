import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import data.model.book.Book;
import database.catalog.Catalog;

public class CatalogTest {
	Book book =null;
	@Disabled
	@BeforeEach
	void init() {
		 book = new Book("JAVA PERSISTENCE WITH HIBERNATE","Christian Bauer","Avaliable");
		book.setCoAuthor("Gavin King");
		book.setISNB("9781617290459");
		book.setPublicationYear(2016);
		book.setPublisher("Manning Publications Co.");
		book.setEdition("SECOND");
		book.setLanguage("English");
		book.setDescription("Java Persistence with Hibernate");
		book.setCategory("Software Development");
	
	
	}
	@Disabled
	@Test
	void testtotalNumberOfBooks() {
		int number = Catalog.totalNumberOfBooks();
		Assertions.assertEquals(16, number);
	}
	@Disabled
	@Test
	void testIdGen() {
		int num = Catalog.getNextTableGeneratorValue();
		Assertions.assertEquals(116, num);
	}
	@Disabled
	@Test
	void testInsetBook() {
		Book bk = Catalog.insertBook(book);
		Assertions.assertEquals(book, bk);
		
		
		
	}
}
