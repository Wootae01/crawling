package hello.chunglim.util;

import hello.chunglim.domain.Meal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MealParserTest {

    MealParser mealParser = new MealParser();
    @Test
    void parse() {
        //given
        String mealInfo = "쌀밥 소고기무국 (우육:호주산) 매콤두부조림 가지애호박볶음 고추지무침 맛김치 우유or두유 849kcal/28g";

        //when
        String[] parse = mealParser.parse(mealInfo);
        String[] expectedMenu = {
                "쌀밥",
                "소고기무국",
                "매콤두부조림",
                "가지애호박볶음",
                "고추지무침",
                "맛김치",
                "우유or두유"
        };
        System.out.println(Arrays.toString(parse));

        //then
        Assertions.assertThat(parse).isEqualTo(expectedMenu);
    }
}