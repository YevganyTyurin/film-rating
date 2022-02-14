package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class GoToLoginPage implements Command {
    public final String loginURL = "/WEB-INF/jsp/login.jsp";
    public final String URL = "URL";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        session.setAttribute(URL, loginURL);

        Cookie queryString = new Cookie("command", request.getQueryString());
        response.addCookie(queryString);

        RequestDispatcher dispatcher = request.getRequestDispatcher(loginURL);
        dispatcher.forward(request, response);
    }
}
