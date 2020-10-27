package de.claudioaltamura.spring.cloud.opentracing.jaeger;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
class JaegerController {

    private final RestTemplate restTemplate;

    private final Tracer tracer;

    JaegerController(RestTemplate restTemplate, Tracer tracer) {
        this.restTemplate = restTemplate;
        this.tracer = tracer;
    }

    @GetMapping("/simple")
    public String simple()
    {
        return "ok";
    }

    @GetMapping("/combined")
    public String combined() {
        Span span = tracer.scopeManager().activeSpan();
        if (span != null) {
            span.log("hi there!");
            span.setTag("key", "my value");
            span.setBaggageItem("greeting", "hello");
        }

        //Rest call GET
        restTemplate.getForObject("https://httpbin.org/base64/SFRUUEJJTiBpcyBhd2Vzb21l", String.class);

        //Rest call faulty service
        restTemplate.getForEntity("https://httpbin.org/status/500", String.class);

        return "combined";
    }

}
