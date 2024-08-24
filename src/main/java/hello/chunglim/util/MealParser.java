package hello.chunglim.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class MealParser {

    public String[] parse(String mealInfo) {
        List<String> menuItems = new ArrayList<>();
        if (mealInfo == "" || mealInfo.equals(".")) {
            menuItems.add("등록된 식단이 없습니다.");
            return menuItems.toArray(new String[0]);
        }
        // 문자열을 공백을 기준으로 나누되, 필요 시 이어붙이기
        String[] parts = mealInfo.split(" ");
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            // 칼로리, 단백질 또는 기타 추가 정보로부터 메뉴 구분
            if (part.contains("단백질") || part.contains("에너지") || part.contains("Kcal") || part.contains("g")) {
                continue;
            } else{
                menuItems.add(part);
            }

           /* //원산지 정보는 메뉴 옆에 붙인다
            if (part.contains("(") || part.contains(")")) {
                String tmp = menuItems.get(menuItems.size() - 1);
                currentMenuItem.append(tmp).append(part);
                menuItems.remove(menuItems.size() - 1);
                menuItems.add(currentMenuItem.toString());
                currentMenuItem.setLength(0);
            } else {
                menuItems.add(part);
            }*/
        }

        // 최종 메뉴 배열로 변환
        return menuItems.toArray(new String[0]);
    }
}
