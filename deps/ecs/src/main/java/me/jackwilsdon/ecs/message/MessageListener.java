package me.jackwilsdon.ecs.message;

import me.jackwilsdon.ecs.core.Message;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MessageListener {
    Class<? extends Message> messageClass();
    boolean includeSubclasses() default false;
}
