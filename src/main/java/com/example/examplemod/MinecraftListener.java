package com.example.examplemod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MinecraftListener {
	boolean registerInEventBus() default true;
	boolean registerGameEvent() default false;
}
