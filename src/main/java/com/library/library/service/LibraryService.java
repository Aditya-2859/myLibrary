package com.library.library.service;

import com.library.library.entity.AllocateBook;
import com.library.library.entity.Book;
import com.library.library.entity.Borrower;
import com.library.library.exception.BadBookRequestException;
import com.library.library.exception.BookNotFoundException;
import com.library.library.repository.AllocatedBookRepository;
import com.library.library.repository.BookRepository;
import com.library.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    AllocatedBookRepository allocatedBookRepository;

    public Book registerBook(Book book) {
        String isbn = book.getIsbn();
        List<Book> books = bookRepository.findByIsbn(isbn);
        if (!books.isEmpty()) {
            boolean allMatch = books.stream().allMatch(book1 -> book1.getTitle().equalsIgnoreCase(book.getTitle())
                    && book1.getAuthor().equalsIgnoreCase(book.getAuthor()));
            if(!allMatch) {
                throw new BadBookRequestException("Books with same ISBN must have same title and author "+
                        "user is trying to add a book of same ISBN with diff title or author");
            }
        }
        return bookRepository.save(book);
    }

    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public List<Book> getAllBooksInLibrary() {
        return bookRepository.findAll();
    }

    public AllocateBook allocateBook(AllocateBook allocateBook) {
        Optional<Book> bookById = bookRepository.findById(allocateBook.getBookid());
        if (bookById.isEmpty()) {
            throw new BookNotFoundException("Book is not present in library system");
        }
        Optional<AllocateBook> bookFound = allocatedBookRepository.findById(allocateBook.getBookid());
        if (bookFound.isPresent()) {
            throw new BadBookRequestException("Book with id "+allocateBook.getBookid()+" already allocated to " +
                    "other borrower");
        }
        return allocatedBookRepository.save(allocateBook);
    }

    public void returnBook(int bookId) {
        if (bookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException("Book does not belong to this library");
        }
        if (allocatedBookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException("Book is not taken by any borrower");
        }
        allocatedBookRepository.deleteById(bookId);
    }
}
