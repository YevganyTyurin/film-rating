package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.dao.FilmDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.film.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilmDAOImpl implements FilmDAO {

    public static final String ID = "id";
    public static final String PRODUCTION_YEAR = "production_year";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String FILM_RATING = "film_rating";
    public static final String REVIEW_AMOUNT = "review_amount";
    public static final String AGE_RATING = "age_rating";
    public static final String TYPE = "type";
    public static final String GENRE = "genre";
    public static final String COUNTRY_OF_ORIGIN = "production_country";
    public static final String TRAILER_VIDEO = "trailer_video";
    public static final String POSTER_IMAGE = "poster_image";

    public static String GET_POSTERS_BY_ID = "SELECT poster_image FROM film_poster WHERE film_id=?;";
    public static String GET_TRAILERS_BY_ID = "SELECT trailer_video FROM film_trailer WHERE film_id=?;";
    public static String GET_GENRES_BY_ID = "SELECT genre.genre FROM genre JOIN film_genre ON genre.id=film_genre.genre_id WHERE film_genre.film_id=?;";
    public static String GET_COUNTRY_OF_ORIGIN_BY_ID = "SELECT country_of_origin.production_country FROM country_of_origin JOIN film_country_of_origin ON country_of_origin.id=film_country_of_origin.country_of_origin_id WHERE film_country_of_origin.film_id=?;";
    public static String ADD_FILM = "INSERT INTO film (production_year, name, description, type_id, age_rating_id) VALUES (?, ?, ?, ?, ?);";
    public static String ADD_FILM_GENRE = "INSERT INTO film_genre (film_id, genre_id) VALUES (?, ?);";
    public static String GET_FILM_BY_ID = "SELECT film.id, film.production_year, film.name, film.description, film.film_rating, film.review_amount, film_age_rating.age_rating, film_type.type FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE film.id=?";
    public static String GET_AGE_RATING_ID = "SELECT film_age_rating.id FROM film_age_rating WHERE film_age_rating.age_rating=?;";
    public static String GET_FILM_TYPE_ID = "SELECT film_type.id FROM film_type WHERE film_type.type=?;";
    public static String GET_GENRE_ID = "SELECT genre.id FROM genre WHERE genre.genre=?;";
    public static String GET_FILM_ID = "SELECT film.id FROM film WHERE film.production_year=? AND film.name=?";

    ConnectionPool connectable = ConnectionPool.getInstance();

    public FilmDAOImpl() {}

    private String createSqlQueryGetFilms (String year, String ageRating, String filmType, String []genres, int startFromRecordNumber) {
        StringBuilder filmParameters = new StringBuilder();

        filmParameters.append("SELECT film.id, film.production_year, film.name, film.description, film.film_rating, film.review_amount, film_age_rating.age_rating, film_type.type FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE 1=1");

        if (year != null && year != "") {
            filmParameters.append(" AND film.production_year = '").append(year).append("'");
        }

        if (ageRating != null) {
            filmParameters.append(" AND film_age_rating.age_rating = '").append(ageRating).append("'");
        }

        if (filmType != null) {
            filmParameters.append(" AND film_type.type = '").append(filmType).append("'");
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

    private String createSqlQueryGetFilmAmount (String year, String ageRating, String filmType, String []genres) {
        StringBuilder filmParameters = new StringBuilder();

        filmParameters.append("SELECT COUNT(film.id) as filmAmount FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE 1=1");

        if (year != null && year != "") {
            filmParameters.append(" AND film.production_year = '").append(year).append("'");
        }

        if (ageRating != null) {
            filmParameters.append(" AND film_age_rating.age_rating = '").append(ageRating).append("'");
        }

        if (filmType != null) {
            filmParameters.append(" AND film_type.type = '").append(filmType).append("'");
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
        filmParameters.append(";");

        return filmParameters.toString();
    }

    @Override
    public List<Film> getFilmsByParameters(String year, String ageRating, String filmType, String []genres, int amount) throws DAOException {
        int id;
        String sql = createSqlQueryGetFilms(year, ageRating, filmType, genres, amount);
        try (Connection connection = connectable.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){

            List<Film> films= new ArrayList<>();

            while (resultSet.next()) {
                films.add(InstanceBuilder.buildFilm(resultSet));
            }

            for (Film film: films ) {
                id = film.getId();

                film.setGenre(getGenresById(id));
                film.setPoster(getPostersById(id));
                film.setCountryOfOrigin(getCountriesOfOriginById(id));
                film.setTrailer(getTrailersById(id));
            }
            return films;
        }catch (SQLException  e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int getFilmAmount(String year, String ageRating, String filmType, String []genres) throws DAOException {
        String sql = createSqlQueryGetFilmAmount(year, ageRating, filmType, genres);
        try (Connection connection = connectable.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                return resultSet.getInt(Parameter.FILM_AMOUNT);
            }
            return 0;
        }catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private List<String> getPostersById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<String> posters = new ArrayList<>();
            connection = connectable.getConnection();

            preparedStatement = connection.prepareStatement(GET_POSTERS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                posters.add(resultSet.getString(FilmDAOImpl.POSTER_IMAGE));
            }
            if (posters.isEmpty()) {
                posters = Collections.emptyList();
            }
            return posters;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    private List<String> getTrailersById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<String> trailers = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_TRAILERS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trailers.add(resultSet.getString(FilmDAOImpl.TRAILER_VIDEO));
            }
            if (trailers.isEmpty()) {
                trailers = Collections.emptyList();
            }
            return trailers;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    private List<String> getGenresById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<String> genres = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_GENRES_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(resultSet.getString(FilmDAOImpl.GENRE));
            }
            return genres;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    private List<String> getCountriesOfOriginById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<String> countriesOfOrigin = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_COUNTRY_OF_ORIGIN_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countriesOfOrigin.add(resultSet.getString(FilmDAOImpl.COUNTRY_OF_ORIGIN));
            }
            if (countriesOfOrigin.isEmpty()) {
                countriesOfOrigin = Collections.emptyList();
            }
            return countriesOfOrigin;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Film getFilmById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Film film = new Film();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_FILM_BY_ID);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                film = InstanceBuilder.buildFilm(resultSet);
            }
            return film;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int getAgeRatingId(String ageRating) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_AGE_RATING_ID);

            preparedStatement.setString(1, ageRating);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(FilmDAOImpl.ID);
            }
            return 0;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int getFilmTypeId(String filmType) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_FILM_TYPE_ID);

            preparedStatement.setString(1, filmType);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(FilmDAOImpl.ID);
            }
            return 0;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int getGenreId(String genre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_GENRE_ID);

            preparedStatement.setString(1, genre);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(FilmDAOImpl.ID);
            }
            return 0;
        }catch (SQLException  e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int getFilmId(String filmName, int year) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_FILM_ID);

            preparedStatement.setInt(1, year);
            preparedStatement.setString(2, filmName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt(FilmDAOImpl.ID);
            }
            return 0;
        }catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int addFilm (String filmName, int productionYear, String description, int ageRatingId, int typeId) throws DAOException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_FILM);

            pr.setInt(1, productionYear);
            pr.setString(2, filmName);
            pr.setString(3, description);
            pr.setInt(4, typeId);
            pr.setInt(5, ageRatingId);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return 1;
    }

    @Override
    public int addFilmGenre(int filmId, int genreId) throws DAOException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_FILM_GENRE);

            pr.setInt(1, filmId);
            pr.setInt(2, genreId);

            return pr.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(pr, connection);
        }
    }
}