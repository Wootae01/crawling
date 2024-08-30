package hello.chunglim.web;

import hello.chunglim.domain.Meal;
import hello.chunglim.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MealController {

    private final CrawlingService crawlingService;

    @RequestMapping("/")
    public String home() {
        return "forward:/todayMeal";
    }
    @GetMapping("/todayMeal/{type}")
    public String todayMeal(Model model, @PathVariable("type") int type) {
        String url = crawlingService.getUrl(type, LocalDate.now());
        Meal todayMeal = crawlingService.getMeal(url, LocalDate.now(), type);
        model.addAttribute("data", todayMeal);

        return "todayMeal";
    }

    @GetMapping("/todayMeal")
    public String todayMeal(Model model) {
        String url = crawlingService.getUrl(1, LocalDate.now());
        Meal todayMeal = crawlingService.getMeal(url, LocalDate.now(), 1);
        model.addAttribute("data", todayMeal);

        return "todayMeal";
    }

    @GetMapping("/todayMeal/{type}/{date}")
    public String dateMeal(@PathVariable("type") int type, @PathVariable("date") LocalDate date, Model model) {
        String url = crawlingService.getUrl(type, date);
        Meal meal = crawlingService.getMeal(url, date, type);
        model.addAttribute("data", meal);
        return "todayMeal";
    }
}
