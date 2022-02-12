package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.service.FilmService;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.DtoService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;
import com.epam.film.rating.service.impl.ReviewServiceImpl;

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
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.GoToFilmDescriptionPage.class);

    public final String currentURL = "/WEB-INF/jsp/filmDescription.jsp";
    public final String parameterId = "id";
    public final String userId = "userId";
    public final String URL = "URL";
    public final String permission = "permission";
    public final String film = "film";
    public final String FILM_ID = "filmId";
    public final String PERMISSION_TRUE = "true";
    public final String PERMISSION_FALSE = "false";
    public final String reviews = "reviews";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            ServiceFactory instance = ServiceFactory.getInstance();
            DtoService dtoService = instance.getDtoService();
            FilmService filmService = instance.getFilmService();

            int filmId = Integer.parseInt(request.getParameter(parameterId));

            request.setAttribute(film, filmService.getFilmById(filmId));

            setPermissionToReview(request, filmId);

            Cookie filmIdCookie = new Cookie(FILM_ID, Integer.toString(filmId));
            response.addCookie(filmIdCookie);

            List<ReviewDTO> ReviewsDTO = dtoService.getReviewsByFilmId(filmId);
            request.setAttribute(reviews, ReviewsDTO);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error("Exception with getting review by film id.", e);
            //TODO exception page
        }
    }

    private void setPermissionToReview(HttpServletRequest request, int filmId) throws ServiceException {
        ServiceFactory instance = ServiceFactory.getInstance();

        ReviewService reviewService = instance.getReviewService();

        HttpSession session = request.getSession();

        int id = (Integer)session.getAttribute(userId);

        List<Review> reviews = reviewService.getReviewById(filmId, id);
        if (reviews.isEmpty()) {
            request.setAttribute(permission, PERMISSION_TRUE);
        } else {
            request.setAttribute(permission, PERMISSION_FALSE);
        }
    }
}