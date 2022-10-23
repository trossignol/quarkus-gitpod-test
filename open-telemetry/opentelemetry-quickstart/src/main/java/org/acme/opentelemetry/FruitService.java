package org.acme.opentelemetry;

import java.time.Duration;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class FruitService {

    @Inject
    @RestClient
    LabelClient labelClient;

    @WithSpan
    public Uni<List<Fruit>> listFruits() {
        this.randomError();
        return Fruit.listAll();
    }

    @WithSpan
    public Uni<Fruit> complete(Fruit fruit) {
        return this.labelClient.get(fruit.name)
                .map(label -> {
                    this.randomError();
                    fruit.label = label.label();
                    return fruit;
                });
    }

    private void randomError() {
        final int random = RandomUtils.nextInt(0, 10);
        if (random == 0)
            throw new RuntimeException("Error random");

    }

    @WithSpan
    public Uni<Void> bulkCreate(List<Fruit> fruits) {
        return Panache
                .<Void>withTransaction(() -> Fruit.persist(fruits))
                .ifNoItem()
                .after(Duration.ofMillis(fruits.size() * 10))
                .fail()
                .onFailure()
                .transform(IllegalStateException::new);
    }
}
