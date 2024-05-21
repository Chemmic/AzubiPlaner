package de.dhbw.softwareengineering.azubiplaner.plugins.unitTests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.application.rules.BaseRule;
import de.dhbw.softwareengineering.azubiplaner.application.rules.FridayRule;
import de.dhbw.softwareengineering.azubiplaner.application.rules.NonConsecutiveDaysRule;
import de.dhbw.softwareengineering.azubiplaner.application.services.KuechendienstService;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Kuechendienst;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstDay;
import de.dhbw.softwareengineering.azubiplaner.domain.values.Email;

@Testable
@SpringBootTest
public class KuechendienstServiceTest {
	
  
    @InjectMocks
    private KuechendienstService kuechendienstService;
    


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    List<DayOfWeek> validDays = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
    Map<DayOfWeek, Integer> dayOfWeekOrder = Map.of(
            DayOfWeek.MONDAY, 1,
            DayOfWeek.TUESDAY, 2,
            DayOfWeek.WEDNESDAY, 3,
            DayOfWeek.THURSDAY, 4,
            DayOfWeek.FRIDAY, 5,
            DayOfWeek.SATURDAY, 6,
            DayOfWeek.SUNDAY, 7
        );

    @Test
    public void testGenerateKuechendienstWithBothRules() {
        List<HelpEntityObject> candidates = new ArrayList<>();
        ArrayList<BaseRule> list = new ArrayList<>();
        list.add(new NonConsecutiveDaysRule());
        list.add(new FridayRule());
        kuechendienstService = new KuechendienstService(list);
        Angestellter e1 = new Angestellter();
        Angestellter e2 = new Angestellter();
        assertDoesNotThrow(() -> {
        	e1.setEmail(new Email("test1@test.de"));
        	e1.setUsername("Test1");
        	
        	e2.setEmail(new Email("test2@test.de"));
        	e2.setUsername("Test2");
        });
        
        
        HelpEntityObject candidate1 = new HelpEntityObject(e1);
        HelpEntityObject candidate2 = new HelpEntityObject(e2);
        candidates.add(candidate1);
        candidates.add(candidate2);

    //    when(candidate1.getAmountOfWorkDays()).thenReturn(0);
     //   when(candidate2.getAmountOfWorkDays()).thenReturn(0);


        //Soritieren der Ergebnisse

        Kuechendienst result = kuechendienstService.generateKuechendienst(candidates, validDays);
        List<KuechendienstDay> sortedDayEntities = result.getDayEntities().stream()
                .sorted(Comparator.comparingInt(dayEntity -> 
                    dayOfWeekOrder.get(dayEntity.getDayOfWeek())))
                .collect(Collectors.toList());
        assertNotNull(sortedDayEntities);
        assertEquals(5, sortedDayEntities.size());

		/* 	Erwartete Reihenfolge mit beiden Regeln:
		 *  Montag: Person 1
		 *  Dienstag: Person 2
		 *  Mittwoch: Person 1
		 *  Donnerstag: Person 2
		 *  Freitag: Person 1
		 */
        assertEquals(e1, sortedDayEntities.get(0).getResponsibleEmployee());
        assertEquals(e2, sortedDayEntities.get(1).getResponsibleEmployee());
        assertEquals(e1, sortedDayEntities.get(2).getResponsibleEmployee());
        assertEquals(e2, sortedDayEntities.get(3).getResponsibleEmployee());
        assertEquals(e1, sortedDayEntities.get(4).getResponsibleEmployee());
    	}


    @Test
    public void testGenerateKuechendienstWithNoRules() {
    	 kuechendienstService = new KuechendienstService(new ArrayList<>());
        List<HelpEntityObject> candidates = new ArrayList<>();
        Angestellter e1 = new Angestellter();
        Angestellter e2 = new Angestellter();
        assertDoesNotThrow(() -> {
        	e1.setEmail(new Email("test1@test.de"));
        	e1.setUsername("Test1");
        	
        	e2.setEmail(new Email("test2@test.de"));
        	e2.setUsername("Test2");
        });
        
        
        HelpEntityObject candidate1 = new HelpEntityObject(e1);
        HelpEntityObject candidate2 = new HelpEntityObject(e2);
        candidates.add(candidate1);
        candidates.add(candidate2);



        //Soritieren der Ergebnisse

        Kuechendienst result = kuechendienstService.generateKuechendienst(candidates, validDays);
        List<KuechendienstDay> sortedDayEntities = result.getDayEntities().stream()
                .sorted(Comparator.comparingInt(dayEntity -> 
                    dayOfWeekOrder.get(dayEntity.getDayOfWeek())))
                .collect(Collectors.toList());
		/* 	Erwartete Reihenfolge mit KEINEN Regeln (es wird versucht Person 1 und 2 gleichmäßig zu verteilen):
		 *  Montag: Person 1
		 *  Dienstag: Person 2
		 *  Mittwoch: Person 2
		 *  Donnerstag: Person 1
		 *  Freitag: Person 1
		 */
        assertNotNull(sortedDayEntities);
        assertEquals(5, sortedDayEntities.size());
        assertEquals(e1, sortedDayEntities.get(0).getResponsibleEmployee());
        assertEquals(e2, sortedDayEntities.get(1).getResponsibleEmployee());
        assertEquals(e2, sortedDayEntities.get(2).getResponsibleEmployee());
        assertEquals(e1, sortedDayEntities.get(3).getResponsibleEmployee());
        assertEquals(e1, sortedDayEntities.get(4).getResponsibleEmployee());
    	}
    

}
