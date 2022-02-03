package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.FilmDAO;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.service.FilmService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddFilm implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.AddFilm.class);
    public final String parameterYear = "year";
    public final String parameterAgeRating = "age_rating";
    public final String parameterType = "type";
    public final String parameterGenre = "genre";
    public final String year = "productionYear";
    public final String filmDescription = "description";
    public final String name = "filmName";

    public final String id = "id";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            FilmService filmService = instance.getFilmService();

            String age_rating = request.getParameter(parameterAgeRating);
            String filmType = request.getParameter(parameterType);
            String genres[] = request.getParameterValues(parameterGenre);
            String description = request.getParameter(filmDescription);
            String filmName = request.getParameter(name);
            int productionYear = Integer.parseInt(request.getParameter(year));


            int ageRatingId = filmService.getAgeRatingId(age_rating);
            int filmTypeId = filmService.getFilmTypeId(filmType);

            filmService.addFilm(filmName, productionYear, description, ageRatingId, filmTypeId);

            addFilmGenres(genres, filmName, productionYear);

        } catch (ServiceException e) {
            logger.error("Exception in adding film.", e);
//            TODO exception
        }
    }

    private void addFilmGenres (String[] genres, String filmName, int productionYear) {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            FilmService filmService = instance.getFilmService();

            int filmId = filmService.getFilmId(filmName, productionYear);

            if(genres != null) {
                for (String genre : genres) {
                    filmService.addFilmGenre(filmId, filmService.getGenreId(genre));
                }
            }
        } catch (ServiceException e) {
            logger.error("Exception in adding film genres relations.", e);
//            TODO exception
        }


    }
}
