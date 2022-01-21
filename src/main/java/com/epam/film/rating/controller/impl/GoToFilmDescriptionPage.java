package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.ReviewDAO;
import com.epam.film.rating.dao.impl.FilmDAOImpl;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.ReviewDTO;
import com.epam.film.rating.entity.film.Film;
import com.epam.film.rating.entity.review.Review;
import com.epam.film.rating.entity.user.User;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GoToFilmDescriptionPage implements Command {
    public final String currentURL = "/WEB-INF/jsp/filmDescription.jsp";
    public final String parameterId = "id";
    public final String userId = "userId";
    public final String URL = "URL";
    public final String permission = "permission";
    public final String film = "film";
    public final String filmId = "filmId";
    public final String reviews = "reviews";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            ServiceFactory instance = ServiceFactory.getInstance();
            Service service = instance.getService();

            int filmId = Integer.parseInt(request.getParameter(parameterId));

            request.setAttribute(film, service.getFilmById(filmId));

            setPermissionToReview(request, filmId);

            Cookie filmIdCookie = new Cookie("filmId", Integer.toString(filmId));
            response.addCookie(filmIdCookie);

            List<ReviewDTO> ReviewsDTO = service.getReviewsByFilmId(filmId);
            request.setAttribute(reviews, ReviewsDTO);

            RequestDispatcher dispatcher = request.getRequestDispatcher(currentURL);
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setPermissionToReview(HttpServletRequest request, int filmId) throws SQLException {
        ServiceFactory instance = ServiceFactory.getInstance();
        Service service = instance.getService();

        HttpSession session = request.getSession();

        int id = (Integer)session.getAttribute(userId);

        List<Review> reviews = service.getReviewById(filmId, id);
        if (reviews.isEmpty()) {
            request.setAttribute(permission, "true");
        } else {
            request.setAttribute(permission, "false");
        }
    }
}