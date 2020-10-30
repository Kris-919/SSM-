import com.aop.cal.Calculator;
import com.aop.cal.CalculatorProxy;
import com.aop.cal.MyCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:aop.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AopTest {

    @Autowired
    Calculator calculator;//自动赋值,等价于 Calculator calculator

    @Test
    public void test00(){
        Calculator proxy= CalculatorProxy.getProxy(new MyCalculator());
       // System.out.println(proxy.getClass());
       // System.out.println(proxy);
       // System.out.println(proxy.toString());
    }

    @Test
    public void test(){
        calculator.add(2,1);
        System.out.println(calculator);//输出 com.aop.cal.MyCalculator@974e45
        System.out.println(calculator.getClass());//输出 class com.sun.proxy.$Proxy21
    }
    @Test
    public void test01(){
        calculator.div(2,0);
    }
}
