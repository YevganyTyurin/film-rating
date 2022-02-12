package com.epam.film.rating.dao;

import com.epam.film.rating.dao.impl.*;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();
    private final UserDAO userDAO;
    private final FilmDAO filmDAO;
    private final ReviewDAO reviewDAO;
    private final ReviewApprovalDAO reviewApprovalDAO;
    private final DtoDAO dtoDAO;

    private DAOFactory(){
        userDAO = new UserDAOImpl();
        filmDAO = new FilmDAOImpl();
        reviewDAO = new ReviewDAOImpl();
        reviewApprovalDAO = new ReviewApprovalDAOImpl();
        dtoDAO = new DtoDAOImpl();
    }

    public ReviewApprovalDAO getReviewApprovalDAO() {return reviewApprovalDAO;}

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public FilmDAO getFilmDAO() {
        return filmDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public DtoDAO getDtoDAO() {
        return dtoDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}