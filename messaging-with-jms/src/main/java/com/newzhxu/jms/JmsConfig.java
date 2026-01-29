package com.newzhxu.jms;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class JmsConfig implements ApplicationContextAware {
    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static class StopEvent extends ApplicationEvent {


        public StopEvent(Object source) {
            super(source);
        }
    }

    @EventListener(StopEvent.class)
    public void onStop(StopEvent event) {
//        registry.getListenerContainers().forEach(Lifecycle::stop);
        System.out.println("JMS Application is stopping. Performing cleanup...");
//        context.close();
//        if (applicationContext instanceof ConfigurableApplicationContext configurableApplicationContext) {
//            configurableApplicationContext.close();
//        }
        System.exit(SpringApplication.exit(applicationContext));
//        applicationContext.
    }
}
