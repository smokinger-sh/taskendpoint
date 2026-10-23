package sg.edu.ntu.siewhoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.ntu.siewhoon.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
