package ch.axa.ita.rs.m233_ap_b.rest;

import ch.axa.ita.rs.m233_ap_b.model.Employee;
import ch.axa.ita.rs.m233_ap_b.model.Message;
import ch.axa.ita.rs.m233_ap_b.model.SignInData;
import ch.axa.ita.rs.m233_ap_b.model.Token;
import ch.axa.ita.rs.m233_ap_b.repository.AssignmentRepository;
import ch.axa.ita.rs.m233_ap_b.repository.EmployeeRepository;
import ch.axa.ita.rs.m233_ap_b.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static ch.axa.ita.rs.m233_ap_b.utility.HashGenerator.hash;
import static ch.axa.ita.rs.m233_ap_b.utility.ResponseGenerator.*;
import static ch.axa.ita.rs.m233_ap_b.utility.TokenGenerator.token;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class API {
    private EmployeeRepository employeeRepository;
    private AssignmentRepository assignmentRepository;
    private ProjectRepository projectRepository;

    public API(EmployeeRepository employeeRepository, AssignmentRepository assignmentRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.assignmentRepository = assignmentRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> getHello() {
        return ok(new Message("Hello!"));
    }

    @PostMapping("/sign_in")
    private ResponseEntity<?> signIn(@RequestBody SignInData signInData) {
        Optional<Employee> employeeFromDB = employeeRepository.findByUsername(signInData.getUsername());

        if (employeeFromDB.isPresent()) {
            Employee employee = employeeFromDB.get();

            String passwordFromDB = employee.getPassword();
            String passwordFromSignInData = hash(signInData.getPassword());

            if (passwordFromDB.equals(passwordFromSignInData)) {
                Token token = token();

                employee.setToken(token.getToken());
                employeeRepository.save(employee);

                return ok(token);
            }

            return badRequest(new Message("Das Passwort ist falsch."));
        }

        return notFound(new Message("Dieser Benutzer wurde nicht gefunden."));
    }
}
