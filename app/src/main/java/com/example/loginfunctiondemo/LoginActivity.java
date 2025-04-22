package com.example.loginfunctiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etPhone, etPassword;
    private UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new UserDatabaseHelper(this);

        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);
        Button btnBack = findViewById(R.id.btn_back);

        btnLogin.setOnClickListener(v -> handleLogin());
        btnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        btnBack.setOnClickListener(v -> finish());
    }

    private void handleLogin() {
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Phone number and password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbHelper.validateUser(phone, password)) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid phone number or password", Toast.LENGTH_SHORT).show();
        }
    }
}