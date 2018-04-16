package ro.ubb.labproblems.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ProblemRestController {

    private RestTemplate restTemplate;
    private String baseUrl;

    public ProblemRestController(RestTemplateBuilder restTemplateBuilder, @Value("${server.baseUrl}") String baseUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    public String showAll() {
        return restTemplate.getForObject(baseUrl + "/problems", String.class);
    }

    public String add(String title, String description) {
        String uri = UriComponentsBuilder
                .fromUriString(baseUrl + "/problems/create")
                .queryParam("title",title)
                .queryParam("description", description)
                .build().toUriString();


        return restTemplate.postForObject(uri, null, String.class);
    }

    public String remove(String title) {
        return restTemplate.postForObject(baseUrl + "problems/{title}/delete", null, String.class, title);
    }

    public String update(String title, String descripton) {
        String uri = UriComponentsBuilder
                .fromUriString(baseUrl + "/problems/{title}")
                .queryParam("title", title)
                .queryParam("description", descripton)
                .build(title).toString();

        return restTemplate.postForObject(uri, null, String.class);
    }
}
