package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.service.ReviewApprovalService;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteReview implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.DeleteReview.class);

    public final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int reviewId = Integer.parseInt(request.getParameter(ID));

            ServiceFactory instance = ServiceFactory.getInstance();
            ReviewService reviewService = instance.getReviewService();

            reviewService.deleteReview(reviewId);
        } catch (ServiceException e) {
            logger.error("Exception in deleting film review.", e);
            //TODO exception
        }
    }
}
