package org.acme.opentelemetry;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
        return Fruit.findById(id).map(Fruit.class::cast).chain(this::complete);
    }

    private Uni<Fruit> complete(Fruit fruit) {
        return Uni.join().all(
                this.service.complete(fruit),
                this.service.complete(fruit))
                .andFailFast().map(fruits -> fruits.get(0));
    }

    @POST
    public Uni<Fruit> create() {
        final Fruit fruit = new Fruit("peach");
        return Panache.<Fruit>withTransaction(fruit::persist)
                .replaceWith(fruit)
                .ifNoItem()
                .after(Duration.ofMillis(1_000))
                .fail()
                .onFailure()
                .transform(t -> new IllegalStateException(t));

    }

    @POST
    @Path("/bulk")
    public Uni<Void> bulkCreate() {
        final int nb = 1_000;
        final List<Fruit> fruits = IntStream.range(0, nb)
                .mapToObj(i -> new Fruit("Bulk " + (i + 1)))
                .toList();
        return this.service.bulkCreate(fruits);
    }
}