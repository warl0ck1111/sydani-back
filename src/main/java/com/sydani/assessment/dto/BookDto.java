package com.sydani.assessment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String name;
    private String isbn;


    private List<String> authors;
    private int number_of_pages;
    private String publisher;
    private String country;

    private String released;


}
