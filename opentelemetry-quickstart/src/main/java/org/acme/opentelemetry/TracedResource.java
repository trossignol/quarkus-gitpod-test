package org.acme.opentelemetry;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/hello")
public class TracedResource {

    private static final Logger LOG = Logger.getLogger(TracedResource.class);

    @Inject
    TracedService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOG.info("hello from logs!");
        return service.get();
    }
}