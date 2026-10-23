package sg.edu.ntu.siewhoon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import jakarta.validation.Valid;
import sg.edu.ntu.siewhoon.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import sg.edu.ntu.siewhoon.model.Task;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController 
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


// createTask(Task task)
    @PostMapping("")
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task) {
        Task newTask = taskService.createTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }


// updateTask(Long id, Task task)
@PutMapping("/{id}")
public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody @Valid Task task) {
    Task updatedTask = taskService.updateTask(id, task);
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
}
// deleteTask(Long id)
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

// markTaskAsComplete(Long id)
@PutMapping("/{id}/complete")
public ResponseEntity<Task> markTaskAsComplete(@PathVariable Long id) {
    Task updatedTask = taskService.markTaskAsCompleted(id);
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
}

// findAllTasks()
@GetMapping("")
public ResponseEntity<List<Task>> findAllTasks() {
    List<Task> tasks = taskService.getAllTasks();
    return new ResponseEntity<>(tasks, HttpStatus.OK);
}

// findTaskById(Long id)
@GetMapping("/{id}")
public ResponseEntity<Task> findTaskById(@PathVariable Long id) {
    Task task = taskService.getTaskById(id);
    return new ResponseEntity<>(task, HttpStatus.OK);
}


}
