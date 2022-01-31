package com.epam.film.rating.service.impl;

import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.ReviewApprovalDAO;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.service.ReviewApprovalService;
import com.epam.film.rating.service.exception.ServiceException;

public class ReviewApprovalImpl implements ReviewApprovalService {
    @Override
    public ReviewApproval getReviewApprovalById(int userId, int reviewId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewApprovalDAO reviewApprovalDAO = factory.getReviewApprovalDAO();
        try {
            return reviewApprovalDAO.getReviewApprovalById(userId, reviewId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewApprovalDAO reviewApprovalDAO = factory.getReviewApprovalDAO();
        try {
            return reviewApprovalDAO.updateReviewApprovalLike(isLiked, userId, reviewId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewApprovalDAO reviewApprovalDAO = factory.getReviewApprovalDAO();
        try {
            return reviewApprovalDAO.updateReviewApprovalDislike(isDisliked, userId, reviewId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewApprovalDAO reviewApprovalDAO = factory.getReviewApprovalDAO();
        try {
            return reviewApprovalDAO.addReviewApproval(userId, reviewId, isLiked, isDisliked);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
