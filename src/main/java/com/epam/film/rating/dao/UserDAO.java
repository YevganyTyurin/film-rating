package com.epam.film.rating.dao;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.user.User;

public interface UserDAO {
    int add(User user) throws DAOException;

    User login(String login) throws DAOException;

    User findById(int id) throws DAOException;

    boolean isBanned(int id) throws DAOException;

    boolean updateIsBanned(int id, boolean isBanned) throws DAOException;

    boolean updateRole(int userId, int roleId) throws DAOException;

    int getRoleId (int userId) throws DAOException;

    boolean activateAccount(String email) throws DAOException;
}
