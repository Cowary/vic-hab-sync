package org.cowary.vichabsync.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cowary.vichabsync.dto.habitica.AddTaskRs;
import org.cowary.vichabsync.dto.habitica.TaskRq;
import org.cowary.vichabsync.dto.habitica.TaskRs;
import org.cowary.vichabsync.dto.habitica.TasksRs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HabiticaService {
    private final RestTemplate restTemplate;
    @Value("${app.habitica-url}")
    private final String baseUrl;
    @Value("${app.habitica-user}")
    private final String user;
    @Value("${app.habitica-token}")
    private final String token;

    public List<TaskRs> getTasksRs() {

        String url = baseUrl + "/tasks/user";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-user", user);
        headers.set("x-api-key", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<TasksRs> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                TasksRs.class
        );
        LOGGER.info("Completed a request to receive all tasks in habitica");
        LOGGER.debug("Received tasks: {}", response.getBody());

        return response.getBody().getData();
    }


    public AddTaskRs addTask(TaskRq addTaskRequest) {
        String url = baseUrl + "/tasks/user";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-user", user);
        headers.set("x-api-key", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TaskRq> requestEntity = new HttpEntity<>(addTaskRequest, headers);

        ResponseEntity<AddTaskRs> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                AddTaskRs.class
        );

        return response.getBody();
    }
}
