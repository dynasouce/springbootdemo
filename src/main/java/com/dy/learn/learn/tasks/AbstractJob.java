package com.dy.learn.learn.tasks;

import com.dy.learn.learn.dao.entity.TaskInfo;
import com.dy.learn.learn.service.TaskService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractJob implements Job {


    protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    @Override
    public void execute(JobExecutionContext job) throws JobExecutionException {
        String jobName = job.getJobDetail().getKey().getName();
        String jobGroup = job.getJobDetail().getKey().getGroup();
        LOGGER.info(">>>>>>>>>>> {} start: <<<<<<<<<<<",jobName);

        TaskInfo taskInfo = taskService.getTaskByNameAndGroup(jobName,jobGroup);

        run(job);

        taskInfo.setLastExecTime(new Date());
        taskService.updateTaskByPrimaryKey(taskInfo);

        LOGGER.info(">>>>>>>>>>> {} end: <<<<<<<<<<<",jobName);
    }


    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public abstract void run(JobExecutionContext job);

}
