package com.example.doanfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Date.DatePickerFrag;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddNote extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ImageView imvCalendar;
    TextView tvDate;
    NotesAdapter adapter = null;
    private List<Notes> NotesList = new ArrayList<Notes>();

    public class NotesAdapter extends ArrayAdapter<Notes>{
        public NotesAdapter(Context context,int textViewResourceId){
            super(context, textViewResourceId);
        }
        public NotesAdapter()
        {
            super(AddNote.this, android.R.layout.simple_list_item_1,NotesList);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //TODO Auto-generated method
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.listnote, null);
            }
            Notes r = NotesList.get(position);
            ((TextView) row.findViewById(R.id.titlethemvao)).setText(r.getTitle());
            return row;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Button save = (Button) findViewById(R.id.Save);
        save.setOnClickListener(onSave);
        ListView list = (ListView)findViewById(R.id.listnote123);
        adapter = new NotesAdapter();
        list.setAdapter(adapter);

        addControls();
        addEvents();

    }

    //save


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        //format
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        tvDate.setText(currentDateString);
    }

    private void addControls() {
        imvCalendar = findViewById(R.id.imvCalendar);
//        imvSave = findViewById(R.id.imvSave);
        tvDate = findViewById(R.id.tvDate);
    }
//

    private void addEvents() {
        imvCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFrag();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Notes r = new Notes();

            String msg;
            EditText title = findViewById(R.id.title);
            r.setTitle(title.getText().toString());
            msg = title.getText().toString();
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            NotesList.add(r);
        }


    };




}

