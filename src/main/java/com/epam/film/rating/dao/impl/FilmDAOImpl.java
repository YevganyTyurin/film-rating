package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
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

    public static String GET_POSTERS_BY_ID = "select poster_image from film_poster where film_id=?;";
    public static String GET_TRAILERS_BY_ID = "select trailer_video from film_trailer where film_id=?;";
    public static String GET_GENRES_BY_ID = "SELECT genre.genre FROM genre JOIN film_genre ON genre.id=film_genre.genre_id WHERE film_genre.film_id=?;";
    public static String GET_COUNTRY_OF_ORIGIN_BY_ID = "SELECT country_of_origin.production_country FROM country_of_origin JOIN film_country_of_origin ON country_of_origin.id=film_country_of_origin.country_of_origin_id WHERE film_country_of_origin.film_id=?;";
    public static String GET_FILMS = "select * from film;";
    public static String ADD_FILM = "insert into film (production_year, name, description, type_id, age_rating_id) values(?, ?, ?, ?, ?);";
    public static String ADD_FILM_GENRE = "insert into film_genre (film_id, genre_id) values(?, ?);";
    public static String GET_FILM_BY_ID = "SELECT film.id, film.production_year, film.name, film.description, film.film_rating, film.review_amount, film_age_rating.age_rating, film_type.type FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE film.id=?";
    public static String GET_AGE_RATING_ID = "SELECT film_age_rating.id FROM film_age_rating WHERE film_age_rating.age_rating=?;";
    public static String GET_FILM_TYPE_ID = "SELECT film_type.id FROM film_type WHERE film_type.type=?;";
    public static String GET_GENRE_ID = "SELECT genre.id FROM genre WHERE genre.genre=?;";
    public static String GET_FILM_ID = "SELECT film.id FROM film WHERE film.production_year=? AND film.name=?";

    ConnectionPool connectable = ConnectionPool.getInstance();

    public FilmDAOImpl() {}

    public String createSQL (String year, String age_rating, String film_type, String genres[], int startFromRecordNumber) {
        StringBuilder filmParameters = new StringBuilder();

        filmParameters.append("SELECT film.id, film.production_year, film.name, film.description, film.film_rating, film.review_amount, film_age_rating.age_rating, film_type.type FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE 1=1");


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

    public String createSQL222 (String year, String age_rating, String film_type, String genres[]) {
        StringBuilder filmParameters = new StringBuilder();

        filmParameters.append("SELECT COUNT(film.id) as filmAmount FROM film JOIN film_age_rating ON film.age_rating_id=film_age_rating.id JOIN film_type ON film.type_id=film_type.id WHERE 1=1");

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
        filmParameters.append(";"); //TODO

        System.out.println(filmParameters.toString());
        return filmParameters.toString();
    }

    public List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[], int amount) throws DAOException {
        int id;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = createSQL(year, age_rating, film_type, genres, amount);
        try {
            List<Film> films= new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("nice");
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
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public List<Film> getAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Film> users = new ArrayList<>();
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_FILMS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(InstanceBuilder.buildFilm(resultSet));
            }
            return users;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int getFilmAmount(String year, String age_rating, String film_type, String genres[]) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = createSQL222(year, age_rating, film_type, genres);
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("filmAmount"); //TODO
            }
            return 0;
        }catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public List<String> getPostersById(int id) throws SQLException {
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
            if (posters.isEmpty()) { //TODO is it correct?
                posters = Collections.EMPTY_LIST;
            }
            return posters;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public List<String> getTrailersById(int id) throws SQLException {
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
            if (trailers.isEmpty()) { //TODO is it correct?
                trailers = Collections.EMPTY_LIST;
            }
            return trailers;
        }catch (SQLException  e) {
            throw new SQLException();
        } finally {
            connectable.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public List<String> getGenresById(int id) throws SQLException {
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

    public List<String> getCountriesOfOriginById(int id) throws SQLException {
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
            if (countriesOfOrigin.isEmpty()) { //TODO is it correct?
                countriesOfOrigin = Collections.EMPTY_LIST;
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
            System.out.println("good");
            resultSet = preparedStatement.executeQuery();
            System.out.println("very good");
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
            System.out.println("good");
            pr.executeUpdate();

            System.out.println("super good");
        } catch (SQLException e) { //TODO
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