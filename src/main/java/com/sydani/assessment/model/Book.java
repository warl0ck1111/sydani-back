package com.sydani.assessment.model;


import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
@DynamicUpdate
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String isbn;
//    private Object authors;
    private int number_of_pages;
    private String publisher;
    private String country;

    private String released;
}
