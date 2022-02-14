package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
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

public class Dislike implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.Dislike.class);
    public final String id = "id";
    public final String userID = "userId";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter(id));
        int dislikesAmount;

        try {
            HttpSession session = request.getSession();
            int userId = (Integer)session.getAttribute(userID);

            ServiceFactory instance = ServiceFactory.getInstance();
            ReviewApprovalService reviewApprovalService = instance.getReviewApprovalService();
            ReviewService reviewService = instance.getReviewService();

            dislikesAmount = reviewService.getDislikesAmountById(reviewId);
            ReviewApproval reviewApproval = reviewApprovalService.getReviewApprovalById(userId, reviewId);
            if(reviewApproval != null) {

                if(reviewApproval.isDisliked()) {
                    reviewApprovalService.updateReviewApprovalDislike(false, userId, reviewId);
                    dislikesAmount--;
                } else {
                    reviewApprovalService.updateReviewApprovalDislike(true, userId, reviewId);
                    dislikesAmount++;

                }
            } else {
                reviewApprovalService.addReviewApproval(userId, reviewId, false, true);
                dislikesAmount++;
            }
            reviewService.updateDislikesAmountById(dislikesAmount, reviewId);

            response.setContentType("text/plain");
            response.getWriter().write(Integer.toString(dislikesAmount));

        } catch (ServiceException e) {
            logger.error("Exception in updating dislikes.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
