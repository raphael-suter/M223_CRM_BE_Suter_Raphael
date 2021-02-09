package ch.axa.ita.rs.m233_ap_b.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "projekteinsatz")
@IdClass(AssignmentId.class)
public class Assignment {
    @Id
    @ManyToOne
    @JoinColumn(name = "projekt_idfs")
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "mitarbeiter_idfs")
    @JsonIgnore
    private Employee employee;

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
