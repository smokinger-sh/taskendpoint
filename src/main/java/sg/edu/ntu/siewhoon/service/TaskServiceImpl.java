package sg.edu.ntu.siewhoon.service;

import sg.edu.ntu.siewhoon.exceptions.StaffNotFoundException;
import sg.edu.ntu.siewhoon.exceptions.TaskNotFoundException;
import sg.edu.ntu.siewhoon.model.Staff;
import sg.edu.ntu.siewhoon.model.Task;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.siewhoon.repository.StaffRepository;
import sg.edu.ntu.siewhoon.repository.TaskRepository;

@Service 
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final StaffRepository staffRepository;

    public TaskServiceImpl(TaskRepository taskRepository, StaffRepository staffRepository) {
        this.taskRepository = taskRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Long id, Staff staff) {
        Staff staffToUpdate = staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
        staffToUpdate.setName(staff.getName());
        staffToUpdate.setEmail(staff.getEmail());
        staffToUpdate.setDepartment(staff.getDepartment());
        staffToUpdate.setPosition(staff.getPosition());
        return staffRepository.save(staffToUpdate);
    }

    @Override
    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new StaffNotFoundException(id);
        }
        staffRepository.deleteById(id);
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // Create Task
    @Override
    public Task createTask(Task task) {
        if (task.getStaff() == null || task.getStaff().getId() == null) {
            throw new IllegalArgumentException("Task must include a staff.id for an existing staff member");
        }
        Staff staff = staffRepository.findById(task.getStaff().getId())
                .orElseThrow(() -> new StaffNotFoundException(task.getStaff().getId()));
        task.setStaff(staff);
        return taskRepository.save(task);
    }

    // Get all tasks
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    // Update task by ID
    @Override
    public Task updateTask(Long id, Task task) {
        Task taskToUpdate = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskToUpdate.setTaskName(task.getTaskName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setStatus(task.getStatus());
        return taskRepository.save(taskToUpdate);
    }

    // Delete task by ID
    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    // Mark Task as completed
    @Override
    public Task markTaskAsCompleted(Long id) {
        Task taskToUpdate = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskToUpdate.setStatus(sg.edu.ntu.siewhoon.model.TaskStatus.COMPLETED);
        return taskRepository.save(taskToUpdate);
    }

    @Override
    public Task addTaskToStaff(Long staffId, Task task) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException(staffId));
        task.setStaff(staff);
        return taskRepository.save(task);
    }
}
