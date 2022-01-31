package com.epam.film.rating.dao.builder;

import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.FilmReviewDTO;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.*;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.entity.user.Role;
import com.epam.film.rating.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstanceBuilder {

    public static User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt(UserDAOImpl.ID));
        user.setLogin(resultSet.getString(UserDAOImpl.LOGIN));
        user.setPassword(resultSet.getString(UserDAOImpl.PASSWORD));
        user.setNickname(resultSet.getString(UserDAOImpl.NICKNAME));
        user.setName(resultSet.getString(UserDAOImpl.NAME));
        user.setSurname(resultSet.getString(UserDAOImpl.SURNAME));
        user.setPhoneNumber(resultSet.getString(UserDAOImpl.PHONE_NUMBER));
        user.seteMail(resultSet.getString(UserDAOImpl.EMAIL));
        user.setBanned(resultSet.getBoolean(UserDAOImpl.IS_BANNED));
        user.setRating(resultSet.getDouble(UserDAOImpl.RATING));
        user.setAvatarImage(resultSet.getString(UserDAOImpl.AVATAR_IMAGE));
        user.setRole(Role.getRole(resultSet.getInt(UserDAOImpl.ROLE_ID)));
        user.setStatus(resultSet.getString(UserDAOImpl.STATUS));

        System.out.println("USER IS BLOCKED " + user.isBanned());

        return user;
    }

//    public static User buildUser(ResultSet resultSet) throws SQLException {
//        User user = new User();
//
//        user.setLogin(resultSet.getString(UserDAOImpl.LOGIN));
//        user.setPassword(resultSet.getString(UserDAOImpl.PASSWORD));
//        user.setNickname(resultSet.getString(UserDAOImpl.NICKNAME));
//        user.setName(resultSet.getString(UserDAOImpl.NAME));
//        user.setSurname(resultSet.getString(UserDAOImpl.SURNAME));
//        user.setPhoneNumber(resultSet.getString(UserDAOImpl.PHONE_NUMBER));
//        user.seteMail(resultSet.getString(UserDAOImpl.EMAIL));
//        user.setBanned(resultSet.getBoolean(UserDAOImpl.IS_BANNED));
//
//        return user;
//    }

    public static Review buildReview(ResultSet resultSet) throws SQLException {
        Review review = new Review();

        review.setId(resultSet.getInt(ReviewDAOImpl.ID));
        review.setReview(resultSet.getString(ReviewDAOImpl.REVIEW));
        review.setMark(resultSet.getInt(ReviewDAOImpl.MARK));
        review.setLikesAmount(resultSet.getInt(ReviewDAOImpl.LIKES_AMOUNT));
        review.setDislikesAmount(resultSet.getInt(ReviewDAOImpl.DISLIKES_AMOUNT));

        return review;
    }

    public static ReviewApproval buildReviewApproval(ResultSet resultSet) throws SQLException {
        ReviewApproval reviewApproval = new ReviewApproval();

        reviewApproval.setLiked(resultSet.getBoolean(ReviewDAOImpl.IS_LIKED));
        reviewApproval.setDisliked(resultSet.getBoolean(ReviewDAOImpl.IS_DISLIKED));

        return reviewApproval;
    }

    public static ReviewDTO buildReviewDTO(ResultSet resultSet) throws SQLException {
        ReviewDTO review = new ReviewDTO();

        review.setId(resultSet.getInt(ReviewDAOImpl.ID));
        review.setReview(resultSet.getString(ReviewDAOImpl.REVIEW));
        review.setMark(resultSet.getInt(ReviewDAOImpl.MARK));
        review.setLikesAmount(resultSet.getInt(ReviewDAOImpl.LIKES_AMOUNT));
        review.setDislikesAmount(resultSet.getInt(ReviewDAOImpl.DISLIKES_AMOUNT));
        review.setNickname(resultSet.getString(UserDAOImpl.NICKNAME));
        review.setRating(resultSet.getDouble(UserDAOImpl.RATING));
        review.setAvatarImage(resultSet.getString(UserDAOImpl.AVATAR_IMAGE));
        review.setStatus(resultSet.getString(UserDAOImpl.STATUS));

        return review;
    }

    public static Film buildFilm(ResultSet resultSet) throws SQLException {
        Film film = new Film();

        film.setId(resultSet.getInt(FilmDAOImpl.ID));
        film.setProductionYear(resultSet.getInt(FilmDAOImpl.PRODUCTION_YEAR));
        film.setName(resultSet.getString(FilmDAOImpl.NAME));
        film.setDescription(resultSet.getString(FilmDAOImpl.DESCRIPTION));
        film.setFilmRating(resultSet.getDouble(FilmDAOImpl.FILM_RATING));
        film.setReviewAmount(resultSet.getInt(FilmDAOImpl.REVIEW_AMOUNT));
        film.setAgeRating(resultSet.getString(FilmDAOImpl.AGE_RATING));
        film.setType(resultSet.getString(FilmDAOImpl.TYPE));

        return film;
    }

    public static FilmReviewDTO buildFilmReviewDTO(ResultSet resultSet) throws SQLException {
        FilmReviewDTO filmReviewDTO = new FilmReviewDTO();

        filmReviewDTO.setReviewId(resultSet.getInt(ReviewDAOImpl.ID));
        filmReviewDTO.setFilmName(resultSet.getString(FilmDAOImpl.NAME));
        filmReviewDTO.setReview(resultSet.getString(ReviewDAOImpl.REVIEW));
        filmReviewDTO.setUserId(resultSet.getInt("users_id"));
//        filmReviewDTO.setBanned(resultSet.getBoolean("is_banned"));

//        System.out.println("it's banned = " + filmReviewDTO.getIsBanned());

        return filmReviewDTO;
    }
}
