package org.cowary.vichabsync.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cowary.vikunja.api.TaskApi;
import org.cowary.vikunja.model.ModelsTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VikunjaService {

    private static final String SPRING_FILTER_ACTIVE = "due_date <= now/w+1w && done = false";
    private static final String SPRING_FILTER_COMPLETED = "due_date <= now/w+1w && done = true && due_date > now/w-4w";

    private final TaskApi taskApi;

    public List<ModelsTask> getActiveSprintTasks() {
        var vikunjaResponse = taskApi.tasksAllGetWithHttpInfo(null, null, null, null, null, SPRING_FILTER_ACTIVE, null, null, null);
        LOGGER.debug("active spring tasks: {}", vikunjaResponse.getBody());
        return vikunjaResponse.getBody();
    }

    public List<ModelsTask> getCompletedSprintTasks() {
        var response = taskApi.tasksAllGetWithHttpInfo(null, null, null, null, null, SPRING_FILTER_COMPLETED, null, null, null);
        LOGGER.debug("completed spring tasks: {}", response.getBody());
        return response.getBody();
    }
}
