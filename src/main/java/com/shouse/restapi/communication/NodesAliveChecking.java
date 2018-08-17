package com.shouse.restapi.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shouse.core.controller.NodeContainer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class NodesAliveChecking implements Runnable {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private NodeContainer nodeContainer;
    private int checkDelayInSeconds;
    private int maxAliveDurationInSeconds;

    public NodesAliveChecking(NodeContainer nodeContainer, int checkDelayInSeconds, int maxAliveDurationInSeconds) {
        this.nodeContainer = nodeContainer;
        this.checkDelayInSeconds = checkDelayInSeconds;
        this.maxAliveDurationInSeconds = maxAliveDurationInSeconds;
    }

    @Override
    public void run() {
        LOGGER.info("NodesAliveChecking started");
        while (true){
            nodeContainer.getAllNodes().stream()
                    .filter(node -> node.isActive())
                    .forEach(node -> {
                        Duration duration = Duration.between(node.getLastAliveDate(), LocalDateTime.now());
                        if(duration.getSeconds() > maxAliveDurationInSeconds){
                            LOGGER.warn("Node id:" + node.getId() + ". " +
                                    "Duration from last alive request:" + duration.getSeconds() + " sec. " +
                                    "Max value:" + maxAliveDurationInSeconds + "sec. " +
                                    "Set node as not active."
                            );

                            node.setActive(false);
                        }
                    });

            try {
                TimeUnit.SECONDS.sleep(checkDelayInSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
