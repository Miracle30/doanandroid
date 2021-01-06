package com.example.doanfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.adapter.TaskAdapter;
import com.example.model.TaskModel;
import com.example.utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TodoListActivity extends AppCompatActivity implements DialogCloseListener{

//    public RecyclerView taskRecyclerView;
//    public TaskAdapter taskAdapter;
//    public List<TaskModel> taskList;
//    public FloatingActionButton fab;
//    public DatabaseHandler db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_todo_list);
//
//        Objects.requireNonNull(getSupportActionBar()).hide();
//
//        db = new DatabaseHandler(this);
////        db.openDb();
//
//        taskList = new ArrayList<>();
//        taskRecyclerView = findViewById(R.id.taskRV);
//        taskRecyclerView.setHasFixedSize(true);
//        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        taskRecyclerView.setAdapter(taskAdapter);
//
//        taskAdapter = new TaskAdapter(db,TodoListActivity.this);
//
//        ItemTouchHelper itemTouchHelper = new
//                ItemTouchHelper(new RecyclerItemTouchHelper(taskAdapter));
//        itemTouchHelper.attachToRecyclerView(taskRecyclerView);
//
//        //button them
//        fab = findViewById(R.id.fab);
//        taskList = db.getAllTask();
//        Collections.reverse(taskList);
//        taskAdapter.setTasks(taskList);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddTask.newInstance().show(getSupportFragmentManager(), AddTask.TAG);
//            }
//        });
//
////        TaskModel task = new TaskModel();
////        task.setTask("Tét ták");
////        task.setStatus(0);
////        task.setId(1);
////
////        taskList.add(task);
////        taskList.add(task);
//
//        taskAdapter.setTask(taskList);
//    }
//
//    @Override
//    public void handleDialogClose(DialogInterface dialog) {
//        taskList = db.getAllTask();
//        Collections.reverse(taskList);
//        taskAdapter.setTasks(taskList);
//        taskAdapter.notifyDataSetChanged();
//    }

    RecyclerView mRecyclerview;
    FloatingActionButton fab;
    private DatabaseHandler myDB;
    private List<TaskModel> mList;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mRecyclerview = findViewById(R.id.taskRV);
        fab = findViewById(R.id.fab);
        myDB = new DatabaseHandler(TodoListActivity.this);
        mList = new ArrayList<>();
        adapter = new TaskAdapter(myDB , TodoListActivity.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask.newInstance().show(getSupportFragmentManager() , AddTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();
    }


}