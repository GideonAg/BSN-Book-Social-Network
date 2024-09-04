package com.redeemerlives.booksocialnetwork.feedback;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public record FeedbackResponse(
        Float note,
        String comment,
        boolean ownFeedback
) {
}
