package com.redeemerlives.booksocialnetwork.book;

import com.redeemerlives.booksocialnetwork.history.BookTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book toBook(BookRequest request) {
        return Book.builder()
                .id(request.id())
                .title(request.title())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .isbn(request.isbn())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .isbn(book.getIsbn())
                .owner(book.getOwner().getFullName())
                .synopsis(book.getSynopsis())
                .rate(book.getRating())
                // todo implement this later
                // .cover()
                .build();
    }

    public BorrowedBooksResponse toBorrowedBooksResponse(BookTransactionHistory history) {
        return BorrowedBooksResponse.builder()
                .id(history.getBook().getId())
                .title(history.getBook().getTitle())
                .authorName(history.getBook().getAuthorName())
                .isbn(history.getBook().getIsbn())
                .rate(history.getBook().getRating())
                .returned(history.isReturned())
                .returnedApproved(history.isReturnApproved())
                .build();
    }
}
