package com.redeemerlives.booksocialnetwork.book;

import com.redeemerlives.booksocialnetwork.common.BaseEntity;
import com.redeemerlives.booksocialnetwork.feedback.Feedback;
import com.redeemerlives.booksocialnetwork.history.BookTransactionHistory;
import com.redeemerlives.booksocialnetwork.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

}
