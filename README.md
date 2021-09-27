# sydani-back

##
GET http://localhost:8080/api/external-books?name= a game of thrones

{
  "status_code": 201,
  "status": "success",
  "data": {
    "id": null,
    "name": "A Game of Thrones",
    "isbn": "978-0553103540",
    "authors": [
      "George R. R. Martin"
    ],
    "number_of_pages": 0,
    "publisher": "Bantam Books",
    "country": "United States",
    "release_date": null
  }
}




##
POST http://localhost:8080/api/v1/books?name= a game of thrones

{
  "status_code": 201,
  "status": "success",
  "data": {
    "id": 1,
    "name": "A Game of Thrones",
    "isbn": "978-0553103540",
    "number_of_pages": 0,
    "publisher": "Bantam Books",
    "country": "United States",
    "released": "1996-08-01T00:00:00"
  }
}


##
GET http://localhost:8080/api/v1/books

{
  "status_code": 200,
  "status": "success",
  "data": [
    {
      "id": 1,
      "name": "A Game of Thrones",
      "isbn": "978-0553103540",
      "number_of_pages": 0,
      "publisher": "Bantam Books",
      "country": "United States",
      "released": "1996-08-01T00:00:00"
    }
  ]
}


##
PATCH http://localhost:8080/api/v1/books/1?name=a pain in arse

{
  "status_code": 200,
  "status": "success",
  "message": "The book a pain in arse was updated successfully",
  "data": {
    "id": 1,
    "name": "a pain in arse",
    "isbn": null,
    "number_of_pages": 0,
    "publisher": null,
    "country": null,
    "released": null
  }
}         


##
DELETE http://localhost:8080/api/v1/books/3

{
  "status_code": 200,
  "status": "success",
  "message": "The book A Game of Thrones was updated successfully",
  "data": []
}

