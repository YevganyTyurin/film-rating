package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.controller.constant.JSPPath;
import com.epam.film.rating.controller.constant.LoggerMessage;
import com.epam.film.rating.controller.constant.Parameter;
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
    /**
     * Update user role command
     */
    private static final Logger logger = LogManager.getLogger(com.epam.film.rating.controller.impl.ChangeUserRole.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roleId;
        int userId = Integer.parseInt(request.getParameter(Parameter.USER_ID));

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            UserService userService = instance.getUserService();

            roleId = userService.getRoleId(userId);
            if(roleId == Role.USER.getId()) {
                userService.updateRole(userId, Role.ADMINISTRATOR.getId());
            } else {
                userService.updateRole(userId, Role.USER.getId());
            }

        } catch (ServiceException e) {
            logger.error(LoggerMessage.CHANGE_ROLE_EXCEPTION, e);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
