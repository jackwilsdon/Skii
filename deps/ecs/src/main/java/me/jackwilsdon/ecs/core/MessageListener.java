package me.jackwilsdon.ecs.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MessageListener {
    Class<? extends Message> type() default Message.class;
    boolean subclasses() default false;
}
