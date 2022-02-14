package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.*;
import com.epam.film.rating.service.exception.ServiceException;
import com.epam.film.rating.service.util.Password;
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

    public final String login = "login";
    public final String password = "password";
    public final String nickname = "nickname";
    public final String name = "name";
    public final String surname = "surname";
    public final String phoneNumber = "phoneNumber";
    public final String email = "email";

    public final String loginURL = "/WEB-INF/jsp/login.jsp";
    public final String URL = "URL";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("registration");

        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();

        try {

            System.out.println("login = " + request.getParameter(login));

            System.out.println("password = " + request.getParameter(password));

            System.out.println("nickname = " + request.getParameter(nickname));

            System.out.println("name = " + request.getParameter(name));

            System.out.println("surname = " + request.getParameter(surname));

            System.out.println("number = " + request.getParameter(phoneNumber));

            System.out.println("email = " + request.getParameter(email));

            User user = new User();
            user.setLogin(request.getParameter(login));
            user.setPassword(request.getParameter(password));

            Password.hashPassword(request.getParameter(password));
            System.out.println("passport hash = " + Password.hashPassword(request.getParameter(password)));
            user.setNickname(request.getParameter(nickname));
            user.setName(request.getParameter(name));
            user.setSurname(request.getParameter(surname));
            user.setPhoneNumber(request.getParameter(phoneNumber));
            user.seteMail(request.getParameter(email));

            System.out.println("hello! anybody is here?");

            if(userService.validateUserRegistration(user)) {
                user.setPassword(Password.hashPassword(request.getParameter(password)));
//                userService.add(user);
                //TODO code
                if (userService.add(user) != 0) {
                    System.out.println("we are here inside IF");
//                    SendingEmail se = new SendingEmail(user.geteMail());
                    System.out.println("we try to send mail");
//                    se.sendMail();
                }
            }

            UserValidator userValidator = new UserValidator();

            userValidator.validateLogin(request.getParameter(login));
            userValidator.validatePassword(request.getParameter(password));
            userValidator.validateNickname(request.getParameter(nickname));
            userValidator.validateName(request.getParameter(name));
            userValidator.validateSurname(request.getParameter(surname));
            userValidator.validatePhoneNumber(request.getParameter(phoneNumber));
            userValidator.validateEmail(request.getParameter(email));

            RequestDispatcher dispatcher = request.getRequestDispatcher(loginURL);
            dispatcher.forward(request, response);

        } catch (ServiceException e) {
            logger.error("Exception in registration.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}

