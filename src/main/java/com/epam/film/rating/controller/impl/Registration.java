package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.*;
import com.epam.film.rating.service.exception.ServiceException;
import com.epam.film.rating.service.util.Password;
import com.epam.film.rating.service.util.SendingEmail;
import com.epam.film.rating.service.validator.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.Registration.class);

    public final String LOGIN = "login";
    public final String PASSWORD = "password";
    public final String NICKNAME = "nickname";
    public final String NAME = "name";
    public final String SURNAME = "surname";
    public final String PHONE_NUMBER = "phoneNumber";
    public final String EMAIL = "email";

    public final String loginURL = "/WEB-INF/jsp/login.jsp";
    public final String URL = "URL";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("registration");

        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();

        try {
            User user = new User();
            user.setLogin(request.getParameter(LOGIN));
            user.setPassword(request.getParameter(PASSWORD));
            user.setNickname(request.getParameter(NICKNAME));
            user.setName(request.getParameter(NAME));
            user.setSurname(request.getParameter(SURNAME));
            user.setPhoneNumber(request.getParameter(PHONE_NUMBER));
            user.seteMail(request.getParameter(EMAIL));

            if(userService.validateUserRegistration(user)) {
                user.setPassword(Password.hashPassword(request.getParameter(PASSWORD)));
//                userService.add(user);
                //TODO code
                if (userService.add(user) != 0) {
                    SendingEmail se = new SendingEmail(user.geteMail());
                    se.sendMail();
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(loginURL);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error("Exception in registration.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}

