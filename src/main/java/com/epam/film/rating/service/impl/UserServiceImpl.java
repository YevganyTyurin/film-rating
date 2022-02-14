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
    public boolean validateUserRegistration(User user) {
        return userValidator.validateUserRegistration(user);
    }

    @Override
    public boolean validateUserLogin(String login, String password) {
        return userValidator.validateUserLogin(login, password);
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
    public User login2(String login) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.login2(login);
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
    }

    @Override
    public boolean updateIsBanned(int id, boolean isBanned) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.updateIsBanned(id, isBanned);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean activateAccount(String email) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.activateAccount(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isBanned(int id) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.isBanned(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateRole(int userId, int roleId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.updateRole(userId, roleId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getRoleId (int userId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.getRoleId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
