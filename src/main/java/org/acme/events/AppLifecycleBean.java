package org.acme.events;

import io.javalin.Context;
import io.javalin.Javalin;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = LoggerFactory.getLogger("ListenerBean");

    private Javalin app;


    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
        app = Javalin.create().start(7000);
        app.get("/", ctx -> getHelloWorld(ctx));
    }

    private Object getHelloWorld(Context ctx) {
        LOGGER.info("Calling helloworld");
        return ctx.result("Hello World");
    }

    void onStop(@Observes ShutdownEvent ev) {
        app.stop();
        LOGGER.info("The application is stopping... ");
    }

}
