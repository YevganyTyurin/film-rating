package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateReview implements Command {
    public final String currentURL = "/WEB-INF/jsp/filmDescription.jsp";
    public final String URL = "URL";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        System.out.println("ID = " + id);
        String review = request.getParameter("review");
        System.out.println("REVIEW = " + review);

        //TODO
    }
}
