package com.fanjiexu.demo;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnocationDemo {
    String desc() default "";
}
