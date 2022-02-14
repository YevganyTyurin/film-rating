package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToAdminPage implements Command {
    public final String currentURL = "/WEB-INF/jsp/adminmainpage.jsp";
    public final String URL = "URL";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie queryString = new Cookie("command", request.getQueryString());
        response.addCookie(queryString);
        //TODO flag

//        HttpSession session = request.getSession();
//        session.setAttribute(URL, currentURL);

        RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
        dispatcher.forward(request, response);
    }
}
