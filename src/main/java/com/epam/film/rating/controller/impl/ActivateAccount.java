package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ActivateAccount implements Command {
    public final String ID = "id";

    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.ActivateAccount.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            UserService userService = instance.getUserService();

            int userId = Integer.parseInt(request.getParameter(ID));
            userService.updateIsBanned(userId, false);


        } catch (ServiceException e) {
            logger.error("Exception in activating account.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
