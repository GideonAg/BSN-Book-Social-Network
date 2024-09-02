package com.redeemerlives.booksocialnetwork.book;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedBooksResponse {

    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private float rate;
    private boolean returned;
    private boolean returnedApproved;
}
