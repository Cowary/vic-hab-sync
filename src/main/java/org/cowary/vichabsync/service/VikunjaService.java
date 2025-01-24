package org.cowary.vichabsync.service;

import lombok.RequiredArgsConstructor;
import org.cowary.vikunja.api.TaskApi;
import org.cowary.vikunja.model.ModelsTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VikunjaService {

    private static final String SPRING_FILTER = "due_date < now/w+2w && due_date <=  now/w+1w && done = false";

    private final TaskApi taskApi;

    public List<ModelsTask> getSprintTasks() {
        var vikunjaResponse = taskApi.tasksAllGetWithHttpInfo(null, null, null, null, null, SPRING_FILTER, null, null, null);
        return vikunjaResponse.getBody();
    }
}
