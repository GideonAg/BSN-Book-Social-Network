package com.redeemerlives.booksocialnetwork.history;

import com.redeemerlives.booksocialnetwork.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class BookTransactionHistory extends BaseEntity {

    // user relationship
    // book relationship

    private boolean returned;
    private boolean returnApproved;
}
