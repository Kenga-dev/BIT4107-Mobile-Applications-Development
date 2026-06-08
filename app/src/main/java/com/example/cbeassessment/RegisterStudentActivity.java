package com.example.cbeassessment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterStudentActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        myDb = new DatabaseHelper(this);

        EditText nameInput = findViewById(R.id.editStudentName);
        EditText admInput = findViewById(R.id.editAdmNo);
        Button saveBtn = findViewById(R.id.btnSaveStudent);

        // Standard click listener (No arrows!)
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String adm = admInput.getText().toString();

                if (name.isEmpty() || adm.isEmpty()) {
                    Toast.makeText(RegisterStudentActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (myDb.insertStudent(name, adm)) {
                        Toast.makeText(RegisterStudentActivity.this, "Student Registered!", Toast.LENGTH_SHORT).show();
                        finish(); // Closes this screen and goes back to Dashboard
                    } else {
                        Toast.makeText(RegisterStudentActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}