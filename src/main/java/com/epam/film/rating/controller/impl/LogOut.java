package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

public class LogOut implements Command {
    public final String currentURL = "/WEB-INF/jsp/mainPage.jsp";

    public final String IS_BANNED = "isBaned";
    public final String USER_ROLE = "userRole";
    public final String USER_ID = "email";
    public final String URL = "URL";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie queryString = new Cookie("command", request.getQueryString());
        response.addCookie(queryString);
        //TODO flag

        HttpSession session = request.getSession();

        session.removeAttribute(IS_BANNED);
        session.removeAttribute(USER_ROLE);
        session.removeAttribute(USER_ID);
        session.removeAttribute(URL);

        request.getRequestDispatcher(currentURL).forward(request, response);
    }
}
