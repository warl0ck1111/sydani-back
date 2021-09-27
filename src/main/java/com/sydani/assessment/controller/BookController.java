package com.sydani.assessment.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sydani.assessment.dto.BookDto;
import com.sydani.assessment.model.Book;
import com.sydani.assessment.response.ApiResponse;
import com.sydani.assessment.response.ApiUpdateResponse;
import com.sydani.assessment.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;



    @PostMapping()
    public ResponseEntity<?> createBooks(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "isbn", required = false) String isbn, @RequestParam(name = "authors", required = false) String authors, @RequestParam(name = "country", required = false) String country, @RequestParam(name = "number_of_pages", required = false) Integer noOfPages, @RequestParam(name = "publisher", required = false) String publisher, @RequestParam(name = "release_date", required = false) String releaseDate) {
        RestTemplate restTemplate = new RestTemplate();
        String externalApiUrl = String.format("https://www.anapioficeandfire.com/api/books?name=%s&isbn=%s&authors=%s&country=%s&number_of_pages=%s&publisher=%s&release_date=%s", name, isbn, authors, country, noOfPages, publisher, releaseDate);

        Gson gson = new Gson();


        // send request and parse result
//        HttpEntity<String> entity = new HttpEntity<>(input, headers);
        ResponseEntity<String> response = restTemplate.exchange(externalApiUrl, HttpMethod.GET, null, String.class);

        System.out.println(response);
        String json = response.getBody().replace("jsonp (", "").replace(")", "").replace(",\"responseParams\":{}", "").trim();
        BookDto[] bookDtos = gson.fromJson(json, BookDto[].class);


        return new ResponseEntity<>(new ApiResponse(201, "success", bookService.create(bookDtos[0])), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getBooks() {
        return new ResponseEntity<>(new ApiResponse(200,"success", bookService.findAll()), HttpStatus.OK);
    }

    @PatchMapping(path = {"/{id}", })
    public ResponseEntity<?> updateBooks(@PathVariable(name = "id") Long id,@RequestParam(name = "name", required = false) String name, @RequestParam(name = "isbn", required = false) String isbn, @RequestParam(name = "authors", required = false) String authors, @RequestParam(name = "country", required = false) String country, @RequestParam(name = "number_of_pages", required = false) Integer noOfPages, @RequestParam(name = "publisher", required = false) String publisher, @RequestParam(name = "release_date", required = false) String releaseDate) {
        List<String> authorsList = new ArrayList<>();
        authorsList.add(authors);

        BookDto bookDto = new BookDto();
//        (name,isbn,authorsList,noOfPages,publisher,country,releaseDate);
if (name != null){
    bookDto.setName(name);
}

        if (isbn != null){
            bookDto.setIsbn(isbn);
        }
        if (noOfPages != null){
            bookDto.setNumber_of_pages(noOfPages);
        }
        if (publisher != null){
            bookDto.setPublisher(publisher);
        }
        if (country != null){
            bookDto.setCountry(country);
        }
        if (releaseDate != null){
            bookDto.setReleased(releaseDate);
        }
        Book updatedBook = bookService.update(bookDto, id);

        return new ResponseEntity<>(new ApiUpdateResponse(200, "success", String.format("The book %s was updated successfully", name),updatedBook.getName() ), HttpStatus.OK);
    }

 @PostMapping(path = "/{id}/update")
    public ResponseEntity<?> updateBooks2(@PathVariable(name = "id") Long id,@RequestParam(name = "name", required = false) String name, @RequestParam(name = "isbn", required = false) String isbn, @RequestParam(name = "authors", required = false) String authors, @RequestParam(name = "country", required = false) String country, @RequestParam(name = "number_of_pages", required = false) int noOfPages, @RequestParam(name = "publisher", required = false) String publisher, @RequestParam(name = "release_date", required = false) String releaseDate) {
     List<String> authorsList = new ArrayList<>();
     authorsList.add(authors);
     BookDto bookDto = new BookDto(name,isbn,authorsList,noOfPages,publisher,country,releaseDate);

     Book updatedBook = bookService.update(bookDto, id);

     return new ResponseEntity<>(new ApiUpdateResponse(200, "success", String.format("The book %s was updated successfully", name),updatedBook.getName() ), HttpStatus.OK);
 }


    @DeleteMapping(path = "/{id}" )
    public ResponseEntity<?> deleteBooks(@PathVariable(name = "id") Long id) {
        Book deletedBook = bookService.delete(id);

        return new ResponseEntity<>(new ApiUpdateResponse(200, "success", String.format("The book %s was deleted successfully", deletedBook.getName()),new ArrayList<>()), HttpStatus.OK);
    }

 @PostMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteBooks2(@PathVariable(name = "id") Long id) {
     Book deletedBook = bookService.delete(id);

     return new ResponseEntity<>(new ApiUpdateResponse(200, "success", String.format("The book %s was deleted successfully", deletedBook.getName()),new ArrayList<>() ), HttpStatus.OK);
    }


}
