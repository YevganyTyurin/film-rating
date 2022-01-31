package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoToMainPage implements Command {
    public final String currentURL = "/WEB-INF/jsp/mainPage.jsp";
    public final String URL = "URL";

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute(URL, currentURL);

        RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
        dispatcher.forward(request, response);
    }
}
