package com.epam.film.rating.dao;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.review.ReviewApproval;

public interface ReviewApprovalDAO {
    ReviewApproval getReviewApprovalById(int userId, int reviewId) throws DAOException;

    boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws DAOException;

    boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws DAOException;

    boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws DAOException;
}
