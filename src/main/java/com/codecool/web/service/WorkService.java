package com.codecool.web.service;

import com.codecool.web.model.Work;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface WorkService {
    List<Work> getAllWorksForPoetById(int id) throws SQLException;
    Work getWorkForPoetById(int poetId, int workId) throws SQLException, ServiceException;
    int getOccurenceOfSubstringInWork(int workId, String subString) throws SQLException, ServiceException;
}
