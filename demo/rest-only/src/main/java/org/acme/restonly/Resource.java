package org.acme.restonly;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@Path("/{api}")
public class Resource {

    @GET
    @Path("/{login}")
    @SneakyThrows
    public Result get(String login) {
        Thread.sleep(200);
        return new Result(login, (int) (Math.random() * 100));
    }

    @Getter
    @AllArgsConstructor
    public class Result {
        private String login;
        private int age;
    }
}