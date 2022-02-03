package com.epam.film.rating.service;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.service.exception.ServiceException;

import java.util.List;

public interface FilmService {

    Film getFilmById(int id) throws ServiceException;

    List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[], int startFromRecordNumber) throws ServiceException;

    int getFilmAmount(String year, String age_rating, String film_type, String genres[]) throws ServiceException;

    int addFilm (String filmName, int productionYear, String description, int ageRatingId, int typeId) throws ServiceException;

    int getFilmId(String filmName, int year) throws ServiceException;

    int addFilmGenre(int filmId, int genreId) throws ServiceException;

    int getAgeRatingId(String ageRating) throws ServiceException;

    int getFilmTypeId(String filmType) throws ServiceException;

    int getGenreId(String genre) throws ServiceException;
}
