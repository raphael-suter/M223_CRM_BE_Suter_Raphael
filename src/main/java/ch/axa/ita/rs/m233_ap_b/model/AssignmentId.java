package ch.axa.ita.rs.m233_ap_b.model;

import java.io.Serializable;
import java.util.Objects;

public class AssignmentId implements Serializable {
    private Project project;
    private Employee employee;

    public AssignmentId() {
        super();
    }

    public AssignmentId(Project project, Employee employee) {
        this.project = project;
        this.employee = employee;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        AssignmentId that = (AssignmentId) object;
        return Objects.equals(project, that.project) && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, employee);
    }
}
