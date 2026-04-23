package com.app.tasksmanager.task.persistence;


import com.app.tasksmanager.task.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    boolean existsByTitle(String title);
}
