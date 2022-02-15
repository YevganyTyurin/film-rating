package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements Command {
    /**
     * Logout command. Delete all attributes from session except language
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie queryString = new Cookie(Parameter.COMMAND, request.getQueryString());
        response.addCookie(queryString);

        HttpSession session = request.getSession();

        session.removeAttribute(Parameter.IS_BANNED);
        session.removeAttribute(Parameter.USER_ROLE);
        session.removeAttribute(Parameter.USER_ID);
        session.removeAttribute(Parameter.URL);

        request.getRequestDispatcher(JSPPath.MAIN_PAGE).forward(request, response);
    }
}
