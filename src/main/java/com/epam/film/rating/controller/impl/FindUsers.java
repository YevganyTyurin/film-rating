package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindUsers implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.FindUsers.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie queryString = new Cookie(Parameter.COMMAND, request.getQueryString());
        response.addCookie(queryString);

        int userId = Integer.parseInt(request.getParameter(Parameter.USER_ID));

        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();

        try {
            User user = userService.findById(userId);
            request.setAttribute(Parameter.USER, user);

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.SHOW_USER_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(LoggerMessage.FINDING_USER_BY_ID_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
