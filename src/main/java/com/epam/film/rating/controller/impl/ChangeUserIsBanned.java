package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserIsBanned implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeUserIsBanned.class);
    public final String id = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter(id));

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            UserService userService = instance.getUserService();
            boolean isBanned = userService.isBanned(userId);
            userService.updateIsBanned(userId, !isBanned);
        } catch (ServiceException e) {
            logger.error("Exception in baning/unbanning user.", e);
            //TODO exception
        }
    }

}
