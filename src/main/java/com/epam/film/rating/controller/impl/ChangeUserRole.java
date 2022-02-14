package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.user.Role;
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

public class ChangeUserRole implements Command {
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.ChangeUserRole.class);
    public final String id = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter(id));

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            UserService userService = instance.getUserService();

            int roleId = userService.getRoleId(userId);
            if(roleId == Role.USER.getId()) {
                userService.updateRole(userId, Role.ADMINISTRATOR.getId());
            } else {
                userService.updateRole(userId, Role.USER.getId());
            }

        } catch (ServiceException e) {
            logger.error("Exception in changing user role.", e);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
