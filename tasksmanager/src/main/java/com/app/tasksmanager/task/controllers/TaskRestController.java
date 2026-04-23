package com.app.tasksmanager.task.controllers;


import com.app.tasksmanager.task.dto.TaskRequestDTO;
import com.app.tasksmanager.task.dto.TaskResponseDTO;
import com.app.tasksmanager.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDTO> getAll(){
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getById(@PathVariable Long id){
        return taskService.getById(id);
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody @Valid TaskRequestDTO dto){
        return taskService.create(dto);
    }

    @PatchMapping("/{id}")
    public TaskResponseDTO updateTask(@RequestBody @Valid TaskRequestDTO dto, Long id){
        return taskService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        return taskService.delete(id);
    }
}
