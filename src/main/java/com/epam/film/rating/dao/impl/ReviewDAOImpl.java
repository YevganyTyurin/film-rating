package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.FilmReviewDTO;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.entity.user.User;

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

    public ReviewDAOImpl() {};

    public static String GET_REVIEWS_BY_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount from review WHERE review.film_id=? AND review.users_id=?;";
    public static String GET_REVIEW_BY_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount from review WHERE review.id=?;";

    public static String GET_REVIEWS_BY_FILM_ID = "select review.id, review.review, review.mark, review.likes_amount, review.dislikes_amount, user.nickname, user.rating, user.avatar_image, user_status.status FROM user JOIN review ON user.id=review.users_id JOIN user_status ON user_status.id=user.user_status_id WHERE review.film_id=?;";

    public static String GET_REVIEWS = "select * from review;";
    public static String ADD_REVIEW = "insert into review (review, mark, film_id, users_id) values(?, ?, ?, ?);";

    public static String GET_REVIEW_DISLIKE_AMOUNT_BY_ID = "SELECT review.dislikes_amount from review WHERE review.id=?;";
    public static String GET_REVIEW_LIKE_AMOUNT_BY_ID = "SELECT review.likes_amount from review WHERE review.id=?;";

    public static String UPDATE_LIKES_AMOUNT_BY_ID = "update review set review.likes_amount=? where review.id=?;";
    public static String UPDATE_DISLIKES_AMOUNT_BY_ID = "update review set review.dislikes_amount=? where review.id=?;";

//    public static String GET_REVIEW_APPROVAL = "SELECT user_review_approval.is_liked FROM user_review_approval WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";
    public static String GET_REVIEW_APPROVAL = "SELECT user_review_approval.is_liked, user_review_approval.is_disliked FROM user_review_approval WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";

//    public static String ADD_REVIEW_APPROVAL = "insert into user_review_approval (users_id, review_id, is_liked, is_disliked) values(?, ?, ?, ?);";

//    public static String UPDATE_REVIEW_APPROVAL_LIKE = "update user_review_approval set user_review_approval.is_liked=? WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";
//    public static String UPDATE_REVIEW_APPROVAL_DISLIKE = "update user_review_approval set user_review_approval.is_disliked=? WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";

//    public List<String> getReviewsByParameters(String year, String age_rating, String film_type, String genres[], int amount, String filmId, String userId) throws DAOException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        String sql = createSQL(year, age_rating, film_type, genres, amount, filmId, userId);
//        try {
//            List<String> films= new ArrayList<>();
//            connection = connectable.getConnection();
//            preparedStatement = connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println("nicer dicer");
//                films.add(resultSet.getString("rev"));
//                for (String review: films) {
//                    System.out.println(review);
//                }
//            }
//
//            return films;
//        }catch (SQLException  e) {
//            throw new DAOException(e);
//        } finally {
//            connectable.closeConnection(resultSet, preparedStatement, connection);
//        }
//    }

    public List<FilmReviewDTO> getReviewsByParameters(String year, String age_rating, String film_type, String genres[], int amount, String filmId, String userId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = createSQL(year, age_rating, film_type, genres, amount, filmId, userId);
        try {
            List<FilmReviewDTO> films= new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("nicer dicer");
                films.add(InstanceBuilder.buildFilmReviewDTO(resultSet));
                for (FilmReviewDTO review: films) {
                    System.out.println(review.getReview());
                }
            }

            return films;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public String createSQL (String year, String age_rating, String film_type, String genres[], int startFromRecordNumber, String filmId, String userId) {
        StringBuilder filmParameters = new StringBuilder();

        filmParameters.append("SELECT review.id, film.name, review.review, review.users_id FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id JOIN review ON review.film_id=film.id JOIN user ON user.id=review.users_id WHERE 1=1");


        if (year != null && year != "") {
            System.out.println("year = " + year);
            filmParameters.append(" AND film.production_year = '").append(year).append("'");
        }

        if (age_rating != null) {
            filmParameters.append(" AND film_age_rating.age_rating = '").append(age_rating).append("'");
        }

        if (film_type != null) {
            filmParameters.append(" AND film_type.type = '").append(film_type).append("'");
        }

        if (filmId != null && filmId != "") {
            System.out.println("filmId = " + filmId);
            filmParameters.append(" AND review.film_id = '").append(filmId).append("'");
        }

        if (userId != null && userId != "") {
            System.out.println("userId = " + userId);
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
            filmParameters.append("')");
        }
        filmParameters.append(" LIMIT 2 OFFSET "); //TODO
        filmParameters.append(startFromRecordNumber); //TODO
        filmParameters.append(";"); //TODO

        System.out.println(filmParameters.toString());
        return filmParameters.toString();
    }



//    public String createSQL (String year, String age_rating, String film_type, String genres[], int startFromRecordNumber, String filmId, String userId) {
//        StringBuilder filmParameters = new StringBuilder();
//
//        filmParameters.append("SELECT review.review as rev FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id JOIN review ON review.film_id=film.id WHERE 1=1");
//
//
//        if (year != null && year != "") {
//            System.out.println("year = " + year);
//            filmParameters.append(" AND film.production_year = '").append(year).append("'");
//        }
//
//        if (age_rating != null) {
//            filmParameters.append(" AND film_age_rating.age_rating = '").append(age_rating).append("'");
//        }
//
//        if (film_type != null) {
//            filmParameters.append(" AND film_type.type = '").append(film_type).append("'");
//        }
//
//        if (filmId != null && filmId != "") {
//            System.out.println("filmId = " + filmId);
//            filmParameters.append(" AND review.film_id = '").append(filmId).append("'");
//        }
//
//        if (userId != null && userId != "") {
//            System.out.println("userId = " + userId);
//            filmParameters.append(" AND review.users_id = '").append(userId).append("'");
//        }
//
//        if (genres != null) {
//            filmParameters.append(" AND film.id IN (SELECT film_genre.film_id FROM film_genre JOIN genre ON film_genre.genre_id=genre.id WHERE genre.genre in(");
//            for (int i = 0; i < genres.length; i++) {
//                filmParameters.append("'").append(genres[i]).append("'");
//                if (genres.length - i > 1) {
//                    filmParameters.append(", ");
//                }
//            }
//            filmParameters.append(") GROUP BY film_genre.film_id HAVING count(*) ='");
//            filmParameters.append(genres.length);
//            filmParameters.append("')");
//        }
//        filmParameters.append(" LIMIT 2 OFFSET "); //TODO
//        filmParameters.append(startFromRecordNumber); //TODO
//        filmParameters.append(";"); //TODO
//
//        System.out.println(filmParameters.toString());
//        return filmParameters.toString();
//    }




    public List<Review> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Review> users = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEWS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(InstanceBuilder.buildReview(resultSet));
            }
            return users;
        }catch (SQLException  sqlE) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

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

//    public ReviewApproval getReviewApprovalById(int userId, int reviewId) throws DAOException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = connectable.getConnection();
//            preparedStatement = connection.prepareStatement(GET_REVIEW_APPROVAL);
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, reviewId);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                return InstanceBuilder.buildReviewApproval(resultSet);
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            connectable.closeConnection(preparedStatement, connection);
//        }
//        return null; //TODO
//    }

    public ReviewApproval getReviewApprovalLikeById(int userId, int reviewId) throws SQLException, InterruptedException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_APPROVAL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return InstanceBuilder.buildReviewApproval(resultSet);
            }
        } catch (SQLException e) {
            //TODO
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return null; //TODO
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
        return null; //TODO
    }

//    public boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws DAOException {
//        Connection connection = connectable.getConnection();
//        PreparedStatement pr = null;
//
//        try{
//            pr = connection.prepareStatement(ADD_REVIEW_APPROVAL);
//
//            pr.setInt(1, userId);
//            pr.setInt(2, reviewId);
//            pr.setBoolean(3, isLiked);
//            pr.setBoolean(4, isDisliked);
//
//            if(pr.executeUpdate() == 1) {
//                return true;
//            };
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            connectable.closeConnection(pr, connection);
//        }
//        return false;
//    }

//    public boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws DAOException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectable.getConnection();
//            preparedStatement = connection.prepareStatement(UPDATE_REVIEW_APPROVAL_LIKE);
//            preparedStatement.setBoolean(1, isLiked);
//            preparedStatement.setInt(2, userId);
//            preparedStatement.setInt(3, reviewId);
//            if (preparedStatement.executeUpdate() == 1) {
//                return true;
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            connectable.closeConnection(preparedStatement, connection);
//        }
//        return false;
//    }

//    public boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws DAOException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectable.getConnection();
//            preparedStatement = connection.prepareStatement(UPDATE_REVIEW_APPROVAL_DISLIKE);
//            preparedStatement.setBoolean(1, isDisliked);
//            preparedStatement.setInt(2, userId);
//            preparedStatement.setInt(3, reviewId);
//            if (preparedStatement.executeUpdate() == 1) {
//                return true;
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            connectable.closeConnection(preparedStatement, connection);
//        }
//        return false;
//    }

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
            };
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return false;

    }

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
                System.out.println("inside");
                reviews.add(InstanceBuilder.buildReviewDTO(resultSet));
                System.out.println("olso inside");
            }
            if (reviews.isEmpty()) { //TODO is it correct?
                reviews = Collections.EMPTY_LIST;
            }
            return reviews;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }
}
