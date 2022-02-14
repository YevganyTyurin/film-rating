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

import javax.servlet.RequestDispatcher;
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
    public final String PRODUCTION_YEAR = "productionYear";
    public final String AGE_RATING = "age_rating";
    public final String TYPE = "type";
    public final String GENRE = "genre";
    public final String FILM_DESCRIPTION = "description";
    public final String FILM_NAME = "filmName";

    public final String currentURL = "/WEB-INF/jsp/adminmainpage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            FilmService filmService = instance.getFilmService();

            String ageRating = request.getParameter(AGE_RATING);
            String filmType = request.getParameter(TYPE);
            String genres[] = request.getParameterValues(GENRE);
            String description = request.getParameter(FILM_DESCRIPTION);
            String filmName = request.getParameter(FILM_NAME);
            int productionYear = Integer.parseInt(request.getParameter(PRODUCTION_YEAR));


            int ageRatingId = filmService.getAgeRatingId(ageRating);
            int filmTypeId = filmService.getFilmTypeId(filmType);

            filmService.addFilm(filmName, productionYear, description, ageRatingId, filmTypeId);

            addFilmGenres(genres, filmName, productionYear);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error("Exception in adding film.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
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
            logger.error("Exception in adding film genres relations.", e);
        }


    }
}
