package com.example.taskManagement.Controller;

import com.example.taskManagement.Dto.TaskDTO;
import com.example.taskManagement.Model.Task;
import com.example.taskManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public Task addTask(@RequestBody TaskDTO task){
        return taskService.addTask(task);
    }

    @GetMapping("/list")
    public List<Task> taskList(){
        return taskService.getAllTasks();
    }
}
