package hello.chunglim.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Meal {
    private String[] morning;
    private String[] lunch;
    private String[] dinner;
    private String date;
    private String days;

    public Meal(String[] morning, String[] lunch, String[] dinner, String date, String days) {
        this.morning = morning;
        this.lunch = lunch;
        this.dinner = dinner;
        this.date = date;
        this.days = days;
    }

    @Override
    public String toString() {
        return "날짜: " + date + " 요일: " + days + "\n"
                + "아침 메뉴 : " + Arrays.toString(morning) + "\n"
                + "점심 메뉴 : " + Arrays.toString(lunch) + "\n"
                + "저녁 메뉴 : " + Arrays.toString(dinner) + "\n";
    }
}
