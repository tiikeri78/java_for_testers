package my.test.bugify.model;

import java.util.Objects;

public class Issue {
    private int id;
    private String subject;
    private String description;

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                Objects.equals(subject, issue.subject) &&
                Objects.equals(description, issue.description) &&
                Objects.equals(status, issue.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description, status);
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }

    private String status;


    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }
}
