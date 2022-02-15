package com.epam.film.rating.dao;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.FilmReviewDTO;
import java.util.List;

public interface DtoDAO {
    List<FilmReviewDTO> getReviewsByParameters(String year, String ageRating, String filmType, String []genres, int amount, String filmId, String userId) throws DAOException;
}
