package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.service.ReviewApprovalService;
import com.epam.film.rating.service.ReviewService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Like implements Command {
    /**
     * Update like amount command. Give updated amount of likes in response
     */

    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.Like.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int likeAmount;
        int userId;
        int reviewId = Integer.parseInt(request.getParameter(Parameter.REVIEW_ID));

        HttpSession session = request.getSession();

        userId = (Integer)session.getAttribute(Parameter.USER_ID);

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
            logger.error(LoggerMessage.UPDATE_LIKES_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }

    }
}
