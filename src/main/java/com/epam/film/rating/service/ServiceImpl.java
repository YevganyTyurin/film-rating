package com.epam.film.rating.service;

import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.FilmDAO;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public class ServiceImpl implements Service{
    @Override
    public List<Review> getReviewById(int filmId, int userId) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.getReviewById(filmId, userId);
    }

    @Override
    public List<ReviewDTO> getReviewsByFilmId(int filmId) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.getReviewsByFilmId(filmId);
    }

    @Override
    public List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[]) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        return  filmDAO.getFilmsByParameters(year, age_rating, film_type, genres);
    }

    @Override
    public int getLikesAmountById(int reviewId) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.getLikesAmountById(reviewId);
    }

    @Override
    public ReviewApproval getReviewApprovalById(int userId, int reviewId) throws SQLException, InterruptedException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.getReviewApprovalById(userId, reviewId);
    }

    @Override
    public Film getFilmById(int id) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        return filmDAO.getFilmById(id);
    }

    @Override
    public boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws SQLException, InterruptedException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.updateReviewApprovalLike(isLiked, userId, reviewId);
    }

    @Override
    public boolean updateLikesAmountById(int likesAmount, int reviewId) throws SQLException, InterruptedException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.updateLikesAmountById(likesAmount, reviewId);
    }

    @Override
    public boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws SQLException, InterruptedException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.addReviewApproval(userId, reviewId, isLiked, isDisliked);
    }

    @Override
    public int getDislikesAmountById(int reviewId) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.getDislikesAmountById(reviewId);
    }

    public boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws SQLException, InterruptedException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.updateReviewApprovalDislike(isDisliked, userId, reviewId);
    }

    @Override
    public boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws SQLException, InterruptedException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.updateDislikesAmountById(dislikesAmount, reviewId);
    }

    @Override
    public boolean addReview(Review review, int filmId) throws SQLException, InterruptedException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        return reviewDAO.addReview(review, filmId);
    }

    @Override
    public User login(String login, String password) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        return userDAO.login(login, password);
    }
}