package de.dhbw.softwareengineering.azubiplaner.application.unitTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import de.dhbw.softwareengineering.azubiplaner.application.services.HolidayService;

@Testable
public class HolidayServiceTest {

    private static HolidayService holidayService;

    @BeforeAll
    public static void setup() {
        holidayService = new HolidayService();
    }

	@Test
	public void testIsHoliday() {
		  LocalDateTime christmas = LocalDateTime.of(2024, 12, 25, 0, 0);
	        assertTrue(holidayService.isHoliday(christmas), "Weihnachten sollte ein Feiertag sein");

	        LocalDateTime normalDay = LocalDateTime.of(2024, 12, 22, 0, 0); 
	        assertFalse(holidayService.isHoliday(normalDay), "Der 22.12 sollte kein Feiertag sein");
		
	}
}
