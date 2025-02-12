package com.example.taskmanagerapi.Web;

import com.example.taskmanagerapi.Models.Task;
import com.example.taskmanagerapi.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> listAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task taskCreated = this.taskService.createTask(task);
        return ResponseEntity.ok(taskCreated);
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<Task> editTask(@PathVariable Long id, @RequestBody Task task) {
        Task updated = this.taskService.updateTask(task, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        this.taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
