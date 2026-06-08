package com.example.cbeassessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        // Standard click listener for Registration
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (myDb.insertUser(email, password)) {
                        Toast.makeText(MainActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                        emailInput.setText("");
                        passwordInput.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Standard click listener for Login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (myDb.checkUser(email, password)) {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    // Redirect to Dashboard
                    startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}