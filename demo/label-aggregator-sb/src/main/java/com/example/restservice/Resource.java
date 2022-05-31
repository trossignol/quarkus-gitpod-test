package com.example.restservice;

import java.util.List;
import java.util.stream.Collectors;

import com.example.restservice.LabelClient.LabelResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;

@RestController
public class Resource {

    @Autowired
    private LabelClient labelClient;

    @GetMapping("/api/{key}/sync")
    public Result getSync(@PathVariable String key) {
        return new Result(key, List.of(labelClient.getSync(key), labelClient.getSync(key), labelClient.getSync(key)));
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
