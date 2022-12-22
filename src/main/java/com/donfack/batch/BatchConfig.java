package com.donfack.batch;


import com.donfack.batch.processor.MyCustomProcessor;
import com.donfack.batch.processor.MyCustomUserProcessor;
import com.donfack.batch.reader.MyCustomReader;
import com.donfack.batch.writer.MyCustomWriter;
import com.donfack.batch.writer.MyUserCustomWriter;
import com.donfack.employee.model.Employee;
import com.donfack.manager.model.Manager;
import com.donfack.user.model.User1;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    MyCustomReader myCustomReader;

    @Autowired
    MyCustomWriter myCustomWriter;

    @Autowired
    MyUserCustomWriter myUserCustomWriter;

    @Autowired
    MyCustomProcessor myCustomProcessor;

    @Autowired
    MyCustomUserProcessor myCustomUserProcessor;

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("MyJob")
                .incrementer(new RunIdIncrementer())
                .flow(createStep()).end().build();
    }

    @Bean
    public Job createJobuser() {
        return jobBuilderFactory.get("MyJob")
                .incrementer(new RunIdIncrementer())
                .flow(createStepUser()).end().build();
    }



    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("Employee to Manager")
                .<Employee, Manager> chunk(1)
                .reader(myCustomReader)
                .processor(myCustomProcessor)
                .writer(myCustomWriter)
                .build();
    }

    @Bean
    public Step createStepUser() {
        return stepBuilderFactory.get("Employee to User1 ")
                .<Employee, User1> chunk(1)
                .reader(myCustomReader)
                .processor(myCustomUserProcessor)
                .writer(myUserCustomWriter)
                .build();
    }



}
