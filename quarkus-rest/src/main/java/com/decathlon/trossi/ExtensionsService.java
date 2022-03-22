package com.decathlon.trossi;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/fruits/mock")
@RegisterRestClient
public interface ExtensionsService {
    
    @GET
    Set<Fruit> listMock();
}
