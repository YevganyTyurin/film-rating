package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguage implements Command{
    /**
     * Change language command
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String language = request.getParameter(Parameter.LOCAL);
        request.getSession().setAttribute(Parameter.LOCAL, language);

        response.sendRedirect(getPreviousRequest(request));
    }

    private String getPreviousRequest(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        url.append("Controller?");

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Parameter.COMMAND)) {
                url.append(cookie.getValue());
                break;
            }
        }
        return url.toString();
    }
}
