package org.cowary.vichabsync.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cowary.vichabsync.dto.habitica.TaskRq;
import org.cowary.vichabsync.dto.habitica.TaskRs;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class SyncService {

    private final HabiticaService habiticaService;
    private final VikunjaService vikunjaService;

    @Scheduled(fixedRate = 60000)
    public void syncToHabitica() {
        LOGGER.info("Start of synchronization syncToHabitica");
        var vikunjaTasks = vikunjaService.getSprintTasks();
        var habiticaTasks = habiticaService.getTasksRs()
                .stream()
                .filter(t -> "todo".equals(t.getType()))
                .toList();
        vikunjaTasks.removeIf(vt -> habiticaTasks.stream().map(TaskRs::getText).toList().contains(vt.getTitle()));
        var rq = vikunjaTasks.stream()
                .map(vt -> TaskRq.builder()
                        .type("todo")
                        .priority(1.5)
                        .text(vt.getTitle())
                        .date(LocalDateTime.parse(vt.getDueDate(), DateTimeFormatter.ISO_DATE_TIME).toLocalDate().atStartOfDay())
                        .build())
                .toList();
        for (TaskRq taskRq : rq) {
            var rs = habiticaService.addTask(taskRq);
            LOGGER.debug("Added a task to habitica: {}", rs);
        }
        LOGGER.info("End of synchronization syncToHabitica");
    }
}
