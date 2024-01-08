package com.example.taskManagement.Service;

import com.example.taskManagement.Dto.TaskDTO;
import com.example.taskManagement.Model.Task;
import com.example.taskManagement.Model.User;

import java.util.List;

public interface TaskService {

    public Task addTask(TaskDTO taskDTO);

    public List<Task> getAllTasks();

    public String deleteTask(int id);
}
