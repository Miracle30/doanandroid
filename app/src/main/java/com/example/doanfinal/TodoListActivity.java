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

    RecyclerView mRecyclerview;
    FloatingActionButton fab;
    private DatabaseHandler myDB;
    private List<TaskModel> mList;
    private TaskAdapter adapter;
    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        addControls();
        addEvents();
    }

    private void addControls() {
        fab = findViewById(R.id.fab);
        mRecyclerview = findViewById(R.id.taskRV);
        myDB = new DatabaseHandler(TodoListActivity.this);
        mList = new ArrayList<>();
        adapter = new TaskAdapter(myDB , TodoListActivity.this);


        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(adapter));
    }

    private void addEvents() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask.newInstance().show(getSupportFragmentManager() , AddTask.TAG);
            }
        });

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);

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