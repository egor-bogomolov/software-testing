package utils.elements;

import java.util.Objects;

public class Issue {
    private String summary;
    private String description;

    public Issue(String summary, String description) {
        this.summary = summary;
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public Issue shortVersion() {
        return new Issue(summary.replace("\\s+", " ").substring(0, Math.min(200, summary.length())),
                description.replace("\\s+", " ").substring(0, Math.min(200, description.length())));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(summary, issue.summary) &&
                Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(summary, description);
    }
}
