package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LeaveReview implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.LeaveReview.class);

    public final String REVIEW_TEXT = "reviewText";
    public final String FILL_MARK = "filmMark";
    public final String FILM_ID = "filmId";
    public final String USER_ID = "userId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String reviewText = request.getParameter(REVIEW_TEXT).trim();
        int filmMark = Integer.parseInt(request.getParameter(FILL_MARK));

        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute(USER_ID);

        String filmId = getFilmIdFromCookie(request, FILM_ID);

        Review review = new Review();
        review.setReview(reviewText);
        review.setMark(filmMark);
        review.setUserId(userId);

        String result = null;
        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            ReviewService service = instance.getReviewService();
            if(service.addReview(review, Integer.parseInt(filmId))) {
                result = "success";
            } else {
                result = "not success";
            }

            response.setContentType("text/plain");
            response.getWriter().write(result);
        } catch (ServiceException e) {
            logger.error("Exception with leaving review.", e);
            //TODO exception page
        }

    }

    private String getFilmIdFromCookie(HttpServletRequest request, String parameter) {
        Cookie[] cookies = request.getCookies();

        String parameterValue = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(parameter)) {
                parameterValue = cookie.getValue();
                break;
            }
        }
        return parameterValue;
    }
}