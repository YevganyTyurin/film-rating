package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
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

    private static final int AMOUNT_OF_RECORDS_ON_PAGE = 2;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int pageNumber;
            int filmAmount;
            List<Integer> pageNumbers;
//            int amountOfPages = 0;
            int amountOfPages;
            int startFromRecordNumber;

            Cookie queryString = new Cookie(Parameter.COMMAND, request.getQueryString());
            response.addCookie(queryString);

            ServiceFactory instance = ServiceFactory.getInstance();
            FilmService filmService = instance.getFilmService();

            response.setContentType("text/html");

            String year = request.getParameter(Parameter.PRODUCTION_YEAR);
            String ageRating = request.getParameter(Parameter.AGE_RATING);
            String filmType = request.getParameter(Parameter.TYPE);
            String []genres = request.getParameterValues(Parameter.GENRE);

            if(request.getParameter(Parameter.PAGE_NUMBER) == null) {
                pageNumber = 1;
                request.setAttribute(Parameter.URL, request.getQueryString());
            } else {
                pageNumber = Integer.parseInt(request.getParameter(Parameter.PAGE_NUMBER));
                request.setAttribute(Parameter.URL, request.getQueryString().substring(0, request.getQueryString().lastIndexOf("&")));
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

            request.setAttribute(Parameter.PAGE_NUMBERS, pageNumbers);

            List<Film> films = filmService.getFilmsByParameters(year, ageRating, filmType, genres, startFromRecordNumber);

            request.setAttribute(Parameter.FILMS, films);

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.FILMS_BY_PARAMETERS);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error(LoggerMessage.FINDING_FILMS_BY_PARAMETERS_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}