package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.service.FilmService;
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

public class FindFilmsByParameters implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.FindFilmsByParameters.class);

    public final String PRODUCTION_YEAR = "productionYear";
    public final String currentURL = "/WEB-INF/jsp/filmsByParameters.jsp";
    public final String ATTRIBUTE_FILMS = "films";
    public final String URL = "URL";
    public final String PAGE_NUMBER = "pageNumber";
    public final String ATTRIBUTE_PAGE_NUMBERS = "pageNumbers";

    public final String AGE_RATING = "age_rating";
    public final String TYPE = "type";
    public final String GENRE = "genre";

    private static final int AMOUNT_OF_RECORDS_ON_PAGE = 2;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int pageNumber;
            int filmAmount;
            List<Integer> pageNumbers;
            int amountOfPages = 0;
            int startFromRecordNumber;

            Cookie queryString = new Cookie("command", request.getQueryString());
            response.addCookie(queryString);
            //TODO flag

            ServiceFactory instance = ServiceFactory.getInstance();
            FilmService filmService = instance.getFilmService();

            response.setContentType("text/html");

            String year = request.getParameter(PRODUCTION_YEAR);
            String ageRating = request.getParameter(AGE_RATING);
            String filmType = request.getParameter(TYPE);
            String []genres = request.getParameterValues(GENRE);

            if(request.getParameter(PAGE_NUMBER) == null) {
                pageNumber = 1;
                request.setAttribute(URL, request.getQueryString());
            } else {
                pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
                request.setAttribute(URL, request.getQueryString().substring(0, request.getQueryString().lastIndexOf("&")));
            }

            startFromRecordNumber = (AMOUNT_OF_RECORDS_ON_PAGE * pageNumber) - AMOUNT_OF_RECORDS_ON_PAGE;

            filmAmount = filmService.getFilmAmount(year, ageRating, filmType, genres);

            if (filmAmount % AMOUNT_OF_RECORDS_ON_PAGE >= 1) {
                amountOfPages = filmAmount / AMOUNT_OF_RECORDS_ON_PAGE;
                amountOfPages++;
            } else {
                amountOfPages = filmAmount / AMOUNT_OF_RECORDS_ON_PAGE;
            }

            pageNumbers = new LinkedList<>();
            for (int i = 1; i <= amountOfPages ; i++) {
                pageNumbers.add(i);
            }

            request.setAttribute(ATTRIBUTE_PAGE_NUMBERS, pageNumbers);

            List<Film> films = filmService.getFilmsByParameters(year, ageRating, filmType, genres, startFromRecordNumber);

            request.setAttribute(ATTRIBUTE_FILMS, films);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error("Exception with finding films by parameters request.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}