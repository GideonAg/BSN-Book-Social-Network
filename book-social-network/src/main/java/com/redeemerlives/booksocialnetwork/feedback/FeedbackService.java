package com.redeemerlives.booksocialnetwork.feedback;

import com.redeemerlives.booksocialnetwork.book.Book;
import com.redeemerlives.booksocialnetwork.book.BookRepository;
import com.redeemerlives.booksocialnetwork.common.PageResponse;
import com.redeemerlives.booksocialnetwork.exception.OperationNotPermittedException;
import com.redeemerlives.booksocialnetwork.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRepository bookRepository;
    private final FeedbackRepository feedbackRepository;

    public Integer saveFeedback(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + request.bookId()));

        if (book.isArchived() || !book.isShareable())
            throw new OperationNotPermittedException("This book is currently not available for receiving feedback");

        User user = (User) connectedUser.getPrincipal();
        if (Objects.equals(user.getId(), book.getOwner().getId()))
            throw new OperationNotPermittedException("You cannot give a feedback to your own book");

        Feedback feedback = Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(book)
                .build();
        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbackByBook(Integer bookId,
                                                                int page,
                                                                int size,
                                                                Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        User user = (User) connectedUser.getPrincipal();
        Page<Feedback> feedbacks = feedbackRepository.findAllFeedbackByBookId(bookId, pageable);

        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(feedback -> FeedbackResponse.builder()
                        .note(feedback.getNote())
                        .comment(feedback.getComment())
                        .ownFeedback(Objects.equals(feedback.getCreatedBy(), user.getId()))
                        .build()
                )
                .toList();

        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
