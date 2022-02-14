package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguage implements Command{
    public final String attributeURL = "URL";
    public final String attributeLocal = "local";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String language = request.getParameter(attributeLocal);
        request.getSession().setAttribute(attributeLocal, language);

        response.sendRedirect(getPreviousRequest(request));
    }

    private String getPreviousRequest(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        url.append("Controller?");

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("command")) {
                url.append(cookie.getValue());

                System.out.println("my URL = " + url.toString());
                break;
            }
        }
        return url.toString();
    }
}
