package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateReview implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.UpdateReview.class);
    public final String currentURL = "/WEB-INF/jsp/filmDescription.jsp";
    public final String URL = "URL";
    public final String ID = "id";
    public final String REVIEW = "review";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int reviewId = Integer.parseInt(request.getParameter(ID));
            String review = request.getParameter(REVIEW);

            ServiceFactory instance = ServiceFactory.getInstance();
            ReviewService reviewService = instance.getReviewService();

            reviewService.updateReview(reviewId, review);

        } catch (ServiceException e) {
            logger.error("Exception in changing film review.", e);
            //TODO exception
        }
    }
}
