package com.codecool.web.servlet;

import com.codecool.web.dao.WorkDao;
import com.codecool.web.dao.database.DatabaseWorkDao;
import com.codecool.web.model.Poet;
import com.codecool.web.model.Work;
import com.codecool.web.service.WorkService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleWorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/work")
public class WorkServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            WorkDao workDao = new DatabaseWorkDao(connection);
            WorkService workService = new SimpleWorkService(workDao);
            int loggedinPoetId = ((Poet)req.getSession().getAttribute("poet")).getId();

            String workId = req.getParameter("id");

            Work work = workService.getWorkForPoetById(loggedinPoetId, Integer.parseInt(workId));

            sendMessage(resp, HttpServletResponse.SC_OK, work);
        } catch (ServiceException ex) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
