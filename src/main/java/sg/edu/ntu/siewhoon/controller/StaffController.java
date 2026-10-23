package sg.edu.ntu.siewhoon.controller;

import java.util.List;
import sg.edu.ntu.siewhoon.model.Task;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.siewhoon.model.Staff;
import sg.edu.ntu.siewhoon.service.TaskService;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final TaskService taskService;

    public StaffController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Staff> addStaff(@RequestBody @Valid Staff staff) {
        Staff newStaff = taskService.saveStaff(staff);
        return new ResponseEntity<>(newStaff, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(taskService.getAllStaff());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody @Valid Staff staff) {
        Staff updatedStaff = taskService.updateStaff(id, staff);
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        taskService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<Task> addTaskToStaff(
            @PathVariable Long id,
            @RequestBody @Valid Task task) {
        Task newTask = taskService.addTaskToStaff(id, task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }
}