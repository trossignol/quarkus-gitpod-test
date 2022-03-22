package com.decathlon.trossi;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/fruits")
public class FruitResource {

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    @Inject
    @RestClient
    ExtensionsService extensionsService;

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit TEST", 10));
        fruits.add(new Fruit("Pineapple", "Tropical fruit", 20));
    }

    @GET
    public Set<Fruit> list() {
        extensionsService.listMock().forEach(fruit -> System.out.println(fruit.name));
        return Set.copyOf(Fruit.listAll());
        //return fruits;
    }

    @GET
    @Path("/mock")
    public Set<Fruit> listMock() {
        return fruits;
    }
}