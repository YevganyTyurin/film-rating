package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.entity.user.Role;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.epam.film.rating.service.util.Password;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Login implements Command {
    /**
     * Login command. Put id, isBanned and role in session as attribute
     */
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.Login.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);

        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();

        try {
            if(userService.validateUserLogin(login, password)) {
                //throw ex
            }
            User user = userService.login(login);

            boolean isCheckedPassword = Password.checkPassword(password, user.getPassword());

            if(user != null && isCheckedPassword) {
                HttpSession session = request.getSession();

                session.setAttribute(Parameter.USER_ID, user.getId());
                session.setAttribute(Parameter.USER_ROLE, user.getRole());
                session.setAttribute(Parameter.IS_BANNED, user.isBanned());

                if (user.getRole().equals(Role.USER) && !user.isBanned()) {
                    user = null;
                    session.setAttribute(Parameter.URL, JSPPath.USER_PAGE);
                    response.sendRedirect("Controller?command=goToMainPage");

                } else if (user.getRole().equals(Role.ADMINISTRATOR) && !user.isBanned()) {
                    user = null;
                    session.setAttribute(Parameter.URL, JSPPath.ADMIN_PAGE);
                    response.sendRedirect("Controller?command=goToAdminPage");
                } else {
                    user = null;
                    session.setAttribute(Parameter.URL, JSPPath.MAIN_PAGE);
                    response.sendRedirect("Controller?command=goToMainPage");
                }
            }

        } catch (ServiceException e) {
            logger.error(LoggerMessage.LOGIN_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
