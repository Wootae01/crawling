package hello.chunglim.service;

import hello.chunglim.domain.Meal;
import hello.chunglim.util.MealParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlingService {

    private final String URL = "https://dorm.chungbuk.ac.kr/home/sub.php";
    private final String MENUKEY = "menukey=20041";
    private final String TYPE = "type=";
    private final String[] NOMENU = {"등록된 식단이 없습니다."};

    private final MealParser mealParser;
    
    
    public String getUrl(int type) {
        return URL + "?" + MENUKEY + "&" + TYPE + type;
    }

    public Meal getTodayMeal(String mealURL) {
        //현재 날짜
        String date = getDate();
        String days = getDays();

        //전체 html 코드
        try {
            Document document = Jsoup.connect(mealURL).get();

            //아침, 점심, 저녁 메뉴 가져오기
            Element today = document.select("#" + date).first();

            String[] morning = getMenu(today, ".morning");
            String[] lunch = getMenu(today, ".lunch");
            String[] evening = getMenu(today, ".evening");


            return new Meal(morning, lunch, evening, date, days);

        } catch (IOException e) {
            return new Meal(NOMENU, NOMENU, NOMENU, date, days);
        } catch (Exception e) {
            return new Meal(NOMENU, NOMENU, NOMENU, date, days);
        }


    }

    private String[] getMenu(Element today, String cssQuery) {
        String mealInfo =  today.select(cssQuery).first().text();
        log.info("mealInfo={}", mealInfo);
        return mealParser.parse(mealInfo);
    }

    private static String getDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateTimeFormatter.format(now);
        return date;
    }

    private static String getDays() {
        String[] days = {"", "월", "화", "수", "목", "금", "토", "일"};
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        return days[dayOfWeek.getValue()];
    }
}
