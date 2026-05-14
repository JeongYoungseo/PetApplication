package kr.ac.kopo.petapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.data.PetStore;

public class PetRegisterActivity extends AppCompatActivity {

    CardView cardBasic, cardCustom, cardResult;

    ImageView imgPet;
    Button btnPhoto, btnDog, btnCat, btnNext1;

    EditText etName, etAge, etWeight;

    CheckBox cbChicken, cbGrain, cbOtherAllergy, cbNoneAllergy;
    RadioGroup rgHealth, rgActivity;
    Button btnNext2;

    TextView tvResult;
    Button btnFinish;

    String petType = "";
    String allergy = "";
    String health = "";
    String activityLevel = "";

    ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_register);

        // =========================
        // 사진 선택기
        // =========================
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK
                            && result.getData() != null) {

                        Uri imageUri = result.getData().getData();

                        if (imageUri != null) {

                            // ⭐ 중요: 영구 권한 확보
                            getContentResolver().takePersistableUriPermission(
                                    imageUri,
                                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                            );

                            imgPet.setImageURI(imageUri);
                            PetStore.imageUri = imageUri.toString();
                        }
                    }
                }
        );

        // =========================
        // 카드 연결
        // =========================
        cardBasic = findViewById(R.id.card_basic);
        cardCustom = findViewById(R.id.card_custom);
        cardResult = findViewById(R.id.card_result);

        // =========================
        // 1페이지
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
        // 2페이지
        // =========================
        cbChicken = findViewById(R.id.cb_chicken);
        cbGrain = findViewById(R.id.cb_grain);
        cbOtherAllergy = findViewById(R.id.cb_other_allergy);
        cbNoneAllergy = findViewById(R.id.cb_none_allergy);

        rgHealth = findViewById(R.id.rg_health);
        rgActivity = findViewById(R.id.rg_activity);
        btnNext2 = findViewById(R.id.btn_next2);

        // =========================
        // 3페이지
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
        // 사진 선택
        // =========================
        btnPhoto.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

            galleryLauncher.launch(intent);
        });

        // =========================
        // 강아지 / 고양이
        // =========================
        btnDog.setOnClickListener(v -> {
            petType = "강아지";
            btnDog.setSelected(true);
            btnCat.setSelected(false);
        });

        btnCat.setOnClickListener(v -> {
            petType = "고양이";
            btnCat.setSelected(true);
            btnDog.setSelected(false);
        });

        // =========================
        // 1 → 2
        // =========================
        btnNext1.setOnClickListener(v -> {
            cardBasic.setVisibility(View.GONE);
            cardCustom.setVisibility(View.VISIBLE);
        });

        // =========================
        // 2 → 3
        // =========================
        btnNext2.setOnClickListener(v -> {

            StringBuilder sb = new StringBuilder();

            if (cbChicken.isChecked()) sb.append("닭고기 ");
            if (cbGrain.isChecked()) sb.append("곡물 ");
            if (cbOtherAllergy.isChecked()) sb.append("기타 ");
            if (cbNoneAllergy.isChecked()) sb.append("없음 ");

            allergy = sb.toString().trim();

            int healthId = rgHealth.getCheckedRadioButtonId();
            RadioButton healthBtn = findViewById(healthId);
            if (healthBtn != null) health = healthBtn.getText().toString();

            int actId = rgActivity.getCheckedRadioButtonId();
            RadioButton actBtn = findViewById(actId);
            if (actBtn != null) activityLevel = actBtn.getText().toString();

            PetStore.name = etName.getText().toString();
            PetStore.type = petType;
            PetStore.age = etAge.getText().toString();
            PetStore.weight = etWeight.getText().toString();
            PetStore.allergy = allergy;
            PetStore.health = health;
            PetStore.activity = activityLevel;

            String result =
                    "이름: " + PetStore.name + "\n" +
                            "종: " + petType + "\n" +
                            "나이: " + PetStore.age + "\n" +
                            "체중: " + PetStore.weight + "\n\n" +
                            "알러지: " + allergy + "\n" +
                            "건강: " + health + "\n" +
                            "활동량: " + activityLevel;

            tvResult.setText(result);

            cardCustom.setVisibility(View.GONE);
            cardResult.setVisibility(View.VISIBLE);
        });

        // =========================
        // 완료
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