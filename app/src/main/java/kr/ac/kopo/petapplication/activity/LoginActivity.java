package kr.ac.kopo.petapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.data.UserStore;

public class LoginActivity extends AppCompatActivity {

    EditText etId, etPw;
    Button btnLogin;
    TextView txtSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etId = findViewById(R.id.et_login_id);
        etPw = findViewById(R.id.et_login_pw);
        btnLogin = findViewById(R.id.btn_login);
        txtSignup = findViewById(R.id.txt_signup);

        // =========================
        // 로그인 버튼
        // =========================
        btnLogin.setOnClickListener(v -> {

            String inputId = etId.getText().toString();
            String inputPw = etPw.getText().toString();

            if (inputId.equals(UserStore.id) && inputPw.equals(UserStore.pw)) {

                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(
                        LoginActivity.this,
                        MainActivity.class
                );
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "아이디/비밀번호 틀림", Toast.LENGTH_SHORT).show();
            }
        });

        // =========================
        // 회원가입 이동
        // =========================
        txtSignup.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    SignupActivity.class
            );
            startActivity(intent);
        });
    }
}