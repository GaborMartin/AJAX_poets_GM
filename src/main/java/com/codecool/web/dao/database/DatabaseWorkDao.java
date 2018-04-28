package com.codecool.web.dao.database;

import com.codecool.web.dao.WorkDao;
import com.codecool.web.model.Work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWorkDao extends AbstractDao implements WorkDao {

    DatabaseWorkDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Work> getAllWorksForPoetById(int id) throws SQLException {
        List<Work> works = new ArrayList<>();
        String sql = "SELECT id, title, content, published_date " +
                "FROM works " +
                "WHERE poet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    works.add(fetchWork(resultSet));
                }
            }
        }
        return works;
    }

    @Override
    public Work getWorkForPoetById(int poetId, int workId) throws SQLException {
        String sql = "SELECT id, title, content, published_date " +
                "FROM works " +
                "WHERE id = ? AND poet_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workId);
            statement.setInt(2, poetId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchWork(resultSet);
                }
                return null;
            }
        }
    }

    private Work fetchWork(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        int publishedDate = resultSet.getInt("published_date");
        return new Work(id, title, content, publishedDate);
    }
}
