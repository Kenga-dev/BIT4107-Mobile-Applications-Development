package com.example.cbeassessment;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    ListView studentListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        myDb = new DatabaseHelper(this);
        studentListView = findViewById(R.id.studentListView);

        ArrayList<String> studentList = new ArrayList<>();
        Cursor res = myDb.getAllStudents();

        while (res.moveToNext()) {
            // Adds "Name - Adm No" to the list
            studentList.add(res.getString(1) + " (" + res.getString(2) + ")");
        }
        res.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        studentListView.setAdapter(adapter);
    }
}