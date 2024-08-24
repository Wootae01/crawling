package hello.chunglim;

import hello.chunglim.domain.Meal;
import hello.chunglim.service.CrawlingService;
import hello.chunglim.util.MealParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChunglimApplicationTests {

	@Autowired
	CrawlingService crawlingService;

	@Test
	public void getURL() {
		for (int i = 0; i < 10; i++) {
			System.out.println("URL = " + crawlingService.getUrl(3));
		}
	}

	@Test
	public void getMenuData() {
		Meal todayMenu = crawlingService.getTodayMeal(crawlingService.getUrl(3));
		System.out.println(todayMenu);

	}
}
