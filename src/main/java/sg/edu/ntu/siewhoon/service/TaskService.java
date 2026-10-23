package sg.edu.ntu.siewhoon.service;

import sg.edu.ntu.siewhoon.model.Task;
import sg.edu.ntu.siewhoon.model.Staff;
import java.util.List;


public interface TaskService {
    
    Task createTask(Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long id); 

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    Task markTaskAsCompleted(Long id);

    Staff saveStaff(Staff staff);
    Staff updateStaff(Long id, Staff staff);
    void deleteStaff(Long id);
    List<Staff> getAllStaff();

    Task addTaskToStaff(Long staffId, Task task);
}
