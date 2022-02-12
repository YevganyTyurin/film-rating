package com.epam.film.rating.service.impl;

import com.epam.film.rating.dao.*;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.FilmReviewDTO;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.service.DtoService;
import com.epam.film.rating.service.exception.ServiceException;

import java.util.List;

public class DtoServiceImpl implements DtoService {
//    @Override
//    public List<Review> getReviewById(int filmId, int userId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.getReviewById(filmId, userId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

    @Override
    public List<ReviewDTO> getReviewsByFilmId(int filmId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.getReviewsByFilmId(filmId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<FilmReviewDTO> getReviewsByParameters(String year, String ageRating, String filmType, String []genres, int startFromRecordNumber, String userId, String filmId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DtoDAO dtoDAO = factory.getDtoDAO();
        try {
            return dtoDAO.getReviewsByParameters(year, ageRating, filmType, genres, startFromRecordNumber, userId, filmId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

//    @Override
//    public List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[], int startFromRecordNumber) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        FilmDAO filmDAO = factory.getFilmDAO();
//        try {
//            return  filmDAO.getFilmsByParameters(year, age_rating, film_type, genres, startFromRecordNumber);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public int getLikesAmountById(int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.getLikesAmountById(reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public ReviewApproval getReviewApprovalById(int userId, int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.getReviewApprovalById(userId, reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }
//    @Override
//    public ReviewApproval getReviewApprovalById(int userId, int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewApprovalDAO reviewApprovalDAO = factory.getReviewApprovalDAO();
//        try {
//            return reviewApprovalDAO.getReviewApprovalById(userId, reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public Film getFilmById(int id) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        FilmDAO filmDAO = factory.getFilmDAO();
//        try {
//            return filmDAO.getFilmById(id);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.updateReviewApprovalLike(isLiked, userId, reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public int getFilmAmount(String year, String age_rating, String film_type, String[] genres) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        FilmDAO filmDAO = factory.getFilmDAO();
//        try {
//            return filmDAO.getFilmAmount(year, age_rating, film_type, genres);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//
//    }

//    @Override
//    public boolean updateLikesAmountById(int likesAmount, int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.updateLikesAmountById(likesAmount, reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.addReviewApproval(userId, reviewId, isLiked, isDisliked);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public int getDislikesAmountById(int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.getDislikesAmountById(reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.updateReviewApprovalDislike(isDisliked, userId, reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.updateDislikesAmountById(dislikesAmount, reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public boolean addReview(Review review, int filmId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.addReview(review, filmId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public User login(String login, String password) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        UserDAO userDAO = factory.getUserDAO();
//        try {
//            return userDAO.login(login, password);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }

//    @Override
//    public Review getReviewByReviewId(int reviewId) throws ServiceException {
//        DAOFactory factory = DAOFactory.getInstance();
//        ReviewDAO reviewDAO = factory.getReviewDAO();
//        try {
//            return reviewDAO.getReviewByReviewId(reviewId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//    }
}
