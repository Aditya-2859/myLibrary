This is simple Library system.
Available endpoints are :
1. Register a new borrower to the library.
2. Register a new book to the library.
3. Get a list of all books in the library.
4. Borrow a book with a particular book id (refer Book in Data Models).
5. Return a borrowed book

Tables used - 
Book (bookId, title, author)
Borrower (borrowerId, name, email)
AllocateBook (bookId, borrowerId).

Envirnoments configured: local, dev and prod.
How to run on diff envirnoment ? use spring.profile.active = dev // for dev envirnoment or spring.profile.active = prod for prod envirnoment in application.properties file. If it doesn't work set the afrgument variable in program arguments.

Valudations and contraints:

1.Borrower should have a unique id, a name and an email address
2. Book should have a unique id, an ISBN number, a title, and an author
3.ISBN number uniquely identifies a book in the following way:
  - 2 books with the same title and same author but different ISBN numbers are considered as different books
  - 2 books with the same ISBN numbers must have the same title and same author
4.Multiple copies of books with same ISBN number are allowed in the system
5.Multiple books with the same ISBN number should be registered as books with different ids.
6. Ensure that no more than one member is borrowing the same book (same book id) at a time.
