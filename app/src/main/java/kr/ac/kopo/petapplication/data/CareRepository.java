package kr.ac.kopo.petapplication.data;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.petapplication.model.CareItem;

public class CareRepository {

    // 앱 실행 중 데이터를 저장하는 공간
    private static List<CareItem> careList = new ArrayList<>();

    // 1. 데이터 추가
    public static void addCareItem(CareItem item) {
        careList.add(0, item); // 최신이 위로
    }

    // 2. 전체 데이터 가져오기
    public static List<CareItem> getAll() {
        return careList;
    }

    // 3. 타입별 필터 (WALK / VACCINE / MEMO)
    public static List<CareItem> getByType(String type) {

        List<CareItem> result = new ArrayList<>();

        for (CareItem item : careList) {
            if (item.type.equals(type)) {
                result.add(item);
            }
        }

        return result;
    }

    // 4. 전체 삭제 (필요하면 사용)
    public static void clearAll() {
        careList.clear();
    }
}