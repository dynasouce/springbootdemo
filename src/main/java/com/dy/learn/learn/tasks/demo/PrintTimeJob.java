package com.dy.learn.learn.tasks.demo;

import com.dy.learn.learn.tasks.AbstractJob;
import org.quartz.JobExecutionContext;

import java.util.Date;

public class PrintTimeJob extends AbstractJob {

    @Override
    public void run(JobExecutionContext jobExecutionContext)  {
        LOGGER.info("PrintTimeJob," + sdf.format(new Date()));

    }
}
