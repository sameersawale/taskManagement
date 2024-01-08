package com.example.taskManagement.Dto;

import lombok.Data;

@Data
public class TaskDTO {

    private String title;

    private String description;

    private boolean isCompleted;
}
