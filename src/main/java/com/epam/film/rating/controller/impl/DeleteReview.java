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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteReview implements Command {
    /**
     * Delete review command.
     */
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.DeleteReview.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int reviewId = Integer.parseInt(request.getParameter(Parameter.REVIEW_ID));

            ServiceFactory instance = ServiceFactory.getInstance();
            ReviewService reviewService = instance.getReviewService();

            reviewService.deleteReview(reviewId);
        } catch (ServiceException e) {
            logger.error(LoggerMessage.DELETE_FILM_REVIEW_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
