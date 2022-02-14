package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.service.FilmService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddFilm implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.AddFilm.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            FilmService filmService = instance.getFilmService();

            String ageRating = request.getParameter(Parameter.AGE_RATING);
            String filmType = request.getParameter(Parameter.TYPE);
            String []genres = request.getParameterValues(Parameter.GENRE);
            String description = request.getParameter(Parameter.FILM_DESCRIPTION);
            String filmName = request.getParameter(Parameter.FILM_NAME);
            int productionYear = Integer.parseInt(request.getParameter(Parameter.PRODUCTION_YEAR));


            int ageRatingId = filmService.getAgeRatingId(ageRating);
            int filmTypeId = filmService.getFilmTypeId(filmType);

            filmService.addFilm(filmName, productionYear, description, ageRatingId, filmTypeId);

            addFilmGenres(genres, filmName, productionYear);

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ADMIN_PAGE);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error(LoggerMessage.ADDING_FILM_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
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
            logger.error(LoggerMessage.ADDING_FILM_GENRES_EXCEPTION, e);
        }
    }
}