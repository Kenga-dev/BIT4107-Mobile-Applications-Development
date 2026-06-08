package com.example.cbeassessment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This simply loads your XML checklist without any buggy edge-to-edge logic
        setContentView(R.layout.activity_assessment);
    }
}