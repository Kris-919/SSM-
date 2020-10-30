package com.bean.factory;

import com.bean.Person.Book;

public class BookInstanceFactory {
    public BookInstanceFactory(){
        //System.out.println("OK");
    }
    public Book createBook(String bookName){
        //System.out.println("book被创建了");
        Book book=new Book();
        book.setBookName(bookName);
        return book;
    }
}
