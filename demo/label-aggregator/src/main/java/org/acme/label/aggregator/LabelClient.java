package org.acme.label.aggregator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;
import lombok.Data;

@RegisterRestClient(configKey = "label-client")
public interface LabelClient {
    
    @GET
    @Path("/{key}")
    LabelResult get(@PathParam("key") String key);

    @GET
    @Path("/{key}")
    Uni<LabelResult> getAsync(@PathParam("key") String key);

    @Data
    public class LabelResult {
        private String key;
        private String label;
    }
}
