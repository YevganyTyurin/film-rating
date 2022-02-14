package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.FilmReviewDTO;
import com.epam.film.rating.service.DtoService;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FindReviewsByParameters implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.FindReviewsByParameters.class);

    public final String USER_ID = "userId";
    public final String FILM_ID = "filmId";
    public final String PRODUCTION_YEAR = "productionYear";
    public final String parameterAgeRating = "age_rating";
    public final String parameterType = "type";
    public final String parameterGenre = "genre";
    public final String currentURL = "/WEB-INF/jsp/reviewsByParameters.jsp";
    public final String attributeFilms = "films";
    public final String URL = "URL";
    public final String PAGE_NUMBER = "pageNumber";
    public final String PAGE_NUMBERS = "pageNumbers";

    private static final int AMOUNT_OF_RECORDS_ON_PAGE = 2;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Integer> pageNumbers;
            List<FilmReviewDTO> reviews;
            int pageNumber;
            int reviewAmount;
            int startFromRecordNumber;
            int amountOfPages = 0;
            ServiceFactory instance = ServiceFactory.getInstance();
            ReviewService reviewService = instance.getReviewService();
            DtoService dtoService = instance.getDtoService();

            Cookie queryString = new Cookie("command", request.getQueryString());
            response.addCookie(queryString);

            response.setContentType("text/html");

            String userId = request.getParameter(FILM_ID);
            String filmId = request.getParameter(USER_ID);
            String year = request.getParameter(PRODUCTION_YEAR);
            String ageRating = request.getParameter(parameterAgeRating);
            String filmType = request.getParameter(parameterType);
            String []genres = request.getParameterValues(parameterGenre);

            if(request.getParameter(PAGE_NUMBER) == null) {
                pageNumber = 1;
                request.setAttribute(URL, request.getQueryString());
            } else {
                pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
                request.setAttribute(URL, request.getQueryString().substring(0, request.getQueryString().lastIndexOf("&")));
            }

            startFromRecordNumber = (AMOUNT_OF_RECORDS_ON_PAGE * pageNumber) - AMOUNT_OF_RECORDS_ON_PAGE;

            reviewAmount = reviewService.getReviewAmount(year, ageRating, filmType, genres, userId, filmId);

            if (reviewAmount % AMOUNT_OF_RECORDS_ON_PAGE >= 1) {
                amountOfPages = reviewAmount / AMOUNT_OF_RECORDS_ON_PAGE;
                amountOfPages++;
            } else {
                amountOfPages = reviewAmount / AMOUNT_OF_RECORDS_ON_PAGE;
            }

            pageNumbers = new LinkedList<>();
            for (int i = 1; i <= amountOfPages ; i++) {
                pageNumbers.add(i);
            }

            request.setAttribute(PAGE_NUMBERS, pageNumbers);

            reviews = dtoService.getReviewsByParameters(year, ageRating, filmType, genres, startFromRecordNumber, userId, filmId);

            request.setAttribute("reviews", reviews);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error("Exception with finding reviews by parameters request.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}