package com.app.tasksmanager.task.dto;


import com.app.tasksmanager.task.model.enums.Priority;
import com.app.tasksmanager.task.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private LocalDateTime dueDate;

    private Status status = Status.EN_ATTENTE;
    private Priority priority = Priority.MOYENNE;

}
