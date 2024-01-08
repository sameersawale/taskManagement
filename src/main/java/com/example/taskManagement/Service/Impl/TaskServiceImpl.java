package com.example.taskManagement.Service.Impl;

import com.example.taskManagement.Dto.TaskDTO;
import com.example.taskManagement.Model.Task;
import com.example.taskManagement.Model.User;
import com.example.taskManagement.Repository.TaskRepository;
import com.example.taskManagement.Repository.UserRepository;
import com.example.taskManagement.Service.TaskService;
import com.example.taskManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Task addTask(TaskDTO taskDTO) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task>taskList=user.getTaskList();
        Task task=new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.isCompleted());
        task.setUser(user);
        taskList.add(task);
        userRepository.save(user);
        taskRepository.save(task);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> taskList=user.getTaskList();
        return taskList;
    }


    @Override
    public String deleteTask(int id) {
        Task task=taskRepository.findById(id).get();
        User user= task.getUser();
        List<Task>taskList=user.getTaskList();
        for(Task task1:taskList){
            if(task1.equals(task)){
                taskList.remove(task1);
            }
        }
        user.setTaskList(taskList);
        userRepository.save(user);
        taskRepository.delete(task);
        return "task deleted successfully.........";
    }
}
