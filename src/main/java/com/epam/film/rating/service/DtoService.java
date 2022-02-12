package com.epam.film.rating.service;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.FilmReviewDTO;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.service.exception.ServiceException;
import java.util.List;

public interface DtoService {

    List<ReviewDTO> getReviewsByFilmId(int filmId) throws ServiceException;

    List<FilmReviewDTO> getReviewsByParameters(String year, String ageRating, String filmType, String []genres, int startFromRecordNumber, String userId, String filmId) throws ServiceException;
}
