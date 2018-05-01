package com.codecool.web.dao;

import com.codecool.web.model.Work;

import java.sql.SQLException;
import java.util.List;

public interface WorkDao {
    List<Work> getAllWorksForPoetById(int id) throws SQLException;
    Work getWorkForPoetById(int poetId, int workId) throws SQLException;
    int getOccurenceOfSubstringInWork(int workId, String substring) throws SQLException;
}
