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
import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.adapter.RecommendAdapter;
import kr.ac.kopo.petapplication.data.PetStore;
import kr.ac.kopo.petapplication.data.ScoreRule;
import kr.ac.kopo.petapplication.model.RecommendItem;

import android.net.Uri;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecommendAdapter adapter;
    ArrayList<RecommendItem> itemList;
    ImageView imgProfile;
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
        imgProfile = view.findViewById(R.id.img_profile);
        recyclerView = view.findViewById(R.id.recycler_recommend);

        // =========================
        // 반려동물 정보 표시
        // =========================
//        if (PetStore.imageUri != null) {
//
//            if (PetStore.imageUri != null) {
//
//                imgProfile.setImageURI(
//                        Uri.parse(PetStore.imageUri)
//                );
//            }
       // }
        if (PetStore.name != null) {

            tvName.setText(PetStore.name);

            tvInfo.setText(
                    PetStore.type + " · " +
                            PetStore.age + "살 · " +
                            PetStore.weight + "kg"
            );
        }

        // =========================
        // 추천 데이터 생성
        // =========================
        setRecommendData();

        adapter = new RecommendAdapter(itemList);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        recyclerView.setAdapter(adapter);

        return view;
    }

    // =========================
    // 추천 시스템
    // =========================
    private void setRecommendData() {

        itemList = new ArrayList<>();

        // =========================
        // 전체 상품
        // =========================
        ArrayList<RecommendItem> allItems = createAllItems();

        // =========================
        // 카테고리별 최고 상품 저장
        // =========================
        Map<String, RecommendItem> bestMap = new HashMap<>();

        for (RecommendItem item : allItems) {

            // =========================
            // 필터링
            // =========================
            if (!isValid(item)) continue;

            // =========================
            // 점수 계산
            // =========================
            int score = calculateScore(item);

            item.score = score;

            String category = item.getCategory();

            // =========================
            // 카테고리별 최고 점수 저장
            // =========================
            if (!bestMap.containsKey(category)) {

                bestMap.put(category, item);

            } else {

                if (bestMap.get(category).score < item.score) {
                    bestMap.put(category, item);
                }
            }
        }

        // =========================
        // 추천 결과 없음
        // =========================
        if (bestMap.isEmpty()) {

            itemList.add(new RecommendItem(
                    R.drawable.x,
                    "추천 없음",
                    "조건에 맞는 상품이 없습니다",
                    "NONE",
                    "0원",
                    0
            ));

            return;
        }

        // =========================
        // 최종 리스트
        // =========================
        itemList.addAll(bestMap.values());
    }

    // =========================
    // 전체 상품 생성
    // =========================
    private ArrayList<RecommendItem> createAllItems() {

        ArrayList<RecommendItem> list = new ArrayList<>();

        // =========================
        // 사료
        // =========================
        list.add(new RecommendItem(
                R.drawable.bonefood,
                "관절 케어 사료",
                "관절 건강 강화",
                "사료",
                "28000원",
                0
        ));

        list.add(new RecommendItem(
                R.drawable.dietfood,
                "다이어트 사료",
                "체중 관리용",
                "사료",
                "25000원",
                0
        ));

        list.add(new RecommendItem(
                R.drawable.highprotienfood,
                "고단백 사료",
                "활동량 많은 반려동물용",
                "사료",
                "30000원",
                0
        ));

        // =========================
        // 간식
        // =========================
        list.add(new RecommendItem(
                R.drawable.beefsnack,
                "저칼로리 간식",
                "체중 관리 간식",
                "간식",
                "8000원",
                0
        ));

        list.add(new RecommendItem(
                R.drawable.tartarremoval,
                "치석 케어 간식",
                "치아 건강 강화",
                "간식",
                "12000원",
                0
        ));

        list.add(new RecommendItem(
                R.drawable.sweetpotato,
                "말랑 고구마 간식",
                "아이들이 좋아하는 고구마",
                "간식",
                "9000원",
                0
        ));

        // =========================
        // 영양제
        // =========================
        list.add(new RecommendItem(
                R.drawable.jointnutritionsupplement,
                "관절 영양제",
                "관절 보호 영양제",
                "영양제",
                "18000원",
                0
        ));

        list.add(new RecommendItem(
                R.drawable.skinnutrients,
                "피부 영양제",
                "피부 건강 개선",
                "영양제",
                "20000원",
                0
        ));
        list.add(new RecommendItem(
                R.drawable.eyenutrients,
                "눈 영양제",
                "눈 건강 개선",
                "영양제",
                "23000원",
                0
        ));

        return list;
    }

    // =========================
    // 필터
    // =========================
    private boolean isValid(RecommendItem item) {

        String allergy = PetStore.allergy;

        if (allergy != null) {

            if (allergy.contains("닭고기")
                    && item.getName().contains("닭")) {
                return false;
            }

            if (allergy.contains("곡물")
                    && item.getName().contains("곡물")) {
                return false;
            }
        }

        return true;
    }

    // =========================
    // 점수 계산
    // =========================
    private int calculateScore(RecommendItem item) {

        int baseScore = ScoreRule.getTotalScore(
                PetStore.type,
                safeParseWeight(PetStore.weight),
                PetStore.health,
                PetStore.allergy
        );

        return baseScore + getItemBonus(item);
    }

    // =========================
    // 상품 추가 점수
    // =========================
    private int getItemBonus(RecommendItem item) {

        // ===== 건강 상태 =====
        if ("관절약함".equals(PetStore.health)
                && item.getName().contains("관절")) {
            return 30;
        }

        if ("비만".equals(PetStore.health)
                && item.getName().contains("다이어트")) {
            return 25;
        }

        if ("피부질환".equals(PetStore.health)
                && item.getName().contains("피부")) {
            return 25;
        }

        // ===== 기본 점수 =====
        if (item.getName().contains("고단백")) {
            return 15;
        }

        return 5;
    }

    // =========================
    // 몸무게 안전 변환
    // =========================
    private float safeParseWeight(String weight) {

        try {
            return Float.parseFloat(weight);

        } catch (Exception e) {

            return 0;
        }
    }
}