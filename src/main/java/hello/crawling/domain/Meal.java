package hello.crawling.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Meal {
    private int type; //기숙사 정보
    private String[] morning; //아침 메뉴
    private String[] lunch; //점심 메뉴
    private String[] dinner; //저녁 메뉴
    private LocalDate date; //날짜
    private String dayOfWeek; //요일

    public Meal(String[] morning, String[] lunch, String[] dinner, LocalDate date, String days, int type) {
        this.morning = morning;
        this.lunch = lunch;
        this.dinner = dinner;
        this.date = date;
        this.dayOfWeek = days;
        this.type = type;
    }
}
