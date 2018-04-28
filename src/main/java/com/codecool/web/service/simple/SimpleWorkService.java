package com.codecool.web.service.simple;

import com.codecool.web.dao.WorkDao;
import com.codecool.web.model.Work;
import com.codecool.web.service.WorkService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleWorkService implements WorkService {
    private final WorkDao workDao;

    public SimpleWorkService(WorkDao workDao) {
        this.workDao = workDao;
    }

    @Override
    public List<Work> getAllWorksForPoetById(int id) throws SQLException {
        return workDao.getAllWorksForPoetById(id);
    }

    @Override
    public Work getWorkForPoetById(int poetId, int workId) throws SQLException, ServiceException {
        try {
            Work work =  workDao.getWorkForPoetById(poetId, workId);
            if (work == null) {
                throw new ServiceException("No work found with these ids!");
            }
            return work;
        } catch (NumberFormatException ex) {
            throw new ServiceException("Id must be an integer!");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
