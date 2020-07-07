package main.rest.service;

import main.rest.model.Task;

import java.text.ParseException;
import java.util.List;

public interface TaskService
{
    public abstract List<Task> getTaskList();

    public abstract int createTask(Task task) throws ParseException;

    public abstract void markInProgress(Integer id);

    public abstract void markCanceled(Integer id);

    public abstract void markClosed(Integer id);

    public abstract void markCompleted(Integer id);

    public abstract void markDeleted(Integer id);

    public abstract void markExpired(Integer id);

    public abstract void deleteTask(Integer id);




}
