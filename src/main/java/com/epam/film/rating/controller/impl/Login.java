package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.user.Role;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.epam.film.rating.service.util.Password;
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
        UserService userService = instance.getUserService();

        try {
            if(userService.validateUserLogin(login, password)) {
                //throw ex
            }
            User user = userService.login2(login);

            boolean isCheckedPassword = Password.checkPassword(password, user.getPassword());

            if(user != null && isCheckedPassword) {
                HttpSession session = request.getSession();

                session.setAttribute(userIdAttribute, user.getId());
                session.setAttribute(userRoleAttribute, user.getRole());
                session.setAttribute("isBanned", user.isBanned());

                if (user.getRole().equals(Role.USER)) {
                    user = null;
                    session.setAttribute(URL, userURL);
                    response.sendRedirect("Controller?command=goToMainPage");

                } else if (user.getRole().equals(Role.ADMINISTRATOR)) {
                    user = null;
                    session.setAttribute(URL, adminURL);
                    response.sendRedirect("Controller?command=goToAdminPage");
                }
            }

        } catch (ServiceException e) {
            logger.error("Exception with leaving review.", e);
            //TODO exception page
        }
    }
}
