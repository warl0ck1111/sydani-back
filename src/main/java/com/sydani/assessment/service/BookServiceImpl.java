package com.sydani.assessment.service;


import com.sydani.assessment.dto.BookDto;
import com.sydani.assessment.model.Book;
import com.sydani.assessment.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService<Book, BookDto> {

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @Transactional
    @Override
    public Book create(BookDto dto) {
        book = new Book();
        BeanUtils.copyProperties(dto, book);
        return bookRepository.save(book);
    }


    @Transactional
    @Override
    public Book update(BookDto dto, Long id) {
        book = findById(id);
        BeanUtils.copyProperties(dto, book);
        return bookRepository.save(book);
    }


    @Transactional
    @Override
    public Book delete(Long id) {
        Book book = findById(id);
        bookRepository.delete(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    @Transactional
    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElseThrow(()->new NoSuchElementException("invalid book id"));
    }
}
