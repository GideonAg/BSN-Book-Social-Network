package com.redeemerlives.booksocialnetwork.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("""
            SELECT book
            FROM Book book
            WHERE book.archived = false
            AND book.shareable = true
            AND book.owner.id != :userId
            """)
    Page<Book> findAllDisplayableBooks(Pageable pageable, Integer userId);

    @Query("""
            SELECT book
            FROM Book book
            WHERE book.owner.id = :userId
            """)
    Page<Book> findAllBooksByOwner(Pageable pageable, Integer userId);
}
