package com.example.doanfinal;

import android.app.TabActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddNote extends TabActivity {

    private List<Notes> ListNote = new ArrayList<Notes>();
    NotesAdapter adapter = null;
    class NotesAdapter extends ArrayAdapter<Notes> {
        public NotesAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public NotesAdapter() {
            super(AddNote.this, android.R.layout.simple_list_item_1, ListNote);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //TODO Auto-generated method
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.listnote, null);
            }
            Notes n = ListNote.get(position);
            ((TextView) row.findViewById(R.id.title)).setText(n.getNote());
            ((TextView) row.findViewById(R.id.content)).setText(n.getContent());
            return row;
        }
    }
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Notes r = ListNote.get(position); // lấy item được chọn
            EditText note;
            EditText content;
// Tham chiếu đến các view trong addtask
            note = (EditText)findViewById(R.id.title3);
            content = (EditText)findViewById(R.id.addnotes);
// thiết lập thông tin tương ứng
            note.setText(r.getNote());
            content.setText(r.getContent());
// sinh viên có thể bổ sung lệnh sau để chuyển view về tab details
            getTabHost().setCurrentTab(1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Button save = (Button) findViewById(R.id.Save);
        save.setOnClickListener(onSave);
        ListView list = (ListView)findViewById(R.id.notes);
        list.setOnItemClickListener(onListClick);
        adapter = new NotesAdapter();
        list.setAdapter(adapter);


        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.notes);
        spec.setIndicator("List",getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);

        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.AddTask);
        spec.setIndicator("Details",
                getResources().getDrawable(R.drawable.notes));
        getTabHost().addTab(spec);

        getTabHost().setCurrentTab(0);

    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            String msg = "Successfully add to notes";
            Notes r = new Notes();
            EditText note = findViewById(R.id.title3);
            EditText content = findViewById(R.id.addnotes);
            r.setNote(note.getText().toString());
            r.setContent(content.getText().toString());
            ListNote.add(r);
        }
    };



}

