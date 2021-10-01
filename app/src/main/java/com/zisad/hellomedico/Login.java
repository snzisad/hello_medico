package com.zisad.hellomedico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private Button Login;
    /* access modifiers changed from: private */
    public EditText Name;
    /* access modifiers changed from: private */
    public EditText Password;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_login);
        super.setTitle("Login");
        this.Name = (EditText) findViewById(R.id.etName);
        this.Password = (EditText) findViewById(R.id.etPassword);
        this.Login = (Button) findViewById(R.id.btnlogin);
        this.Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login.this.validate(Login.this.Name.getText().toString(), Login.this.Password.getText().toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public void validate(String userName, String userPassword) {
        if (!userName.equals("Admin") || !userPassword.equals("1234")) {
            DataContainer.admin = false;
            Toast.makeText(this, "Invalid username or password", 0).show();
            return;
        }
        DataContainer.admin = true;
        startActivity(new Intent(this, secondactivity.class));
        finish();
    }
}
