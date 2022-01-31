package com.epam.film.rating.service;

import com.epam.film.rating.dao.DAOFactory;
import com.epam.film.rating.dao.FilmDAO;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.UserDAO;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.dao.impl.UserDAOImpl;
import com.epam.film.rating.service.impl.FilmServiceImpl;
import com.epam.film.rating.service.impl.ReviewApprovalImpl;
import com.epam.film.rating.service.impl.ReviewServiceImpl;
import com.epam.film.rating.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService;
    private final ReviewService reviewService;
    private final FilmService filmService;
    private final ReviewApprovalService reviewApprovalService;

    private final Service service;

    private ServiceFactory() {
        userService = new UserServiceImpl();
        reviewService = new ReviewServiceImpl();
        filmService = new FilmServiceImpl();
        reviewApprovalService = new ReviewApprovalImpl();
        service = new ServiceImpl();
    }

    public ReviewApprovalService getReviewApprovalService() {
        return reviewApprovalService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public FilmService getFilmService() {
        return filmService;
    }

    public UserService getUserService() {
        return userService;
    }

    public Service getService() {
        return service;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
