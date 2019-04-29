package com.dy.learn.learn.service;

import com.dy.learn.learn.dao.entity.TaskInfo;

import java.util.List;

public interface TaskService {

    List<TaskInfo> getAllTaskList();

    TaskInfo getTaskById(int id);

    void updateTaskByPrimaryKey(TaskInfo taskInfo);

    TaskInfo getTaskByNameAndGroup(String jobName, String jobGroup);
}
