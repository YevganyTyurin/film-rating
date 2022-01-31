package com.epam.film.rating.service.impl;

import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.UserService;
import com.epam.film.rating.service.exception.ServiceException;
import com.epam.film.rating.service.validator.UserValidator;

public class UserServiceImpl implements UserService{
    UserValidator userValidator = new UserValidator();

    @Override
    public boolean validateLogin(String login) {
        return userValidator.validateLogin(login);
    }

    @Override
    public boolean validatePassword(String password) {
        return userValidator.validatePassword(password);
    }

    @Override
    public boolean validateNickname(String nickname) {
        return userValidator.validateNickname(nickname);
    }

    @Override
    public boolean validateName(String name) {
        return userValidator.validateName(name);
    }

    @Override
    public boolean validateSurname(String surname) {
        return userValidator.validateSurname(surname);
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return userValidator.validatePhoneNumber(phoneNumber);
    }

    @Override
    public boolean validateEmail(String email) {
        return userValidator.validateEmail(email);
    }

    @Override
    public boolean validateUser (User user) {
        return userValidator.validateUser(user);
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.login(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int add (User user) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.add(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    };
}
