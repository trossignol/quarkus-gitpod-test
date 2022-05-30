package org.acme.label.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@Path("/api")
public class Resource {

    @GET
    @Path("/{key}")
    @SneakyThrows
    public Result get(String key) {
        Thread.sleep(200);
        return new Result(key, RandomStringUtils.randomAlphabetic(10));
    }

    @Getter
    @AllArgsConstructor
    public class Result {
        private String key;
        private String label;
    }
}