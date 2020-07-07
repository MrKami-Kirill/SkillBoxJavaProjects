package main.rest.model;

import lombok.Data;

@Data
public class Task {

    private Integer id;
    private String name;
    private String description;
    private String date;
    private TaskStatus status;

}
