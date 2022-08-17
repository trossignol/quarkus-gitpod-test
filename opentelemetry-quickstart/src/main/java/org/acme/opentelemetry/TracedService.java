package org.acme.opentelemetry;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import io.opentelemetry.extension.annotations.WithSpan;

@ApplicationScoped
public class TracedService {

    private static final Logger LOG = Logger.getLogger(TracedService.class);
    
    @WithSpan
    public String get() {
        final int random = (int) Math.round(Math.random()*2);
        LOG.info("hello from service: " + random);
        if (random == 2) throw new RuntimeException("Error random");
        return "hello " + random;
    }
}
