package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.FilmReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindReviewsByParameters implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.FindReviewsByParameters.class);

    public final String parameterUserId = "userId";
    public final String parameterFilmId = "filmId";
    public final String parameterYear = "year";
    public final String parameterAgeRating = "age_rating";
    public final String parameterType = "type";
    public final String parameterGenre = "genre";
    public final String currentURL = "/WEB-INF/jsp/reviewsByParameters.jsp";
    public final String attributeFilms = "films";
    public final String URL = "URL";
    public final String PAGE_NUMBER = "pageNumber";
    public final String PAGE_NUMBERS = "pageNumbers";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            Service service = instance.getService();

            response.setContentType("text/html");

            String userId = request.getParameter(parameterFilmId);
            String filmId = request.getParameter(parameterUserId);
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

//            int filmAmount = service.getFilmAmount(year, age_rating, film_type, genres);
            int filmAmount = 1;
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

//            List<Film> films = service.getFilmsByParameters(year, age_rating, film_type, genres, startFromRecordNumber);

            ReviewDAOImpl reviewDAO = new ReviewDAOImpl(); //TODO
            List<FilmReviewDTO> reviews = reviewDAO.getReviewsByParameters(year, age_rating, film_type, genres, startFromRecordNumber, userId, filmId);
            for (FilmReviewDTO review:reviews) {
                System.out.println(review.getReviewId());
                System.out.println(review.getReview());
                System.out.println(review.getFilmName());
            }

            request.setAttribute("reviews", reviews);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);

        } catch (DAOException e) {
            logger.error("Exception with finding reviews by parameters request.", e);
            //TODO exception page
        }

    }
}
