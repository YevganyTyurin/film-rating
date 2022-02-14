package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.*;
import com.epam.film.rating.service.exception.ServiceException;
import com.epam.film.rating.service.util.Password;
import com.epam.film.rating.service.util.SendingEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.Registration.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();

        try {
            User user = new User();
            user.setLogin(request.getParameter(Parameter.LOGIN));
            user.setPassword(request.getParameter(Parameter.PASSWORD));
            user.setNickname(request.getParameter(Parameter.NICKNAME));
            user.setName(request.getParameter(Parameter.NAME));
            user.setSurname(request.getParameter(Parameter.SURNAME));
            user.setPhoneNumber(request.getParameter(Parameter.PHONE_NUMBER));
            user.seteMail(request.getParameter(Parameter.EMAIL));

            if(userService.validateUserRegistration(user)) {
                user.setPassword(Password.hashPassword(request.getParameter(Parameter.PASSWORD)));

                if (userService.add(user) != 0) {
                    SendingEmail se = new SendingEmail(user.geteMail());
                    se.sendMail();
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.LOGIN_PAGE);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error(LoggerMessage.REGISTRATION_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}

