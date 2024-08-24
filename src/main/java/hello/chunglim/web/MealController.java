package hello.chunglim.web;

import hello.chunglim.domain.Meal;
import hello.chunglim.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MealController {

    private final CrawlingService crawlingService;

    @RequestMapping("/todayMeal/{type}")
    public String todayMeal(Model model, @PathVariable("type") int type) {
        String url = crawlingService.getUrl(type);
        Meal todayMeal = crawlingService.getTodayMeal(url);
        model.addAttribute("data", todayMeal);

        return "todayMeal";
    }
    @RequestMapping("/todayMeal")
    public String todayMeal(Model model) {
        String url = crawlingService.getUrl(1);
        Meal todayMeal = crawlingService.getTodayMeal(url);
        model.addAttribute("data", todayMeal);

        return "todayMeal";
    }

}
