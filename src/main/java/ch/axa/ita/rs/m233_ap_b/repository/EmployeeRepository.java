package ch.axa.ita.rs.m233_ap_b.repository;

import ch.axa.ita.rs.m233_ap_b.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.username = ?1")
    Optional<Employee> findByUsername(String username);
}
