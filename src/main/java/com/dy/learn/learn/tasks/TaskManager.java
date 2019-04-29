package com.dy.learn.learn.tasks;

import com.dy.learn.learn.dao.entity.TaskInfo;
import com.dy.learn.learn.enums.ETaskStatus;
import com.dy.learn.learn.service.TaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class TaskManager {

    SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Scheduled(cron = "50 0/1 * * * ?")// 每分钟的第50秒执行
    public void startJob(){
        LOGGER.info(">>>>>>>>>>>>>TaskManage run.....<<<<<<<<<<<<<<<");

        List<TaskInfo> taskInfos = taskService.getAllTaskList();
        if (taskInfos!=null && taskInfos.size()>0) {
            for (TaskInfo task : taskInfos)
                if (ETaskStatus.Stop.name().equals(task.getStatus())) {
                    // stop , to delete
                    try {
                        JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
                        JobDetail jobDetail = getScheduler().getJobDetail(jobKey);
                        if (jobDetail != null) {
                            LOGGER.info(">>>>>>>>>>>>task[id:{},name:{} is Stop,to delete it.]<<<<<<<<<<<<", task.getTaskId(), task.getTaskName());
                            deleteTask(task);
                        }
                    }catch (SchedulerException e){
                        LOGGER.error(">>>>>>>>> delete task[id:{}] fail,{} <<<<<<<<<<",task.getTaskId(),e);
                    }
                } else {
                    try {
                        JobKey jobKey = JobKey.jobKey(task.getTaskName(),task.getTaskGroup());
                        JobDetail jobDetail = getScheduler().getJobDetail(jobKey);
                        if (jobDetail!=null) {
                            CronTrigger trigger = (CronTrigger) getScheduler().getTrigger(new TriggerKey(task.getTaskName(), task.getTaskGroup()));
                            String oldCronExpression = trigger.getCronExpression();
                            if (oldCronExpression!=null && task.getCron().equals(oldCronExpression)){
                                // the same as so,to ignore
                            } else {
                                updateTask(task,oldCronExpression);
                            }
                        } else {
                            // add
                            addTask(task);
                        }
                    } catch (SchedulerException e){
                        LOGGER.error(">>>>>>>>> check task[id:{}] fail,{} <<<<<<<<<<",task.getTaskId(),e);
                    }
                }
        }
        LOGGER.info(">>>>>>>>>>>>>TaskManage run end.....<<<<<<<<<<<<<<<");

    }

    private void updateTask(TaskInfo task,String oldCronExpression) {
        LOGGER.info(">>>>>>>>>>>>>>>> update task[id:{},name:{}.oldCron:{},newCron:{}]<<<<<<<<<<<<<<<",task.getTaskId(),task.getTaskName(),oldCronExpression,task.getCron());
        deleteTask(task);
        addTask(task);
    }

    private void deleteTask(TaskInfo task) {
        try {
            LOGGER.info(">>>>>>>>>>>>>>>> delete task[id:{},name:{}]<<<<<<<<<<<<<<<",task.getTaskId(),task.getTaskName());
            JobKey jobKey = JobKey.jobKey(task.getTaskName(),task.getTaskGroup());
            getScheduler().deleteJob(jobKey);
        } catch (SchedulerException ex) {
            LOGGER.error(">>>>>>>>>> delete task fail:{}<<<<<<<<<<<<",ex);
        }
    }

    private void addTask(TaskInfo task){
        try{
            LOGGER.info(">>>>>>>>>>>>>>>> add new task[id:{},name:{}]<<<<<<<<<<<<<<<",task.getTaskId(),task.getTaskName());
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(task.getClassName());
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(task.getTaskName(), task.getTaskGroup()).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getTaskGroup())
                    .withSchedule(scheduleBuilder).build();
            getScheduler().scheduleJob(jobDetail, cronTrigger);
        } catch (SchedulerException ex) {
            LOGGER.error(">>>>>>>>>> add task fail:{}<<<<<<<<<<<<",ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
    }


    private Scheduler getScheduler(){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        return scheduler;
    }

}
