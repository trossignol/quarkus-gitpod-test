package org.acme.opentelemetry;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.opentelemetry.extension.annotations.WithSpan;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class FruitService {

    @WithSpan
    public Uni<List<Fruit>> listFruits() {
        // final int random = (int) Math.round(Math.random() * 2);
        // if (random == 2)
            // throw new RuntimeException("Error random");
        return Fruit.listAll();
    }
}
