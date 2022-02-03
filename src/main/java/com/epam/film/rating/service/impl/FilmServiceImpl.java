package com.epam.film.rating.service.impl;

import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.FilmDAO;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.service.FilmService;
import com.epam.film.rating.service.exception.ServiceException;

import java.util.List;

public class FilmServiceImpl implements FilmService {
    @Override
    public Film getFilmById(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return filmDAO.getFilmById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Film> getFilmsByParameters(String year, String age_rating, String film_type, String genres[], int startFromRecordNumber) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return  filmDAO.getFilmsByParameters(year, age_rating, film_type, genres, startFromRecordNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getFilmAmount(String year, String age_rating, String film_type, String[] genres) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return filmDAO.getFilmAmount(year, age_rating, film_type, genres);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int addFilm (String filmName, int productionYear, String description, int ageRatingId, int typeId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return filmDAO.addFilm(filmName, productionYear, description, ageRatingId, typeId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getFilmId(String filmName, int year) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return filmDAO.getFilmId(filmName, year);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int addFilmGenre(int filmId, int genreId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return filmDAO.addFilmGenre(filmId, genreId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getAgeRatingId(String ageRating) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return filmDAO.getAgeRatingId(ageRating);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getFilmTypeId(String filmType) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return filmDAO.getFilmTypeId(filmType);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getGenreId(String genre) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        FilmDAO filmDAO = factory.getFilmDAO();
        try {
            return filmDAO.getGenreId(genre);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
