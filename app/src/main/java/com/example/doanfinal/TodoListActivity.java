package com.example.doanfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.adapter.TaskAdapter;
import com.example.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;
    private List<TaskModel> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        getSupportActionBar().hide();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.taskRV);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(TodoListActivity.this);
        tasksRecyclerView.setAdapter(taskAdapter);

        TaskModel task = new TaskModel();
        task.setTask("Tét ták");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);

        taskAdapter.setTask(taskList);
    }
}