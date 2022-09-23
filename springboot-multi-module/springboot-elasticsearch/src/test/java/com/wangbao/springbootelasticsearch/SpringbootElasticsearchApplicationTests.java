package com.wangbao.springbootelasticsearch;

import com.wangbao.springbootelasticsearch.pojo.Book;
import com.wangbao.springbootelasticsearch.service.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootElasticsearchApplicationTests {
    @Autowired
    private BookRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSave() {
        Book book = new Book();
        book.setId(1L);
        book.setBookName("西游记");
        book.setAuthor("吴承恩");
        repository.save(book);
        Book newbook=repository.findById(1).orElse(null);
        System.out.println(newbook);
    }

}
