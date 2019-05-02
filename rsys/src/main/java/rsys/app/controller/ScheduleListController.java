package rsys.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rsys.domain.model.Schedule;
import rsys.domain.model.User;
import rsys.domain.model.calendar.Calendar;
import rsys.domain.service.ScheduleService;
import rsys.domain.service.UserService;

@Controller
@RequestMapping("admin/schedule")
public class ScheduleListController {

	Calendar calendar;

	@Autowired
	private UserService userService;

	@Autowired
	private ScheduleService scheduleService;

	@GetMapping(value = {"", "index"})
	public String index(Model model) {
		LocalDate date = LocalDate.now();
		calendar = new Calendar(date);
		model.addAttribute("date", date);
		model.addAttribute("calendar", calendar.getCalendar());
		model.addAttribute("users", userService.findAll());
		return "admin/schedule/index";
	}

	@GetMapping(value = "{year}/{month}")
	public String slideCalendarByDiff(Model model,
			@PathVariable("year") String year, @PathVariable("month") String month) {
		calendar = new Calendar(Integer.parseInt(year), Integer.parseInt(month));
		model.addAttribute("date", LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1));
		model.addAttribute("calendar", calendar.getCalendar());
		return "admin/schedule/index";
	}

	@GetMapping(value = "{date}")
	public String selectedDateCalendar(Model model,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date) {
		calendar = new Calendar(date);
		List<Schedule> list = scheduleService.findSchedulesByDate(date);

		model.addAttribute("date", date);
		model.addAttribute("schedule", list);
		model.addAttribute("calendar", calendar.getCalendar());
		return "admin/schedule/dateSchedule";
	}

	@PostMapping(value = "user")
	public String selectedUsersSchedule(Model model, Integer userId) {
		/*System.out.println("userId" + userId);*/
		User user = userService.findOne(userId);
		List<Schedule> list = scheduleService.findSchedulesByUserId(userId);
		/*for(Schedule schedule : list) {
			System.out.println(schedule);
		}*/
		model.addAttribute("user", user);
		model.addAttribute("schedule", list);
		return "admin/schedule/userSchedule";
	}
}
