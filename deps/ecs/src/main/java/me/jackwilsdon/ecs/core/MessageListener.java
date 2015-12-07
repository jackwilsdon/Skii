package me.jackwilsdon.ecs.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageListener {
    int priority() default 0;
    Class<? extends Message> type() default Message.class;
    boolean subclasses() default false;
}
