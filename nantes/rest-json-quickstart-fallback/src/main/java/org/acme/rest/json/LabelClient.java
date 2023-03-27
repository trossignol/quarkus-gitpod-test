package org.acme.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "label-client")
public interface LabelClient {
    
    @GET
    @Path("/{key}")
    LabelResult get(@PathParam("key") String key);

    public class LabelResult {
        public String key;
        public String label;
    }
}
