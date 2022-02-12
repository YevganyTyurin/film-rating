package com.epam.film.rating.dao;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.FilmReviewDTO;
import java.util.List;

public interface DtoDAO {
    List<FilmReviewDTO> getReviewsByParameters(String year, String age_rating, String film_type, String genres[], int amount, String filmId, String userId) throws DAOException;
}
