package com.admin.dozer;

import com.adminportal.config.MappingConfig;
import com.domain.domain.Book;
import com.domain.dto.book.BookDetailLite;
import org.dozer.Mapper;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MappingConfig.class})
public class DozerTest {

    @Autowired
    private Mapper mapper;

    @Test
    public void testDozerMapping() throws Exception {

        Book book = new Book();
        book.setTitle("John Smith");
        book.setId(25L);

        BookDetailLite p1Dto = mapper.map(book, BookDetailLite.class, "bookDetailLiteId");

        Assert.assertEquals(book.getTitle(), p1Dto.getTitle());
        Assert.assertEquals(book.getId(), p1Dto.getId());
    }
}
