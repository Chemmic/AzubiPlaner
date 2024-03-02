package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.dhbw.softwareengineering.azubiplaner.domain.history.HistoryElement;

@Entity
@Table(name =  "History")
public class HistoryEntity {

	@OneToMany
	private List<? extends HistoryElement> entries;
	

}
