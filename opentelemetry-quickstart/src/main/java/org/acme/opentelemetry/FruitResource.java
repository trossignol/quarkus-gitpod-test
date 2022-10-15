package org.acme.opentelemetry;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

@Path("/fruit")
public class FruitResource {

    private static final Logger LOG = Logger.getLogger(FruitResource.class);

    @Inject
    FruitService service;

    @GET
    public Uni<List<Fruit>> getAll() {
        LOG.info("hello from logs!");
        return service.listFruits();
    }

    @GET
    @Path("/id/{id}")
    public Uni<Fruit> getById(@PathParam("id") long id) {
        return Fruit.findById(id);
    }

    @POST
    public Uni<Response> init() {
        LOG.info(" ---------> INIT <----------");
        final Fruit fruit = new Fruit("Thomas");
        return Panache.<Fruit>withTransaction(fruit::persist)
                .onItem().transform(inserted -> Response.created(URI.create("/fruits/" + inserted.id)).build());

    }
}