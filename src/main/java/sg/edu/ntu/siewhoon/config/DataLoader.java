package sg.edu.ntu.siewhoon.config;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import sg.edu.ntu.siewhoon.model.Department;
import sg.edu.ntu.siewhoon.model.Staff;
import sg.edu.ntu.siewhoon.repository.StaffRepository;

@Component
public class DataLoader {
    private final StaffRepository staffRepository;

    public DataLoader(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostConstruct
    public void loadData() {
        if (staffRepository.count() > 0) {
            return;
        }

        staffRepository.saveAll(List.of(
                createStaff("Joshua", "joshua@avengers.com", Department.SALES, "Manager"),
                createStaff("Siew Hoon", "SH@avengers.com", Department.ENGINEERING, "Executive"),
                createStaff("Bruce", "bruce@avengers.com", Department.MARKETING, "Executive")));
    }

    private Staff createStaff(String name, String email, Department department, String position) {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setEmail(email);
        staff.setDepartment(department);
        staff.setPosition(position);
        return staff;
    }
}