package ch.axa.ita.rs.m233_ap_b.repository;

import ch.axa.ita.rs.m233_ap_b.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
