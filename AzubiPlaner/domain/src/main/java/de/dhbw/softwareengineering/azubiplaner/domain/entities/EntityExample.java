package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TableName")
public class EntityExample {
    @Id
    private String id;
    @Column
    private String name;
   

    public EntityExample(String id, String name) {
    
        this.id = id;
        this.name = name;

    }

    protected EntityExample() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

   
}
