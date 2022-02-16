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
    public List<FilmReviewDTO> getReviewsByParameters(String year, String ageRating, String filmType, String []genres, int amount, String filmId, String userId) throws DAOException {
        String sql = createSqlQueryGetReviews (year, ageRating, filmType, genres, amount, filmId, userId);
        try (Connection connection = connectable.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            List<FilmReviewDTO> films= new ArrayList<>();
            while (resultSet.next()) {
                films.add(InstanceBuilder.buildFilmReviewDTO(resultSet));
            }
            return films;
        }catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private String createSqlQueryGetReviews (String year, String ageRating, String filmType, String []genres, int startFromRecordNumber, String filmId, String userId) {
        StringBuilder filmParameters = new StringBuilder();

        filmParameters.append("SELECT review.id, film.name, review.review, review.users_id FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id JOIN review ON review.film_id=film.id JOIN user ON user.id=review.users_id WHERE 1=1");


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
            filmParameters.append("')");
        }
        filmParameters.append(" LIMIT 2 OFFSET ");
        filmParameters.append(startFromRecordNumber);
        filmParameters.append(";");

        return filmParameters.toString();
    }
}
