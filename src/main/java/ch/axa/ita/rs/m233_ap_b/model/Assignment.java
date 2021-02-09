package ch.axa.ita.rs.m233_ap_b.model;

import javax.persistence.*;

@Entity
@Table(name = "projekteinsatz")
@IdClass(AssignmentId.class)
public class Assignment {
    @Id
    @Column(name = "projekt_idfs")
    private int projectIDFS;

    @Id
    @Column(name = "mitarbeiter_idfs")
    private int employeeIDFS;

    @ManyToOne
    @JoinColumn(name = "projekt_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Employee employee;

    public int getProjectIDFS() {
        return projectIDFS;
    }

    public void setProjectIDFS(int projectIDFS) {
        this.projectIDFS = projectIDFS;
    }

    public int getEmployeeIDFS() {
        return employeeIDFS;
    }

    public void setEmployeeIDFS(int employeeIDFS) {
        this.employeeIDFS = employeeIDFS;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
