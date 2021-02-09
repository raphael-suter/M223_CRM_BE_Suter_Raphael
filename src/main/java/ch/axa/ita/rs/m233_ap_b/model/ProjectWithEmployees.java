package ch.axa.ita.rs.m233_ap_b.model;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectWithEmployees {
    private int id;
    private String name;
    private String description;
    private String status;
    private List<Employee> employees;

    public ProjectWithEmployees(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.status = project.getStatus();

        this.employees = project
                .getAssignments()
                .stream()
                .map(assignment -> assignment.getEmployee())
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
