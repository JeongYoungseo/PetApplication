package kr.ac.kopo.petapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.kopo.petapplication.data.UserStore;

import kr.ac.kopo.petapplication.R;

public class SignupActivity extends AppCompatActivity {

    EditText etName, etId, etPw, etPwCheck, etAddress, etPhone, etEmail;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.et_name);
        etId = findViewById(R.id.et_id);
        etPw = findViewById(R.id.et_pw);
        etPwCheck = findViewById(R.id.et_pw_check);
        etAddress = findViewById(R.id.et_address);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);

        btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(v -> {

            UserStore.name = etName.getText().toString();
            UserStore.id = etId.getText().toString();
            UserStore.pw = etPw.getText().toString();
            UserStore.email = etEmail.getText().toString();
            UserStore.phone = etPhone.getText().toString();
            UserStore.address = etAddress.getText().toString();

            Intent intent = new Intent(
                    SignupActivity.this,
                    PetRegisterActivity.class
            );
            startActivity(intent);
        });
    }
}