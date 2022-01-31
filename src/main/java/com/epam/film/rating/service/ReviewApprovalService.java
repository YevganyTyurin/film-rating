package com.epam.film.rating.service;

import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.service.exception.ServiceException;

public interface ReviewApprovalService {
    ReviewApproval getReviewApprovalById(int userId, int reviewId) throws ServiceException;

    boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws ServiceException;

    boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws ServiceException;

    boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws ServiceException;
}
