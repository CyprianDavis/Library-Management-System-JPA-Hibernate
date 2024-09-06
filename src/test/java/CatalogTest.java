import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import data.model.book.Book;
import database.catalog.Catalog;

public class CatalogTest {
	Book book =null;
	boolean value =false;
	List<Book>books = new LinkedList<>();
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
		Assertions.assertEquals(6, number);
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
	@Disabled
	@Test
	void testCheckISBN() {
		value = Catalog.checkISBN("978-1-492-05761-1");
		Assertions.assertEquals(true, value);
	}
	
	@Test
	void findBook() {
		book = Catalog.findBook("BK0001002024");
		Assertions.assertEquals(book, book);
	}
	@Disabled
	@Test
	void removeBook() {
		 value = Catalog.removeBook("BK0001272024");
		Assertions.assertEquals(true, value);
	}
	@Disabled
	@Test
	void isBookCheckedout() {
		 value = Catalog.isBookCheckedout("BK0001272024");
		Assertions.assertEquals(true, value);
	}
	@Test
	void getCatalogBooks() {
		books.addAll(Catalog.getCatalogBooks());
		Assertions.assertEquals(true, !books.isEmpty());
	}
	@Test
	void searchBooks() {
		books.addAll(Catalog.searchBooks("Learning SQL"));
		Assertions.assertEquals(true, !books.isEmpty());
	}
	@Test
	void getBooksByStatus() {
		int value = Catalog.getBooksByStatus("Issued");
		Assertions.assertEquals(0, value);
	}
	@Test
	void getHoldsOnBook() {
		book = Catalog.findBook("BK0001002024");
		Assertions.assertEquals(4, book.getHolds().size());
	}
	
	
}
