package com.epam.film.rating.service;

import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.service.exception.ServiceException;

import java.util.List;

public interface FilmService {

    Film getFilmById(int id) throws ServiceException;

    List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[], int startFromRecordNumber) throws ServiceException;

    int getFilmAmount(String year, String age_rating, String film_type, String genres[]) throws ServiceException;
}
