package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindUsers implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.FindUsers.class);

    public final String URL = "URL";
    public final String USER_ID = "userId";
    public final String nickname = "userNickname";
    public final String currentURL = "/WEB-INF/jsp/showUserPage.jsp";
    public final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        session.setAttribute(URL, currentURL);

        Cookie queryString = new Cookie("command", request.getQueryString());
        response.addCookie(queryString);
        //TODO flag

        int userId = Integer.parseInt(request.getParameter(USER_ID));

        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();

        try {
            User user = userService.findById(userId);
            System.out.println(user.isBanned());
            request.setAttribute(USER, user);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Exception with finding user by id.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
