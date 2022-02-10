package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.service.FilmService;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActivateAccount implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            UserService userService = instance.getUserService();

            int userId = Integer.parseInt(request.getParameter("id"));
            userService.updateIsBanned(userId, false);


        } catch (ServiceException e) {
//            logger.error("Exception in adding film.", e);
//            TODO exception
        }
    }
}
