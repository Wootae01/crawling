package hello.chunglim.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Meal {
    private int type; //기숙사 정보
    private String[] morning;
    private String[] lunch;
    private String[] dinner;
    private LocalDate date;
    private String dayOfWeek;

    public Meal(String[] morning, String[] lunch, String[] dinner, LocalDate date, String days, int type) {
        this.morning = morning;
        this.lunch = lunch;
        this.dinner = dinner;
        this.date = date;
        this.dayOfWeek = days;
        this.type = type;
    }
}
