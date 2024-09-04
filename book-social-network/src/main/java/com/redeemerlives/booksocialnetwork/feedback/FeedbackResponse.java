package com.redeemerlives.booksocialnetwork.feedback;

import lombok.Builder;

@Builder
public record FeedbackResponse(
        Float note,
        String comment,
        boolean ownFeedback
) {
}
