package org.chen.fish.task;

import org.chen.fish.dao.JobMapper;
import org.chen.fish.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobTask {

    @Autowired
    private JobMapper jobMapper;

    private  static final Integer Process=1;
    private  static final Integer Ready=0;

    @Scheduled(initialDelay = 10000,fixedRate = 1000*10)
    public void dowork() throws InterruptedException {
        System.out.println("I am task");
        Integer ready=0;
        Job job = jobMapper.findByState(ready);
        if (job==null||job.getStatus()==Process){
            System.out.println("没有可执行的任务 ");
            return ;
        }
        Thread.sleep(3000);
        Integer newVersion = job.getVersion() + 1;
        int result = jobMapper.updateByVersion(Process,newVersion,Ready,job.getVersion(),"node");
        if (result == 0){
            System.out.println("未抢到任务，返回");
            return;
        }
        System.out.println("开始执行任务");
        Thread.sleep(5000);
        System.out.println("执行任务完毕");
    }
}
