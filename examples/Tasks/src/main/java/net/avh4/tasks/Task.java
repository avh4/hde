package net.avh4.tasks;


import java.io.Serializable;

import static net.avh4.tasks.android.Logger.log;

public class Task implements Serializable {
    private final int estimate;
    private final String description;
    private boolean completed;

    public Task(String text) {
        String estimateText = text.replaceFirst("^[^0-9]*", "");
        if (!estimateText.isEmpty()) {
            this.estimate = Integer.parseInt(estimateText);
//            log("ui_event", "set_estimate", "quick_add");
        } else {
            this.estimate = 10;
//            log("ui_event", "set_estimate", "default");
        }

        this.description = text;
        this.completed = false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "estimate=" + estimate +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (completed != task.completed) return false;
        if (estimate != task.estimate) return false;
        if (description != null ? !description.equals(task.description) : task.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = estimate;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }

    public void setCompleted() {
        completed = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getEstimate() {
        return estimate;
    }
}
