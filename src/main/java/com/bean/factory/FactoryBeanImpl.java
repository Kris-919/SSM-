package com.bean.factory;

import com.bean.Person.Book;
import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanImpl implements FactoryBean<Book> {
    /*
    * 实现了FactoryBean接口的类是Spring可以识别的工厂类
    * Spring会自动调用工厂方法创建实例
    */

    public FactoryBeanImpl(){
        //System.out.println("HHHH");
    }

    //工厂方法,返回创建的对象
    public Book getObject() throws Exception {
        Book book=new Book();
        book.setBookName("水浒");
        return book;
    }

    //返回创建的对象的类型,Spring会自动调用这个方法来确认创建的对象是什么类型
    public Class<?> getObjectType() {
        return Book.class;
    }

    //是否是单例实例
    public boolean isSingleton() {
        return true;
    }
}
