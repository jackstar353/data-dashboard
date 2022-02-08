package ie.mydashaboard.datadashboard.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Covid implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private int id;
    
    private String location;
    private LocalDate date;
    private String variant;
    private int numSequences;
    private Double percSequences;
    private int totalSequences;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getVariant() {
        return variant;
    }
    public void setVariant(String variant) {
        this.variant = variant;
    }
    public int getNumSequences() {
        return numSequences;
    }
    public void setNumSequences(int numSequences) {
        this.numSequences = numSequences;
    }
    public double getPercSequences() {
        return percSequences;
    }
    public void setPercSequences(Double percSequences) {
        this.percSequences = percSequences;
    }
    public int getTotalSequences() {
        return totalSequences;
    }
    public void setTotalSequences(int totalSequences) {
        this.totalSequences = totalSequences;
    }
   
    
}
