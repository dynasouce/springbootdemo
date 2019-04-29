package com.dy.learn.learn.service.impl;

import com.dy.learn.learn.dao.entity.TaskInfo;
import com.dy.learn.learn.dao.mapper.TaskInfoMapper;
import com.dy.learn.learn.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Override
    public List<TaskInfo> getAllTaskList() {
        return taskInfoMapper.getAllTaskList();
    }

    @Override
    public TaskInfo getTaskById(int id) {
        return taskInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateTaskByPrimaryKey(TaskInfo taskInfo) {
        taskInfoMapper.updateByPrimaryKey(taskInfo);
    }

    @Override
    public TaskInfo getTaskByNameAndGroup(String jobName, String jobGroup) {
        return taskInfoMapper.getTaskByNameAndGroup(jobName,jobGroup);
    }
}
