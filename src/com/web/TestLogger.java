package com.web;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RunWith(SerenityRunner.class)
public class TestLogger {

    protected Logger log = LogManager.getLogger(this);

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface RunCustomBefore {
    }

    @Rule
    public final TestWatcher watcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            log.info("Test '{}' - PASSED\n", description.getMethodName());
            super.succeeded(description);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            log.info("Test '{}' - FAILED\nReason: '{}'\n", description.getMethodName(), e.getMessage());
            super.failed(e, description);
        }

        @Override
        protected void starting(Description description) {
            Serenity.throwExceptionsImmediately();
            log.info("Test '{}' - starting...", description.getMethodName());
            super.starting(description);
        }
    };

    @Rule
    public TestRule beforeMethodRunCheck = (base, d) -> new Statement() {
        @Override
        public void evaluate() throws Throwable {
            if (d.getAnnotation(RunCustomBefore.class) != null) {
                customBefore();
            }
            base.evaluate();
        }
    };

    protected void customBefore() {
        log.debug("Before method is not overriden. Doing nothing");
    }
}
