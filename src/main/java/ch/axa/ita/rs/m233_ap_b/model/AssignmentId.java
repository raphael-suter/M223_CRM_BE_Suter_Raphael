package ch.axa.ita.rs.m233_ap_b.model;

import java.io.Serializable;
import java.util.Objects;

public class AssignmentId implements Serializable {
    private int projectIDFS;
    private int employeeIDFS;

    public AssignmentId() {
        super();
    }

    public AssignmentId(int projectIDFS, int employeeIDFS) {
        this.projectIDFS = projectIDFS;
        this.employeeIDFS = employeeIDFS;
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
        return projectIDFS == that.projectIDFS && employeeIDFS == that.employeeIDFS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectIDFS, employeeIDFS);
    }
}
