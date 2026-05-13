package kr.ac.kopo.petapplication.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.adapter.RecommendAdapter;
import kr.ac.kopo.petapplication.model.RecommendItem;
import kr.ac.kopo.petapplication.data.PetStore;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecommendAdapter adapter;
    ArrayList<RecommendItem> itemList;

    TextView tvName, tvInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // =========================
        // UI 연결
        // =========================
        tvName = view.findViewById(R.id.tv_name);
        tvInfo = view.findViewById(R.id.tv_info);

        recyclerView = view.findViewById(R.id.recycler_recommend);

        // =========================
        // PetStore 데이터 표시
        // =========================
        if (PetStore.name != null) {

            tvName.setText(PetStore.name);

            tvInfo.setText(
                    PetStore.type + " · " +
                            PetStore.age + "살 · " +
                            PetStore.weight + "kg"
            );
        }

        // =========================
        // ⭐ 추천 데이터 생성 (여기서 "호출"만)
        // =========================
        setRecommendData();

        adapter = new RecommendAdapter(itemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    // =========================
    // ⭐ 추천 로직 (밖으로 이동!)
    // =========================
    private void setRecommendData() {

        itemList = new ArrayList<>();

        String type = PetStore.type;
        String health = PetStore.health;
        String allergy = PetStore.allergy;

        float weight = 0;
        try {
            weight = Float.parseFloat(PetStore.weight);
        } catch (Exception e) {
            weight = 0;
        }

        String weightType = getWeightType(weight);

        int baseScore = 0;

        if ("강아지".equals(type)) baseScore += 10;
        if ("고양이".equals(type)) baseScore += 20;

        if ("소형".equals(weightType)) baseScore += 5;
        else if ("중형".equals(weightType)) baseScore += 10;
        else baseScore += 15;

        if ("정상".equals(health)) baseScore += 5;
        else if ("비만".equals(health)) baseScore += 15;
        else if ("관절약함".equals(health)) baseScore += 20;
        else if ("피부질환".equals(health)) baseScore += 18;
        else baseScore += 10;

        if (allergy != null) {

            if (allergy.contains("닭고기")) baseScore -= 30;
            if (allergy.contains("곡물")) baseScore -= 20;
            if (allergy.contains("기타")) baseScore -= 10;
            if (allergy.contains("없음")) baseScore += 5;
        }

        itemList.add(new RecommendItem(
                "관절 케어 사료",
                "맞춤 사료",
                "사료",
                "28000원",
                baseScore + 20
        ));

        itemList.add(new RecommendItem(
                "다이어트 사료",
                "체중 관리",
                "사료",
                "25000원",
                baseScore + 10
        ));

        itemList.add(new RecommendItem(
                "고단백 사료",
                "활동량 많은 반려동물용",
                "사료",
                "30000원",
                baseScore + 15
        ));

        Collections.sort(itemList, (a, b) -> b.score - a.score);
    }

    // =========================
    // 체중 분류
    // =========================
    private String getWeightType(float weight) {

        if (weight < 5) return "소형";
        else if (weight < 15) return "중형";
        else return "대형";
    }
}