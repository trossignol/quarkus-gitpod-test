package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/fruits")
public class FruitResource {

    @RestClient
    LabelClient labelClient;

    @GET
    @Transactional
    public List<Fruit> list() {
        if (Fruit.count() == 0) {
            Stream.of(new Fruit("Apple", "Winter fruit"),
                    new Fruit("Pineapple", "Tropical fruit"))
                    .forEach(fruit -> fruit.persist());
        }

        List<Fruit> fruits = Fruit.listAll();
        fruits.forEach(fruit -> fruit.label = this.labelClient.get(fruit.name).label);
        return fruits;
    }
}