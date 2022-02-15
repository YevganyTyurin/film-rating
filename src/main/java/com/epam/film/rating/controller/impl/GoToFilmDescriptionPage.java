package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.service.FilmService;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.DtoService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GoToFilmDescriptionPage implements Command {
    /**
     * Go to film description page command.
     */
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.GoToFilmDescriptionPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            ServiceFactory instance = ServiceFactory.getInstance();
            DtoService dtoService = instance.getDtoService();
            FilmService filmService = instance.getFilmService();

            Cookie queryString = new Cookie(Parameter.COMMAND, request.getQueryString());
            response.addCookie(queryString);

            int filmId = Integer.parseInt(request.getParameter(Parameter.FILM_ID));

            request.setAttribute(Parameter.FILM, filmService.getFilmById(filmId));

            setPermissionToReview(request, filmId);

            Cookie filmIdCookie = new Cookie(Parameter.FILM_ID, Integer.toString(filmId));
            response.addCookie(filmIdCookie);

            List<ReviewDTO> reviewsDTO = dtoService.getReviewsByFilmId(filmId);
            request.setAttribute(Parameter.REVIEWS, reviewsDTO);

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.FILM_DESCRIPTION_PAGE);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error(LoggerMessage.GET_REVIEW_BY_FILM_ID_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }

    private void setPermissionToReview(HttpServletRequest request, int filmId) throws ServiceException {
        ServiceFactory instance = ServiceFactory.getInstance();

        ReviewService reviewService = instance.getReviewService();

        HttpSession session = request.getSession();

        int id = (Integer)session.getAttribute(Parameter.USER_ID);

        List<Review> reviews = reviewService.getReviewById(filmId, id);
        if (reviews.isEmpty()) {
            request.setAttribute(Parameter.PERMISSION, Parameter.TRUE);
        } else {
            request.setAttribute(Parameter.PERMISSION, Parameter.FALSE);
        }
    }
}