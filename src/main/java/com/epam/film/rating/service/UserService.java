package com.epam.film.rating.service;

import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.exception.ServiceException;

public interface UserService {

    boolean validateLogin(String login);

    boolean validatePassword(String password);

    boolean validateNickname(String nickname);

    boolean validateName(String name);

    boolean validateSurname(String surname);

    boolean validatePhoneNumber(String phoneNumber);

    boolean validateEmail(String email);

    boolean validateUserRegistration(User user);

    boolean validateUserLogin(String login, String password);

    User login(String login) throws ServiceException;

    int add (User user) throws ServiceException;

    User findById(int id) throws ServiceException;

    boolean updateIsBanned(int id, boolean isBanned) throws ServiceException;

    boolean isBanned(int id) throws ServiceException;

    boolean updateRole(int userId, int roleId) throws ServiceException;

    int getRoleId (int userId) throws ServiceException;

    boolean activateAccount(String email) throws ServiceException;
}
