package com.sydani.assessment.controller;

import com.google.gson.Gson;
import com.sydani.assessment.dto.BookDto;
import com.sydani.assessment.response.ApiResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
public class ExternalBookController{

    @GetMapping(path = "api/external-books")
    public ResponseEntity<?> findBookByName(@RequestParam(name = "name") String nameOfBook) {
        RestTemplate restTemplate = new RestTemplate();
        String externalApiUrl = "https://www.anapioficeandfire.com/api/books?name=" + nameOfBook;

        Gson gson = new Gson();


        // send request and parse result
//        HttpEntity<String> entity = new HttpEntity<>(input, headers);
        ResponseEntity<String> response = restTemplate.exchange(externalApiUrl, HttpMethod.GET, null, String.class);

        System.out.println(response);
        String json = response.getBody().replace("jsonp (", "").replace(")", "").replace(",\"responseParams\":{}", "").trim();
        BookDto[] bookDtos = gson.fromJson(json, BookDto[].class);


        return new ResponseEntity<>(new ApiResponse(201, "success", bookDtos[0]), HttpStatus.CREATED);
    }

}
