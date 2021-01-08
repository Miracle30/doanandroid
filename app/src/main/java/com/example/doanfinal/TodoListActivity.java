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
import com.example.utils.TaskHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoListActivity extends AppCompatActivity implements DialogCloseListener{

    RecyclerView taskRV;
    FloatingActionButton fab;
    private TaskHelper myDB;
    private List<TaskModel> ListNote;
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
        taskRV = findViewById(R.id.taskRV);

        myDB = new TaskHelper(TodoListActivity.this);
        ListNote = new ArrayList<>();
        adapter = new TaskAdapter(myDB , TodoListActivity.this);

        taskRV.setHasFixedSize(true);
        taskRV.setLayoutManager(new LinearLayoutManager(this));
        taskRV.setAdapter(adapter);

        itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(adapter));

    }

    private void addEvents() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask.newInstance().show(getSupportFragmentManager() , AddTask.TAG);

            }
        });

        ListNote = myDB.getAllTasks();
        Collections.reverse(ListNote);
        adapter.setTasks(ListNote);

        itemTouchHelper.attachToRecyclerView(taskRV);
    }


    @Override
    public void handleDialogClose(DialogInterface dialog) {
        ListNote = myDB.getAllTasks();
        Collections.reverse(ListNote);
        adapter.setTasks(ListNote);
        adapter.notifyDataSetChanged();
    }

}