package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class LogOut implements Command {
    public final String currentURL = "/WEB-INF/jsp/mainPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            session.removeAttribute(attributes.nextElement());
        }

        request.getRequestDispatcher(currentURL).forward(request, response);

        request.getRequestDispatcher(currentURL).forward(request, response);
    }
}
