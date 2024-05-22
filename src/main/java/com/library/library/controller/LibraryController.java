package com.library.library.controller;

import com.library.library.entity.AllocateBook;
import com.library.library.entity.Book;
import com.library.library.entity.Borrower;
import com.library.library.response.ApiResponse;
import com.library.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @PostMapping("/addBook")
    public ResponseEntity<ApiResponse<Book>> registerBook(@RequestBody Book book) {
        Book addedBook = libraryService.registerBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(new Date(),
                "success", "Books added successfully", addedBook));
    }

    @PostMapping("/addBorrower")
    public ResponseEntity<ApiResponse<Borrower>> registerBorrower(@RequestBody Borrower borrower) {
        Borrower newBorrower =  libraryService.registerBorrower(borrower);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(new Date(),
                "success", "Borrower added successfully", newBorrower));
    }

    @PostMapping("/allocateBook")
    public ResponseEntity<ApiResponse<AllocateBook>>  allocateBook(@RequestBody AllocateBook allocateBook) {
        AllocateBook bookAllocated = libraryService.allocateBook(allocateBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(new Date(),
                "success", "Book allocated successfully", bookAllocated));
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        List<Book> allBooks = libraryService.getAllBooksInLibrary();
        return ResponseEntity.ok(new ApiResponse<>(new Date(), "success", "Books retrived" +
                "successfully", allBooks));
    }

    @DeleteMapping("/returnBook/{bookId}")
    public ResponseEntity<ApiResponse<Integer>> returnBook(@PathVariable int bookId) {
        libraryService.returnBook(bookId);
        return ResponseEntity.ok(new ApiResponse<>(new Date(), "success", "Books returned " +
                "successfully", bookId));
    }

}
