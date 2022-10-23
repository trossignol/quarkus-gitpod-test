package org.acme.opentelemetry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;

@RegisterRestClient(configKey = "label-client")
public interface LabelClient {

    @GET
    @Path("/{key}")
    Uni<LabelResult> get(@PathParam("key") String key);

    public record LabelResult(String key, String label) {
    }
}
