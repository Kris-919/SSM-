
import com.bean.Person.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/*使用Spring的单元测试步骤
* (1)导入Spring的test包
* (2)使用注解 @ContextConfiguration(locations="") 来指定Spring的配置文件的位置
* (3)使用注解 @RunWith 来指定用哪种驱动进行单元测试,默认为junit
* @RunWith(SpringJUnit4ClassRunner.class)表示使用Spring的单元测试模块来执行标了@Test注解的测试方法
* 好处:不用利用bean.getBean方法来获取对象
*/
@ContextConfiguration(locations = "classpath:bean.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BeanTest {
    //ApplicationContext为ioc容器类,可以创造ioc容器,ioc(控制反转)
    //ClassPathXmlApplicationContext: 当前应用的配置文件路径
    //根据Spring的配置文件可以获得ioc容器对象
    //容器中的对象创建在创建容器时就创建了,每个对象只会被创建一次(可以通过在构造方法中打印来查看)
    private ApplicationContext bean=new ClassPathXmlApplicationContext("bean.xml");
    private ConfigurableApplicationContext con=new ClassPathXmlApplicationContext("connection.xml");


    @Qualifier("book01")
    @Autowired
    Book book;

    @Test
    public void test01() {
        Person person=(Person)bean.getBean("person06");//若容器中没有person01,则会报错
        //Person person1=bean.getBean(Person.class);用类型找对象时只在容器中只有一个该对象时有效,否则会报错
        //Person person1=bean.getBean("person01",Person.class);指定类型同时指定id
        System.out.println(person.toString());
        List<Integer>list=(List<Integer>)bean.getBean("myList");
        for(int i=0;i<list.size();i++)
            System.out.println(list.get(i));
    }

    @Test
    public void test02(){
        //创建book01这个对象时,BookInstanceFactory中的构造方法也被调用了,说明BookInstanceFactory的实例也被创建了,
        // 即创建容器时所有的对象都会被创建好,除了实现了FactoryBean接口的工厂类中要创建的实例不会被实现,其它对象都会被创建
        Book book=(Book)bean.getBean("book01");
        System.out.println(book.toString());
    }


    /*静态,实例和实现了FactoryBean接口的工厂类创建实例的区别(单实例)
    * 静态工厂: ioc容器创建时只会创建该工厂中需要创建的对象,而不会创建该工厂本身的对象
    * 实例工厂: ioc容器创建时会创建该工厂本身的实例对象,同时还会创建该工厂中需要创建的对象
    * 实现了FactoryBean接口的工厂: ioc容器创建时只会创建该工厂本身的实例对象,该工厂中需要创建的对象要等到调用时才会被创建
    * 多实例时,三种工厂中需要创建的实例均在调用的时候才创建
    */


    @Test
    public void test03(){
        Object book=bean.getBean("factoryBean");
        System.out.println(book);
    }

    @Test
    public void test04() throws SQLException {
        DataSource dataSource=con.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void test05(){
        System.out.println(book.toString());
    }

}
