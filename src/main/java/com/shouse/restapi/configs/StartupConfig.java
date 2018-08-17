package com.shouse.restapi.configs;

import com.shouse.restapi.communication.NodesAliveChecking;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import shouse.core.controller.Controller;

/**
 * Created by Maks on 11.05.2018.
 */
@Configuration
public class StartupConfig {

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner controllerRunner(TaskExecutor executor, Controller controller){
        return args -> executor.execute(controller);
    }

    @Bean
    public CommandLineRunner nodesAliveCheckingRunner(TaskExecutor executor, NodesAliveChecking nodesAliveChecking){
        return args -> executor.execute(nodesAliveChecking);
    }

}
