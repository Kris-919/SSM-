package com.bean.factory;

import com.bean.Person.Book;

/**
* 静态工厂
*/
public class BookStaticFactory {
    public BookStaticFactory(){
        //System.out.println("YES");
    }
    public static Book createBook(String bookName){
        Book book=new Book();
        book.setBookName(bookName);
        return book;
    }
}
