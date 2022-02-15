package com.epam.film.rating.dao;

import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.entity.user.User;
import org.junit.Assert;
import org.junit.Test;

public class UserDAOTest {

    @Test
    public void getUserIsBannedById() throws DAOException{
        UserDAO userDAO = new UserDAOImpl();
        Assert.assertFalse(userDAO.isBanned(9));
    }

    @Test
    public void updateUserRoleToAdminById() throws DAOException{
        UserDAO userDAO = new UserDAOImpl();
        Assert.assertTrue(userDAO.updateRole(9, 1));
    }

    @Test
    public void updateAdminRoleToUserById() throws DAOException{
        UserDAO userDAO = new UserDAOImpl();
        Assert.assertTrue(userDAO.updateRole(9, 2));
    }

    @Test
    public void addUser() throws DAOException{
        UserDAO userDAO = new UserDAOImpl();
        User user = new User();
        user.seteMail("testemail@test.com");
        user.setPhoneNumber("+375256399515");
        user.setLogin("testLogin");
        user.setPassword("asd12345");
        user.setNickname("testNickname");
        user.setName("testName");
        user.setSurname("testSurname");

        Assert.assertEquals(userDAO.add(user),1);
    }
}