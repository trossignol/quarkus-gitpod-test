package gatling;


import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Simulation extends io.gatling.javaapi.core.Simulation {

        HttpProtocolBuilder httpProtocol = http
                        .baseUrl("http://localhost:8080")
                        .acceptHeader("application/json");

        ScenarioBuilder scn = scenario("Scenario")
                        .exec(http("request_1")
                                        .get("/api/thomas/async"));

        {
                setUp(scn.injectOpen(rampUsersPerSec(1).to(30).during(30)).protocols(httpProtocol));
        }
}