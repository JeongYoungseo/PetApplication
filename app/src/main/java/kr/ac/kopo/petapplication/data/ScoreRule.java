package kr.ac.kopo.petapplication.data;

import java.util.HashMap;
import java.util.Map;

public class ScoreRule {

    public static Map<String, Integer> typeMap = new HashMap<>();
    public static Map<String, Integer> weightMap = new HashMap<>();
    public static Map<String, Integer> healthMap = new HashMap<>();
    public static Map<String, Integer> allergyMap = new HashMap<>();

    // 초기화
    public static void init() {

        typeMap.put("강아지", 10);
        typeMap.put("고양이", 20);

        weightMap.put("소형", 5);
        weightMap.put("중형", 10);
        weightMap.put("대형", 15);

        healthMap.put("정상", 5);
        healthMap.put("비만", 15);
        healthMap.put("관절약함", 20);
        healthMap.put("피부질환", 20);

        allergyMap.put("닭고기", -30);
        allergyMap.put("곡물", -20);
        allergyMap.put("기타", -10);
        allergyMap.put("없음", 0);
    }
}
