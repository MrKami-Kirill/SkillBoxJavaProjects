package main.rest.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String date;
    private TaskStatus status;

    public boolean isCreated() {
        return getStatus() == TaskStatus.CREATED;
    }

    public boolean isInProgress() {
        return getStatus() == TaskStatus.IN_PROGRESS;
    }

    public boolean isCanceled() {
        return getStatus() == TaskStatus.CANCELED;
    }

    public boolean isClosed() {
        return getStatus() == TaskStatus.CLOSED;
    }

    public boolean isExpired() {
        if (getStatus() == TaskStatus.EXPIRED) {
            return true;
        }
        return false;
    }

    public boolean isCompleted() {
        return getStatus() == TaskStatus.COMPLETED;
    }

    public boolean isDeleted() {
        return getStatus() == TaskStatus.DELETED;
    }

}
