package com.example.cbeassessment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btnRegister = findViewById(R.id.btnRegisterStudent);
        Button btnAssess = findViewById(R.id.btnConductAssessment);

        // Updated to link to the new activity
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, RegisterStudentActivity.class));
        });

        btnAssess.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, AssessmentActivity.class));
        });
    }
}