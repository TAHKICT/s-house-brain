package com.shouse.restapi.configs;

import com.shouse.restapi.storage.InMemoryStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import shouse.core.api.RequestProcessor;
import shouse.core.communication.Communicator;
import shouse.core.controller.Controller;
import shouse.core.controller.ControllerImpl;
import shouse.core.node.storage.NodeStorage;

import java.util.Set;

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
    public NodeStorage nodeStorage(){
        return new InMemoryStorage();
    }

    @Bean
    public Controller smartController(Set<Communicator> communicators, Set<RequestProcessor> processors, NodeStorage nodeStorage){
        return new ControllerImpl(communicators, processors, nodeStorage);
    }

    @Bean
    public CommandLineRunner controllerRunner(TaskExecutor executor, Controller controller){
        return args -> executor.execute(controller);
    }

}
