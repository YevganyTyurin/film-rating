package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.service.ReviewApprovalService;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Like implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.Like.class);
    public final String currentURL = "/WEB-INF/jsp/filmDescription.jsp";
    public final String URL = "URL";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter("id"));

        int likeAmount;

        HttpSession session = request.getSession();

        int userId = (Integer)session.getAttribute("userId");

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            ReviewApprovalService reviewApprovalService = instance.getReviewApprovalService();
            ReviewService reviewService = instance.getReviewService();

            likeAmount = reviewService.getLikesAmountById(reviewId);
            ReviewApproval reviewApproval = reviewApprovalService.getReviewApprovalById(userId, reviewId);
            if(reviewApproval != null) {
                if(reviewApproval.isLiked()) {
                    // cancel like
                    reviewApprovalService.updateReviewApprovalLike(false, userId, reviewId);
                    likeAmount--;

                } else {
                    //do like
                    reviewApprovalService.updateReviewApprovalLike(true, userId, reviewId);
                    likeAmount++;
                }
            } else {
                //do instance with like
                reviewApprovalService.addReviewApproval(userId, reviewId, true, false);
                likeAmount++;
            }
            reviewService.updateLikesAmountById(likeAmount, reviewId);

            response.setContentType("text/plain");
            response.getWriter().write(Integer.toString(likeAmount));

        } catch (ServiceException e) {
            logger.error("Exception with updating like.", e);
            //TODO exception page
        }

    }
}
