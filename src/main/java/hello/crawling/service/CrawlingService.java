package hello.crawling.service;

import hello.crawling.domain.Meal;
import hello.crawling.util.MealParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlingService {

    //url 설정에 필요한 상수
    private final String URL = "https://dorm.chungbuk.ac.kr/home/sub.php";
    private final String MENUKEY = "menukey=20041";
    private final String TYPE = "type=";
    private final String CUR_DAY = "cur_day=";

    private final MealParser mealParser;
    private final String[] NOMENU = {"등록된 식단이 없습니다."};
    
    //url 얻는 메서드
    public String getUrl(int type, LocalDate date) {
        String format = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return URL + "?" + MENUKEY + "&" + CUR_DAY + format + "&" + TYPE + type;
    }


    public Meal getMeal(String url, LocalDate date, int type) {
        String format = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dayOfWeek = getDayOfWeek(date);

        try{
            Document document = Jsoup.connect(url).get();

            Element today = document.select("#" + format).first(); //html 요소가 현재 날짜인 부분 가져온다.

            String[] morning = getMenu(today, ".morning"); //아침 메뉴
            String[] lunch = getMenu(today, ".lunch"); //점심 메뉴
            String[] evening = getMenu(today, ".evening"); //저녁 메뉴

            return new Meal(morning, lunch, evening, date, dayOfWeek, type);
        }catch (IOException e) {
            return new Meal(NOMENU, NOMENU, NOMENU, date, dayOfWeek, type);
        } catch (NullPointerException e) {
            //오늘 날짜에 해당하는 식단이 없으면 NullPointerException 발생
            log.error("today is null={}", e);
            return new Meal(NOMENU, NOMENU, NOMENU, date, dayOfWeek, type);
        }
    }

    private String[] getMenu(Element today, String cssQuery) {
        String mealInfo =  today.select(cssQuery).first().text();
        log.info("mealInfo={}", mealInfo);
        return mealParser.parse(mealInfo);
    }

    private static String getDayOfWeek(LocalDate date) {
        String[] days = {"", "월", "화", "수", "목", "금", "토", "일"};
        return days[date.getDayOfWeek().getValue()];
    }
}
