package com.app.tasksmanager.task.service;

import com.app.tasksmanager.task.dto.TaskRequestDTO;
import com.app.tasksmanager.task.dto.TaskResponseDTO;
import com.app.tasksmanager.task.model.TaskEntity;
import com.app.tasksmanager.task.persistence.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    // function create
    public TaskResponseDTO create(TaskRequestDTO dto){

        if(taskRepository.existsByTitle(dto.getTitle())){
            throw new RuntimeException("This task already exist !");
        }

        TaskEntity newTaskEntity = new TaskEntity();


        newTaskEntity.setTitle(dto.getTitle());
        newTaskEntity.setDescription((dto.getDescription()));
        newTaskEntity.setStatus((dto.getStatus()));
        newTaskEntity.setPriority((dto.getPriority()));
        newTaskEntity.setDueDate((dto.getDueDate()));

        TaskEntity savedTask = taskRepository.save(newTaskEntity);

        return toResponseDTO(savedTask);
    }

    // function that return object task entity as TaskResponseDTO
    public TaskResponseDTO toResponseDTO(TaskEntity entity){

        TaskResponseDTO dto = new TaskResponseDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription((entity.getDescription()));
        dto.setStatus((entity.getStatus()));
        dto.setPriority((entity.getPriority()));
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt((entity.getUpdatedAt()));
        dto.setDueDate((entity.getDueDate()));

        return dto;
    }

    // that get all tasks
    public List<TaskResponseDTO> getAll(){

        return taskRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // function getById()
    public TaskResponseDTO getById(Long id){

        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return toResponseDTO(entity);
    }

    // function delete a task :
    public String delete(Long id){

        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Get Failed : Any task have this id !"));

        taskRepository.delete(entity);

        return "Task deleted successfully";


    }

    // function update a task
    public TaskResponseDTO update(Long id, TaskRequestDTO dto){

        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Update Failed : Any task have this id !"));


        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setPriority(dto.getPriority());
        entity.setDueDate(dto.getDueDate());

        TaskEntity savedTask = taskRepository.save(entity);

        return toResponseDTO((savedTask));



    }
}
