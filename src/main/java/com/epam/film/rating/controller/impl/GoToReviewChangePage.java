package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToReviewChangePage implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.GoToReviewChangePage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie queryString = new Cookie(Parameter.COMMAND, request.getQueryString());
        response.addCookie(queryString);

        ServiceFactory instance = ServiceFactory.getInstance();
        ReviewService reviewService = instance.getReviewService();

        response.setContentType("text/plain");

        int reviewId = Integer.parseInt(request.getParameter(Parameter.REVIEW_ID));

        try{
            request.setAttribute(Parameter.REVIEW, reviewService.getReviewByReviewId(reviewId));

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.REVIEW_CHANGE_PAGE);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error(LoggerMessage.GET_REVIEW_BY_REVIEW_ID_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
