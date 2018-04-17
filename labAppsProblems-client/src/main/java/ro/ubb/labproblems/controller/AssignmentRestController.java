package ro.ubb.labproblems.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class AssignmentRestController {

    private RestTemplate restTemplate;
    private String baseUrl;

    public AssignmentRestController(RestTemplateBuilder restTemplateBuilder, @Value("${server.baseUrl}") String baseUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    public String showAll() {
        return restTemplate.getForObject(baseUrl + "/assignments", String.class);
    }

    public String unassign(Integer registrationNumber, String title) {
        return restTemplate.postForObject(baseUrl + "assignments/{registrationNumber}/{title}/delete", null, String.class, registrationNumber, title);
    }

    public String grade(Integer registrationNumber, String title, Double grade) {
        String uri = UriComponentsBuilder
                .fromUriString(baseUrl + "/assignments/{registrationNumber}/{title}")
                .queryParam("registrationNumber", registrationNumber)
                .queryParam("title", title)
                .queryParam("grade", grade)
                .build(registrationNumber, title).toString();

        return restTemplate.postForObject(uri, null, String.class);
    }

    public String assign(Integer registrationNumber, String problemTitle) {
        String uri = UriComponentsBuilder
                .fromUriString(baseUrl + "/assignments/create")
                .queryParam("registrationNumber", registrationNumber)
                .queryParam("title", problemTitle)
                .build().toUriString();

        return restTemplate.postForObject(uri, null, String.class);
    }
}
