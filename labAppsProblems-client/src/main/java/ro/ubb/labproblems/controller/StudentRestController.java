package ro.ubb.labproblems.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StudentRestController {


    private RestTemplate restTemplate;
    private String baseUrl;

    public StudentRestController(RestTemplateBuilder restTemplateBuilder, @Value("${server.baseUrl}") String baseUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    public String showAll() {
        return restTemplate.getForObject(baseUrl + "/students", String.class);
    }

    public String add(String name, String registrationNumber, Integer groupNumber) {
        String uri = UriComponentsBuilder
                .fromUriString(baseUrl + "/students/create")
                .queryParam("name", name)
                .queryParam("registrationNumber", registrationNumber)
                .queryParam("groupNumber", groupNumber)
                .build().toUriString();


        return restTemplate.postForObject(uri, null, String.class);
    }

    public String update(String name, String registrationNumber, Integer groupNumber) {
        String uri = UriComponentsBuilder
                .fromUriString(baseUrl + "/students/{registrationNumber}")
                .queryParam("name", name)
                .queryParam("registrationNumber", registrationNumber)
                .queryParam("groupNumber", groupNumber)
                .build(registrationNumber).toString();

        return restTemplate.postForObject(uri, null, String.class);
    }

    public String filterByGroup(Integer groupNumber) {
        return restTemplate.getForObject(baseUrl + "students/group/{groupNumber}", String.class, groupNumber);
    }

    public String remove(String registrationNumber) {
        return restTemplate.postForObject(baseUrl + "students/{registrationNumber}/delete", null, String.class, registrationNumber);
    }
}
