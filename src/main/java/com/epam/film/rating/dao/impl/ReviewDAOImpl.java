package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.FilmReviewDTO;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    public static final String ID = "id";
    public static final String REVIEW = "review";
    public static final String MARK = "mark";
    public static final String LIKES_AMOUNT = "likes_amount";
    public static final String DISLIKES_AMOUNT = "dislikes_amount";
    public static final String IS_LIKED = "is_liked";
    public static final String IS_DISLIKED = "is_disliked";

    ConnectionPool connectable = ConnectionPool.getInstance();

    public ReviewDAOImpl() {}

    public static String GET_REVIEWS_BY_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount from review WHERE review.film_id=? AND review.users_id=?;";
    public static String GET_REVIEW_BY_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount from review WHERE review.id=?;";
    public static String GET_REVIEWS_BY_FILM_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount, user.nickname, user.rating, user.avatar_image, user_status.status FROM user JOIN review ON user.id=review.users_id JOIN user_status ON user_status.id=user.user_status_id WHERE review.film_id=?;";
    public static String ADD_REVIEW = "insert into review (review, mark, film_id, users_id) values(?, ?, ?, ?);";
    public static String GET_REVIEW_DISLIKE_AMOUNT_BY_ID = "SELECT review.dislikes_amount from review WHERE review.id=?;";
    public static String GET_REVIEW_LIKE_AMOUNT_BY_ID = "SELECT review.likes_amount from review WHERE review.id=?;";
    public static String UPDATE_LIKES_AMOUNT_BY_ID = "update review set review.likes_amount=? where review.id=?;";
    public static String UPDATE_REVIEW_BY_ID = "update review set review.review=? where review.id=?;";
    public static String DELETE_REVIEW = "DELETE FROM review WHERE id=?;";
    public static String UPDATE_DISLIKES_AMOUNT_BY_ID = "update review set review.dislikes_amount=? where review.id=?;";

    @Override
    public int getReviewAmount(String year, String ageRating, String filmType, String []genres, String filmId, String userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = createSqlQueryAmountOfReviews(year, ageRating, filmType, genres, filmId, userId);
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(Parameter.REVIEW_AMOUNT);
            }
            return 0;
        }catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    private String createSqlQueryAmountOfReviews (String year, String ageRating, String filmType, String []genres, String filmId, String userId) {
        StringBuilder filmParameters = new StringBuilder();

        filmParameters.append("SELECT COUNT(review.id) as reviewAmount FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id JOIN review ON review.film_id=film.id JOIN user ON user.id=review.users_id WHERE 1=1");

        if (year != null && year != "") {
            filmParameters.append(" AND film.production_year = '").append(year).append("'");
        }

        if (ageRating != null) {
            filmParameters.append(" AND film_age_rating.age_rating = '").append(ageRating).append("'");
        }

        if (filmType != null) {
            filmParameters.append(" AND film_type.type = '").append(filmType).append("'");
        }

        if (filmId != null && filmId != "") {
            filmParameters.append(" AND review.film_id = '").append(filmId).append("'");
        }

        if (userId != null && userId != "") {
            filmParameters.append(" AND review.users_id = '").append(userId).append("'");
        }

        if (genres != null) {
            filmParameters.append(" AND film.id IN (SELECT film_genre.film_id FROM film_genre JOIN genre ON film_genre.genre_id=genre.id WHERE genre.genre in(");
            for (int i = 0; i < genres.length; i++) {
                filmParameters.append("'").append(genres[i]).append("'");
                if (genres.length - i > 1) {
                    filmParameters.append(", ");
                }
            }
            filmParameters.append(") GROUP BY film_genre.film_id HAVING count(*) ='");
            filmParameters.append(genres.length);
            filmParameters.append("');");
        }
        return filmParameters.toString();
    }

    @Override
    public boolean updateDislikesAmountById(int dislikesAmount, int reviewId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_DISLIKES_AMOUNT_BY_ID);
            preparedStatement.setInt(1, dislikesAmount);
            preparedStatement.setInt(2, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    @Override
    public Review getReviewByReviewId(int reviewId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_BY_ID);
            preparedStatement.setInt(1, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return InstanceBuilder.buildReview(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return null;
    }

    @Override
    public boolean updateLikesAmountById(int likesAmount, int reviewId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_LIKES_AMOUNT_BY_ID);
            preparedStatement.setInt(1, likesAmount);
            preparedStatement.setInt(2, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    @Override
    public List<Review> getReviewById(int filmId, int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Review> reviews = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEWS_BY_ID);
            preparedStatement.setInt(1, filmId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reviews.add(InstanceBuilder.buildReview(resultSet));
            }
            return reviews;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int getDislikesAmountById(int reviewId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_DISLIKE_AMOUNT_BY_ID);
            preparedStatement.setInt(1, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(ReviewDAOImpl.DISLIKES_AMOUNT);
            }
            return -1;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int getLikesAmountById(int reviewId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_LIKE_AMOUNT_BY_ID);
            preparedStatement.setInt(1, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(ReviewDAOImpl.LIKES_AMOUNT);
            }
            return -1;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public boolean addReview(Review review, int filmId) throws DAOException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_REVIEW);

            pr.setString(1, review.getReview());
            pr.setInt(2, review.getMark());
            pr.setInt(3, filmId);
            pr.setInt(4, review.getUserId());

            if(pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return false;
    }

    @Override
    public List<ReviewDTO> getReviewsByFilmId(int filmId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<ReviewDTO> reviews = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEWS_BY_FILM_ID);
            preparedStatement.setInt(1, filmId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reviews.add(InstanceBuilder.buildReviewDTO(resultSet));
            }
            if (reviews.isEmpty()) {
                reviews = Collections.emptyList();
            }
            return reviews;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public boolean updateReview(int reviewId, String review) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_REVIEW_BY_ID);
            preparedStatement.setString(1, review);
            preparedStatement.setInt(2, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    @Override
    public boolean deleteReview (int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_REVIEW);
            preparedStatement.setInt(1, id);
            if(preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }
}