package org.cowary.vichabsync.dto.habitica;

import lombok.Data;

import java.util.List;

@Data
public class TasksRs {

    Boolean success;
    List<TaskRs> data;
}
