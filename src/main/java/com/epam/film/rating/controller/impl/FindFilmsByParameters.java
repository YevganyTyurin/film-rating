package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.service.FilmService;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;
import com.epam.film.rating.service.impl.FilmServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindFilmsByParameters implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.FindFilmsByParameters.class);

    public final String parameterYear = "year";
    public final String parameterAgeRating = "age_rating";
    public final String parameterType = "type";
    public final String parameterGenre = "genre";
    public final String currentURL = "/WEB-INF/jsp/filmsByParameters.jsp";
    public final String attributeFilms = "films";
    public final String URL = "URL";
    public final String PAGE_NUMBER = "pageNumber";
    public final String PAGE_NUMBERS = "pageNumbers";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            Service service = instance.getService(); //TODO
            FilmService filmService = new FilmServiceImpl();

            response.setContentType("text/html");

            String year = request.getParameter(parameterYear);
            String age_rating = request.getParameter(parameterAgeRating);
            String film_type = request.getParameter(parameterType);
            String genres[] = request.getParameterValues(parameterGenre);

            int pageNumber;

            if(request.getParameter(PAGE_NUMBER) == null) {
                pageNumber = 1;
                request.setAttribute(URL, request.getQueryString());
            } else {
                pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
                request.setAttribute(URL, request.getQueryString().substring(0, request.getQueryString().lastIndexOf("&")));
            }

//            if(request.getParameter("amountOfRecordsOnPage") == null) {
//                amountOfRecordsOnPage = 1;
//            } else {
//                amountOfRecordsOnPage = Integer.parseInt(request.getParameter("amountOfRecordsOnPage"));
//            }
            int amountOfRecordsOnPage = 2; //TODO

            int startFromRecordNumber = (amountOfRecordsOnPage * pageNumber) - amountOfRecordsOnPage;

            int filmAmount = filmService.getFilmAmount(year, age_rating, film_type, genres);
            int amountOfPages = 0;

            if (filmAmount % amountOfRecordsOnPage >= 1) {
                System.out.println(filmAmount % amountOfRecordsOnPage);
                amountOfPages = filmAmount / amountOfRecordsOnPage;
                amountOfPages++;
            } else {
                amountOfPages = filmAmount / amountOfRecordsOnPage;
            }

            List pageNumbers = new ArrayList();
            for (int i = 1; i <= amountOfPages ; i++) {
                pageNumbers.add(i);
            }

            request.setAttribute(PAGE_NUMBERS, pageNumbers);

            List<Film> films = filmService.getFilmsByParameters(year, age_rating, film_type, genres, startFromRecordNumber);

            request.setAttribute(attributeFilms, films);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error("Exception with finding films by parameters request.", e);
            //TODO exception
        }

    }
}
