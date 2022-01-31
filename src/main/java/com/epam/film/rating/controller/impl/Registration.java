package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.*;
import com.epam.film.rating.service.exception.ServiceException;
import com.epam.film.rating.service.impl.UserServiceImpl;
import com.epam.film.rating.service.validator.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        ServiceFactory instance = ServiceFactory.getInstance();
        UserService userService = instance.getUserService();

        try {

            User user = new User();
            user.setLogin(request.getParameter(login));
            user.setPassword(request.getParameter(password));
            user.setNickname(request.getParameter(nickname));
            user.setName(request.getParameter(name));
            user.setSurname(request.getParameter(surname));
            user.setPhoneNumber(request.getParameter(phoneNumber));
            user.seteMail(request.getParameter(email));

            if(userService.validateUser(user)) {
                userService.add(user);
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
            //TODO exception page
        }
    }
}

