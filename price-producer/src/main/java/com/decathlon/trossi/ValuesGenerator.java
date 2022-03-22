package com.decathlon.trossi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ValuesGenerator {

    private static final Logger LOG = Logger.getLogger(ValuesGenerator.class);

    private Random random = new Random();

    private List<Product> products = List.of(
            new Product(1001, "Product 1", 13.0),
            new Product(1002, "Product 2", 5.3),
            new Product(1003, "Product 3", 125.2),
            new Product(1004, "Product 4 bis", 10.5));

    @Outgoing("prices")
    public Multi<Record<Long, String>> generate() {
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Multi.createFrom().ticks().every(Duration.ofMillis(500))
                .onOverflow().drop()
                .map(tick -> {
                    Product product = products.get(random.nextInt(products.size()));
                    double price = BigDecimal.valueOf(random.nextGaussian() * 15 + product.averagePrice)
                            .setScale(1, RoundingMode.HALF_UP)
                            .doubleValue();

                    LOG.infov("station: {0}, temperature: {1}", product.name, price);
                    return Record.of(product.id, "{ \"productId\" : " + product.id + ", \"price\" : " + price
                            + ", \"timestamp\": \"" + dateFormatter.format(new Date()) + "\"}");
                });
    }

    @Outgoing("products")
    public Multi<Record<Long, String>> products() {
        return Multi.createFrom().items(products.stream()
                .map(s -> Record.of(s.id, "{ \"id\" : " + s.id + ", \"name\" : \"" + s.name + "\" }")));
    }

    @Outgoing("orders")
    public Multi<Record<Long, String>> orders() {
        return Multi.createFrom().ticks().every(Duration.ofMillis(500))
                .onOverflow().drop()
                .map(tick -> {
                    final long id = (long) random.nextInt(10);
                    LOG.infov("order: {0}", id);
                    return Record.of(id, "{\"id\":" + id + ",\"product\":\"foo\",\"quantity\":10,\"price\":50}");
                });
    }

    private static class Product {
        long id;
        String name;
        double averagePrice;

        public Product(long id, String name, double averagePrice) {
            this.id = id;
            this.name = name;
            this.averagePrice = averagePrice;
        }
    }
}