package org.acme.restonly;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Path("/{api}")
public class Resource {

    @GET
    @Path("/{login}")
    public Result get(String login) {
        return new Result(login, (int) (Math.random() * 100));
    }

    @Getter
    @AllArgsConstructor
    public class Result {
        private String login;
        private int age;
    }
}