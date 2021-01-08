package com.example.doanfinal;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.model.TaskModel;
import com.example.utils.TaskHelper;

public class AddTask  extends DialogFragment {
    public static final String TAG = "AddTask";

    EditText mEditText;
    Button mSaveButton;

    private TaskHelper myDb;

    public static AddTask newInstance(){
        return new AddTask();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_task, container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.newTaskText);
        mSaveButton = view.findViewById(R.id.newTaskButton);

        myDb = new TaskHelper(getActivity());

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if (bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            mEditText.setText(task);

            assert task != null;
            if (task.length() > 0 ){
                mSaveButton.setEnabled(false);
            }
        }

        //thay doi chuoi dung textWatcher
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    mSaveButton.setEnabled(false);
                    mSaveButton.setBackgroundColor(getResources().getColor(R.color.hongBinz));
                }else{
                    mSaveButton.setEnabled(true);
                    mSaveButton.setBackgroundColor(getResources().getColor(R.color.save));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Save lai
        final boolean finalIsUpdate = isUpdate;
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();

                if (finalIsUpdate){
                    //goi method update theo id
                    myDb.updateTask(bundle.getInt("id") , text);
                }else{
                    TaskModel item = new TaskModel();
                    item.setTask(text);
                    item.setStatus(0);
                    //insert vao db
                    myDb.insertTask(item);
                }
                dismiss();

            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
