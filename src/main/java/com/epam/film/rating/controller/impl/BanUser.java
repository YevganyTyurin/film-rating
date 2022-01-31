package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BanUser implements Command {
    public final String id = "id";
    public final String userID = "userId";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter(id));

        System.out.println("HERE IS USER ID + " + userId);
        //TODO

    }
}
