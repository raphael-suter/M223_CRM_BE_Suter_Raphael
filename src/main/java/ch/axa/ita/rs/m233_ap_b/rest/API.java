package ch.axa.ita.rs.m233_ap_b.rest;

import ch.axa.ita.rs.m233_ap_b.model.Employee;
import ch.axa.ita.rs.m233_ap_b.model.Message;
import ch.axa.ita.rs.m233_ap_b.model.SignInData;
import ch.axa.ita.rs.m233_ap_b.repository.AssignmentRepository;
import ch.axa.ita.rs.m233_ap_b.repository.EmployeeRepository;
import ch.axa.ita.rs.m233_ap_b.repository.ProjectRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static ch.axa.ita.rs.m233_ap_b.utility.HashGenerator.hash;
import static ch.axa.ita.rs.m233_ap_b.utility.ResponseGenerator.*;
import static ch.axa.ita.rs.m233_ap_b.utility.TokenTool.extractTokenFromHttpHeaders;
import static ch.axa.ita.rs.m233_ap_b.utility.TokenTool.generateToken;

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
                String token = generateToken();

                employee.setToken(token);
                employeeRepository.save(employee);

                return ok(new Message(token));
            }

            return badRequest(new Message("Das Passwort ist falsch."));
        }

        return notFound(new Message("Dieser Benutzer wurde nicht gefunden."));
    }

    @PostMapping("/sign_out")
    private ResponseEntity<?> signOut(@RequestHeader HttpHeaders httpHeaders) {
        Optional<Employee> userFromDB = employeeRepository.findByToken(extractTokenFromHttpHeaders(httpHeaders));

        if (userFromDB.isPresent()) {
            Employee employee = userFromDB.get();

            employee.setToken(null);
            employeeRepository.save(employee);

            return ok();
        }

        return notFound(new Message("Dieser Benutzer wurde nicht gefunden."));
    }
}
