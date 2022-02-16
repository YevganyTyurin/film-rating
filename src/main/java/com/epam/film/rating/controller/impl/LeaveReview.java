package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.entity.review.Review;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LeaveReview implements Command {
    /**
     * Add film review command.
     */
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.LeaveReview.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        int filmMark;
        int userId;
        String reviewText = request.getParameter(Parameter.REVIEW_TEXT).trim();
        filmMark = Integer.parseInt(request.getParameter(Parameter.FILM_MARK));

        HttpSession session = request.getSession();
        userId = (Integer)session.getAttribute(Parameter.USER_ID);

        String filmId = getParameterFromCookie(request, Parameter.FILM_ID);

        Review review = new Review();
        review.setReview(reviewText);
        review.setMark(filmMark);
        review.setUserId(userId);

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            ReviewService service = instance.getReviewService();
            service.addReview(review, Integer.parseInt(filmId));

        } catch (ServiceException e) {
            logger.error(LoggerMessage.LEAVING_REVIEW_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }

    }

    private String getParameterFromCookie(HttpServletRequest request, String parameter) {
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