package com.dmittov.mrdemo.app;

/**
 * Created by mittov on 31/12/2016.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class App {

    private static final Log log = LogFactory.getLog(App.class);

    public static void main(String[] args) throws Exception {
        // AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "/META-INF/spring/app-context.xml", App.class);
        log.info("Application Running");
        context.registerShutdownHook();

//        RawLogParser repository = context.getBean(RawLogHiveParser.class);
//        repository.run();
//        context.close();

        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        Job job = context.getBean(Job.class);
        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("event_date", new JobParameter("20161007"));
        jobLauncher.run(job, new JobParameters(parameters));

        log.info("Application Completed");
    }
}
