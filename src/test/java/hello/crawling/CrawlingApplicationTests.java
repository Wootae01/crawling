package hello.crawling;

import hello.crawling.domain.Meal;
import hello.crawling.service.CrawlingService;
import hello.crawling.util.MealParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CrawlingApplicationTests {

	CrawlingService crawlingService = new CrawlingService(new MealParser());

	@Test
	public void getURL() {
		for (int i = 0; i < 10; i++) {
			System.out.println("URL = " + crawlingService.getUrl(3, LocalDate.now().minusDays(15)));
		}
	}

	@Test
	public void getMenuData() {
		String url = crawlingService.getUrl(3, LocalDate.now().minusDays(15));
		Meal todayMenu = crawlingService.getMeal(url, LocalDate.now().minusDays(15), 3);
		System.out.println(todayMenu);

	}
}
