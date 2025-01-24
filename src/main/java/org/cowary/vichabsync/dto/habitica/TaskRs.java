package org.cowary.vichabsync.dto.habitica;

import lombok.Data;

@Data
public class TaskRs {

    private String id;
    private String type;
    private String text;
    private Boolean completed;
    private String userID;

}
