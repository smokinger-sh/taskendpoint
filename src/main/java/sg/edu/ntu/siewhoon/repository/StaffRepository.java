package sg.edu.ntu.siewhoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.ntu.siewhoon.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    
}
