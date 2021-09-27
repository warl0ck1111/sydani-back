package com.sydani.assessment.service;

import com.sydani.assessment.model.Book;

import java.util.List;

/**
 * @author OKala III
 * on 5/16/2021
 */

public interface BookService<E, Dto> {


    E create(Dto e);



    E update(Dto d, Long id);


    Book delete(Long id);


    List<E> findAll();

    E findById(long id);



}
