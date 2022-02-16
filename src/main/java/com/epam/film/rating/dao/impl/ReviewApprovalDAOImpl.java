package com.epam.film.rating.dao.impl;

import com.epam.film.rating.connectionpool.ConnectionPool;
import com.epam.film.rating.dao.ReviewApprovalDAO;
import com.epam.film.rating.dao.builder.InstanceBuilder;
import com.epam.film.rating.dao.exception.DAOException;
import com.epam.film.rating.entity.review.ReviewApproval;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewApprovalDAOImpl implements ReviewApprovalDAO {
    public static String GET_REVIEW_APPROVAL = "SELECT user_review_approval.is_liked, user_review_approval.is_disliked FROM user_review_approval WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";
    public static String UPDATE_REVIEW_APPROVAL_LIKE = "update user_review_approval set user_review_approval.is_liked=? WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";
    public static String UPDATE_REVIEW_APPROVAL_DISLIKE = "update user_review_approval set user_review_approval.is_disliked=? WHERE user_review_approval.users_id=? AND user_review_approval.review_id=?;";
    public static String ADD_REVIEW_APPROVAL = "insert into user_review_approval (users_id, review_id, is_liked, is_disliked) values(?, ?, ?, ?);";

    ConnectionPool connectable = ConnectionPool.getInstance();

    @Override
    public ReviewApproval getReviewApprovalById(int userId, int reviewId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(GET_REVIEW_APPROVAL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, reviewId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return InstanceBuilder.buildReviewApproval(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return null;
    }

    @Override
    public boolean updateReviewApprovalLike(boolean isLiked, int userId, int reviewId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_REVIEW_APPROVAL_LIKE);
            preparedStatement.setBoolean(1, isLiked);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    @Override
    public boolean updateReviewApprovalDislike(boolean isDisliked, int userId, int reviewId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectable.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_REVIEW_APPROVAL_DISLIKE);
            preparedStatement.setBoolean(1, isDisliked);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, reviewId);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(preparedStatement, connection);
        }
        return false;
    }

    @Override
    public boolean addReviewApproval (int userId, int reviewId, boolean isLiked, boolean isDisliked) throws DAOException {
        Connection connection = connectable.getConnection();
        PreparedStatement pr = null;

        try{
            pr = connection.prepareStatement(ADD_REVIEW_APPROVAL);

            pr.setInt(1, userId);
            pr.setInt(2, reviewId);
            pr.setBoolean(3, isLiked);
            pr.setBoolean(4, isDisliked);

            if(pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            connectable.closeConnection(pr, connection);
        }
        return false;
    }
}
