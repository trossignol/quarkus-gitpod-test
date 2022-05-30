package org.acme.label.aggregator;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.acme.label.aggregator.LabelClient.LabelResult;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import lombok.Getter;

@Path("/api")
public class Resource {

    @RestClient
    LabelClient labelClient;

    @GET
    @Path("/{key}/sync")
    @Blocking
    public Result getSync(String key) {
        return new Result(key, List.of(this.labelClient.get(key), this.labelClient.get(key),
                this.labelClient.get(key)));
    }

    @GET
    @Path("/{key}/async")
    @Blocking
    public Uni<Result> getAsync(String key) {
        return Uni.join()
                .all(this.labelClient.getAsync(key), this.labelClient.getAsync(key), this.labelClient.getAsync(key))
                .andCollectFailures().map(labels -> new Result(key, labels));
    }

    @Getter
    public class Result {
        private final String key;
        private final List<String> labels;

        public Result(String key, List<LabelResult> results) {
            this.key = key;
            this.labels = results.stream().map(LabelResult::getLabel).collect(Collectors.toList());
        }
    }
}