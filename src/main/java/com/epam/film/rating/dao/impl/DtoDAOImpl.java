package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.dao.DtoDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.FilmReviewDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DtoDAOImpl implements DtoDAO {

    ConnectionPool connectable = ConnectionPool.getInstance();

    @Override
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
//TODO
            //TODO
            return films;
        }catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    private String createSQL (String year, String age_rating, String film_type, String genres[], int startFromRecordNumber, String filmId, String userId) {
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
}
