package ch.axa.ita.rs.m233_ap_b.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mitarbeiter")
public class Employee {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "vorname")
    private String firstname;

    @Column(name = "nachname")
    private String lastname;

    @Column(name = "abteilung")
    private String department;

    @Column(name = "stundensatz")
    private double hourlyRate;

    @OneToMany(mappedBy = "employee")
    private List<Assignment> assignments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
