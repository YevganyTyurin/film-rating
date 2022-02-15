package com.epam.film.rating.service.impl;

import com.epam.film.rating.dao.*;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.FilmReviewDTO;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.service.DtoService;
import com.epam.film.rating.service.exception.ServiceException;

import java.util.List;

public class DtoServiceImpl implements DtoService {

    @Override
    public List<ReviewDTO> getReviewsByFilmId(int filmId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ReviewDAO reviewDAO = factory.getReviewDAO();
        try {
            return reviewDAO.getReviewsByFilmId(filmId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<FilmReviewDTO> getReviewsByParameters(String year, String ageRating, String filmType, String []genres, int startFromRecordNumber, String userId, String filmId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        DtoDAO dtoDAO = factory.getDtoDAO();
        try {
            return dtoDAO.getReviewsByParameters(year, ageRating, filmType, genres, startFromRecordNumber, userId, filmId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
