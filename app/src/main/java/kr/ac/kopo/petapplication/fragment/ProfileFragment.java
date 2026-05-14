package kr.ac.kopo.petapplication.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.activity.IntroActivity;
import kr.ac.kopo.petapplication.data.PetStore;

public class ProfileFragment extends Fragment {

    TextView tvPetName, tvAllergy, tvHealth, tvActivity;
    ImageView imgProfile;
    Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // =========================
        // UI 연결
        // =========================
        imgProfile = view.findViewById(R.id.img_profile);
        tvPetName = view.findViewById(R.id.tv_username);
        tvAllergy = view.findViewById(R.id.tv_allergy);
        tvHealth = view.findViewById(R.id.tv_health);
        tvActivity = view.findViewById(R.id.tv_activity);
        btnLogout = view.findViewById(R.id.btn_logout);

        // =========================
        // 데이터 표시
        // =========================
        loadProfile();

        // =========================
        // 로그아웃
        // =========================
        btnLogout.setOnClickListener(v -> {

            PetStore.name = null;
            PetStore.type = null;
            PetStore.age = null;
            PetStore.weight = null;
            PetStore.allergy = null;
            PetStore.health = null;
            PetStore.activity = null;
            PetStore.imageUri = null;

            Intent intent = new Intent(getActivity(), IntroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return view;
    }

    private void loadProfile() {

        // =========================
        // 이름
        // =========================
        if (PetStore.name != null) {
            tvPetName.setText(PetStore.name);
        } else {
            tvPetName.setText("등록된 반려동물 없음");
        }

        // =========================
        // 알러지
        // =========================
        if (PetStore.allergy != null) {
            tvAllergy.setText("알러지: " + PetStore.allergy);
        } else {
            tvAllergy.setText("알러지: 없음");
        }

        // =========================
        // 건강
        // =========================
        if (PetStore.health != null) {
            tvHealth.setText("건강상태: " + PetStore.health);
        } else {
            tvHealth.setText("건강상태: 미설정");
        }

        // =========================
        // 활동량
        // =========================
        if (PetStore.activity != null) {
            tvActivity.setText("활동량: " + PetStore.activity);
        } else {
            tvActivity.setText("활동량: 미설정");
        }

        // =========================
        // 프로필 이미지
        // =========================
        if (PetStore.imageUri != null && !PetStore.imageUri.isEmpty()) {
            try {
                imgProfile.setImageURI(Uri.parse(PetStore.imageUri));
            } catch (Exception e) {
                imgProfile.setImageResource(R.mipmap.ic_launcher);
            }
        } else {
            imgProfile.setImageResource(R.mipmap.ic_launcher);
        }
    }
}