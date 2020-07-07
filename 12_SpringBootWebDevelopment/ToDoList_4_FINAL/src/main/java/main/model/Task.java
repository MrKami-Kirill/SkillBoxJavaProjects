package main.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return getStatus() == TaskStatus.EXPIRED;
    }

    public boolean isCompleted() {
        return getStatus() == TaskStatus.COMPLETED;
    }

    public boolean isDeleted() {
        return getStatus() == TaskStatus.DELETED;
    }

}
