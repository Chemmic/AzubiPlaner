package de.dhbw.softwareengineering.azubiplaner.application.services;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import de.jollyday.HolidayManager;

@Service
public class HolidayService {
	private static final String REGION = "de-bw";
	
	
	public boolean isHoliday(LocalDateTime date) {
		HolidayManager manager = HolidayManager.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.set(date.getYear(), date.getMonth().getValue() -1 , date.getDayOfMonth());
		return manager.isHoliday(calendar, REGION);
	}
	
	
	public List<DayOfWeek> getValidDaysForWeek(LocalDateTime date) {
		LocalDateTime mondayOfGivenDay = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		List<DayOfWeek> validDays = new ArrayList<>();
		
		for(int i = DayOfWeek.MONDAY.getValue() -1 ; i <= DayOfWeek.FRIDAY.getValue() - 1; i++ ) {
			if(!isHoliday(mondayOfGivenDay.plusDays(i))) {
				validDays.add(mondayOfGivenDay.plusDays(i).getDayOfWeek());
			}
		}
		return validDays;
		
	}
}
