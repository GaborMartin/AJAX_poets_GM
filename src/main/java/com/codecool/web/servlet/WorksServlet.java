package com.codecool.web.servlet;


import com.codecool.web.dao.WorkDao;
import com.codecool.web.dao.database.DatabaseWorkDao;
import com.codecool.web.model.Poet;
import com.codecool.web.model.Work;
import com.codecool.web.service.WorkService;
import com.codecool.web.service.simple.SimpleWorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/works")
public class WorksServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            HttpSession session = req.getSession();
            int loggedInPoetId = ((Poet)session.getAttribute("poet")).getId();
            WorkDao workDao = new DatabaseWorkDao(connection);
            WorkService workService = new SimpleWorkService(workDao);

            List<Work> works = workService.getAllWorksForPoetById(loggedInPoetId);

            sendMessage(resp, HttpServletResponse.SC_OK, works);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
