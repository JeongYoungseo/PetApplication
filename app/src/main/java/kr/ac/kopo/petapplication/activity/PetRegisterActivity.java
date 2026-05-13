package kr.ac.kopo.petapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import kr.ac.kopo.petapplication.data.PetStore;

import kr.ac.kopo.petapplication.R;

public class PetRegisterActivity extends AppCompatActivity {

    // =========================
    // 카드
    // =========================
    CardView cardBasic, cardCustom, cardResult;

    // =========================
    // 1페이지
    // =========================
    ImageView imgPet;
    Button btnPhoto, btnDog, btnCat, btnNext1;

    EditText etName, etAge, etWeight;

    String petType = "";

    // =========================
    // 2페이지
    // =========================
    CheckBox cbChicken, cbGrain, cbOtherAllergy, cbNoneAllergy;
    RadioGroup rgHealth, rgActivity;
    Button btnNext2;

    String allergy = "";
    String health = "";
    String activityLevel = "";

    // =========================
    // 3페이지
    // =========================
    TextView tvResult;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_register);

        // =========================
        // 카드 연결
        // =========================
        cardBasic = findViewById(R.id.card_basic);
        cardCustom = findViewById(R.id.card_custom);
        cardResult = findViewById(R.id.card_result);

        // =========================
        // 1페이지 연결
        // =========================
        imgPet = findViewById(R.id.img_pet);
        btnPhoto = findViewById(R.id.btn_add_photo);
        btnDog = findViewById(R.id.btn_dog);
        btnCat = findViewById(R.id.btn_cat);
        btnNext1 = findViewById(R.id.btn_next1);

        etName = findViewById(R.id.et_pet_name);
        etAge = findViewById(R.id.et_age);
        etWeight = findViewById(R.id.et_weight);

        // =========================
        // 2페이지 연결
        // =========================
        cbChicken = findViewById(R.id.cb_chicken);
        cbGrain = findViewById(R.id.cb_grain);
        cbOtherAllergy = findViewById(R.id.cb_other_allergy);
        cbNoneAllergy = findViewById(R.id.cb_none_allergy);

        rgHealth = findViewById(R.id.rg_health);
        rgActivity = findViewById(R.id.rg_activity);
        btnNext2 = findViewById(R.id.btn_next2);

        // =========================
        // 3페이지 연결
        // =========================
        tvResult = findViewById(R.id.tv_result);
        btnFinish = findViewById(R.id.btn_finish);

        // =========================
        // 초기 상태
        // =========================
        cardBasic.setVisibility(View.VISIBLE);
        cardCustom.setVisibility(View.GONE);
        cardResult.setVisibility(View.GONE);

        // =========================
        // 📷 사진 (임시)
        // =========================
        btnPhoto.setOnClickListener(v -> {
            imgPet.setImageResource(R.mipmap.ic_launcher);
        });

        // =========================
        // 🐶 강아지 선택
        // =========================
        btnDog.setOnClickListener(v -> {
            petType = "강아지";
            btnDog.setSelected(true);
            btnCat.setSelected(false);
        });

        // =========================
        // 🐱 고양이 선택
        // =========================
        btnCat.setOnClickListener(v -> {
            petType = "고양이";
            btnCat.setSelected(true);
            btnDog.setSelected(false);
        });

        // =========================
        // 1 → 2 페이지
        // =========================
        btnNext1.setOnClickListener(v -> {
            cardBasic.setVisibility(View.GONE);
            cardCustom.setVisibility(View.VISIBLE);
        });

        // =========================
        // 2 → 3 페이지
        // =========================
        btnNext2.setOnClickListener(v -> {

            // ===== 알러지 =====
            StringBuilder sb = new StringBuilder();

            if (cbChicken.isChecked()) sb.append("닭고기 ");
            if (cbGrain.isChecked()) sb.append("곡물 ");
            if (cbOtherAllergy.isChecked()) sb.append("기타 ");
            if (cbNoneAllergy.isChecked()) sb.append("없음 ");

            allergy = sb.toString().trim();

            // ===== 건강 =====
            int healthId = rgHealth.getCheckedRadioButtonId();
            RadioButton healthBtn = findViewById(healthId);

            if (healthBtn != null)
                health = healthBtn.getText().toString();

            // ===== 활동량 =====
            int actId = rgActivity.getCheckedRadioButtonId();
            RadioButton actBtn = findViewById(actId);

            if (actBtn != null)
                activityLevel = actBtn.getText().toString();

            // ===== 입력값 =====
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String weight = etWeight.getText().toString();

            PetStore.name = etName.getText().toString();
            PetStore.type = petType;
            PetStore.age = etAge.getText().toString();
            PetStore.weight = etWeight.getText().toString();

            PetStore.allergy = allergy;
            PetStore.health = health;
            PetStore.activity = activityLevel;

            // ===== 결과 화면 출력 =====
            String result =
                    "이름: " + name + "\n" +
                            "종: " + petType + "\n" +
                            "나이: " + age + "\n" +
                            "체중: " + weight + "\n\n" +
                            "알러지: " + allergy + "\n" +
                            "건강: " + health + "\n" +
                            "활동량: " + activityLevel;

            tvResult.setText(result);

            // ===== 페이지 이동 =====
            cardCustom.setVisibility(View.GONE);
            cardResult.setVisibility(View.VISIBLE);
        });

        // =========================
        // 완료 → 홈 이동
        // =========================
        btnFinish.setOnClickListener(v -> {

            Intent intent = new Intent(
                    PetRegisterActivity.this,
                    LoginActivity.class
            );

            startActivity(intent);
            finish();
        });
    }
}