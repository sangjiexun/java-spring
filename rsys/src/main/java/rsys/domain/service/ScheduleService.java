package rsys.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rsys.domain.model.Schedule;
import rsys.domain.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleService {
	@Autowired
	ScheduleRepository scheduleRepository;

	public List<Schedule> findSchedules(LocalDate date){
		return scheduleRepository.findByScheduleId_workDateOrderByScheduleId_userIdAsc(date);
	}
}