package ch.axa.ita.rs.m233_ap_b.repository;

import ch.axa.ita.rs.m233_ap_b.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
