import com.transaction.BookDao;
import com.transaction.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:tx.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TxTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookDao bookDao;

    @Test
    public void test(){
        bookService.checkout("Tom","book1");
    }
    @Test
    public void test1(){
        System.out.println(bookDao);
    }
}
