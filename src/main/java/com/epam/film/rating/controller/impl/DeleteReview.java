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

public class DeleteReview implements Command {

    public final String id = "id";
    public final String userID = "userId";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter(id));

        System.out.println("HERE IS REVIEW ID + " + reviewId);

        //TODO
    }
}
