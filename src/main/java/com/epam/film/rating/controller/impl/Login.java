package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.user.Role;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.Service;
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
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Login implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.Login.class);

    public final String parameterLogin = "login";
    public final String parameterPassword = "password";
    public final String adminPageURL = "/WEB-INF/jsp/adminmainpage.jsp";
    public final String userPageURL = "/WEB-INF/jsp/userMainPage.jsp";
    public final String mainPageURL = "/WEB-INF/jsp/mainPage.jsp";
    public final String userRoleAttribute = "userRole";
    public final String userIdAttribute = "userId";

    public final String userURL = "/WEB-INF/jsp/userMainPage.jsp";
    public final String adminURL = "/WEB-INF/jsp/adminmainpage.jsp";
    public final String URL = "URL";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(parameterLogin);
        String password = request.getParameter(parameterPassword);

        ServiceFactory instance = ServiceFactory.getInstance();
        UserService service = instance.getUserService();


        try {
            User user = service.login(login, password);

            if(user != null) {
                HttpSession session = request.getSession();

                session.setAttribute(userIdAttribute, user.getId());
                session.setAttribute(userRoleAttribute, user.getRole());

                if (user.getRole().equals(Role.USER)) {
                    user = null;
                    session.setAttribute(URL, userURL);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(userPageURL);
                    dispatcher.forward(request, response);

                } else if (user.getRole().equals(Role.ADMINISTRATOR)) {
                    user = null;
                    session.setAttribute(URL, adminURL);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(adminPageURL);
                    dispatcher.forward(request, response);
                }
            }
        } catch (ServiceException e) {
            logger.error("Exception with leaving review.", e);
            //TODO exception page
        }
    }
}
