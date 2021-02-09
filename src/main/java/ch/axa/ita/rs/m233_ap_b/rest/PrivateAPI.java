package ch.axa.ita.rs.m233_ap_b.rest;

import ch.axa.ita.rs.m233_ap_b.model.Project;
import ch.axa.ita.rs.m233_ap_b.model.ProjectWithEmployees;
import ch.axa.ita.rs.m233_ap_b.model.ProjectWithEmployeesCount;
import ch.axa.ita.rs.m233_ap_b.repository.AssignmentRepository;
import ch.axa.ita.rs.m233_ap_b.repository.EmployeeRepository;
import ch.axa.ita.rs.m233_ap_b.repository.ProjectRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ch.axa.ita.rs.m233_ap_b.utility.ResponseGenerator.notFound;
import static ch.axa.ita.rs.m233_ap_b.utility.ResponseGenerator.ok;

@RestController
@RequestMapping("/api/private")
@CrossOrigin(origins = "http://localhost:3000")
public class PrivateAPI {
    private EmployeeRepository employeeRepository;
    private AssignmentRepository assignmentRepository;
    private ProjectRepository projectRepository;

    public PrivateAPI(EmployeeRepository employeeRepository, AssignmentRepository assignmentRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.assignmentRepository = assignmentRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/project")
    public ResponseEntity<?> loadAllProjects() {
        List<ProjectWithEmployeesCount> projects = projectRepository
                .findAll()
                .stream()
                .map(project -> new ProjectWithEmployeesCount(project))
                .collect(Collectors.toList());

        return ok(projects);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> loadProjectById(@PathVariable("id") int id) {
        Optional<Project> project = projectRepository.findById(id);

        if (project.isPresent()) {
            return ok(new ProjectWithEmployees(project.get()));
        }

        return notFound("Dieses Projekt wurde nicht gefunden.");
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registerAuthenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthenticationFilter(employeeRepository));
        registrationBean.addUrlPatterns("/api/private/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
