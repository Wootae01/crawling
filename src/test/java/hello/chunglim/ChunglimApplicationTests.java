package hello.chunglim;

import hello.chunglim.domain.Meal;
import hello.chunglim.service.CrawlingService;
import hello.chunglim.util.MealParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

class ChunglimApplicationTests {

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
