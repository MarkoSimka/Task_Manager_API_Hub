package com.example.taskmanagerapi.Service;

import com.example.taskmanagerapi.Models.Exceptions.InvalidTaskIdException;
import com.example.taskmanagerapi.Models.Task;
import com.example.taskmanagerapi.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        System.out.println("Tasks in DB: " + tasks);
        return tasks;
    }

    public Task getTaskById(Long id) {
        return this.taskRepository.findById(id).orElseThrow(InvalidTaskIdException::new);
    }

    public Task createTask(Task task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setStatus(task.getStatus());
        newTask.setPriority(task.getPriority());
        newTask.setAssignee(task.getAssignee());
        newTask.setDueDate(task.getDueDate());
        newTask.setCreatedAt(task.getCreatedAt());
        newTask.setUpdatedAt(task.getUpdatedAt());
        this.taskRepository.save(newTask);
        return task;
    }

    public Task updateTask(Task task, Long id) {
        return this.taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(task.getTitle());
                    existingTask.setDescription(task.getDescription());
                    existingTask.setStatus(task.getStatus());
                    existingTask.setPriority(task.getPriority());
                    existingTask.setAssignee(task.getAssignee());
                    existingTask.setDueDate(task.getDueDate());
                    existingTask.setCreatedAt(task.getCreatedAt());
                    existingTask.setUpdatedAt(task.getUpdatedAt());
                    this.taskRepository.save(existingTask);
                    return existingTask;
                }).orElseThrow(InvalidTaskIdException::new);
    }

    public void deleteTaskById(Long id) {
        if (this.taskRepository.findById(id).isPresent()) {
            this.taskRepository.deleteById(id);
        } else {
            throw new InvalidTaskIdException();
        }
    }
}
