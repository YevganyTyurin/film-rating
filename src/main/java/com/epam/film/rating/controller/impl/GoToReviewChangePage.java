package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
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

public class GoToReviewChangePage implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.GoToReviewChangePage.class);
    public final String loginURL = "/WEB-INF/jsp/reviewChangePage.jsp";
    public final String URL = "URL";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute(URL, loginURL);

        ServiceFactory instance = ServiceFactory.getInstance();
        ReviewService reviewService = instance.getReviewService();

        response.setContentType("text/plain");

        int reviewId = Integer.parseInt(request.getParameter("id"));

        try{
            request.setAttribute("review", reviewService.getReviewByReviewId(reviewId));

            RequestDispatcher dispatcher = request.getRequestDispatcher(loginURL);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Exception with finding review by review id.", e);
            //TODO exception page
        }
    }
}
