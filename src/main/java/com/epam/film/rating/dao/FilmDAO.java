package com.epam.film.rating.dao;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.film.Film;

import java.util.List;

public interface FilmDAO {

    List<Film> getFilmsByParameters(String year, String ageRating, String filmType, String []genres, int startFromRecordNumber) throws DAOException;

    Film getFilmById(int id) throws DAOException;

    int getFilmAmount(String year, String ageRating, String filmType, String []genres) throws DAOException;

    int getAgeRatingId(String ageRating) throws DAOException;

    int getFilmTypeId(String filmType) throws DAOException;

    int getGenreId(String genre) throws DAOException;

    int addFilm (String filmName, int productionYear, String description, int ageRatingId, int typeId) throws DAOException;

    int getFilmId(String filmName, int year) throws DAOException;

    int addFilmGenre(int filmId, int genreId) throws DAOException;
}