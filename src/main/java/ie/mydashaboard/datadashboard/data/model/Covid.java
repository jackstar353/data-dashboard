package ie.mydashaboard.datadashboard.data.model;

import java.time.LocalDate;

public class Covid {

    private String location;
    private LocalDate date;
    private String variant;
    private int numSequences;
    private int percSequences;
    private int numSequencestotal;
    
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
    public int getPercSequences() {
        return percSequences;
    }
    public void setPercSequences(int percSequences) {
        this.percSequences = percSequences;
    }
    public int getNumSequencestotal() {
        return numSequencestotal;
    }
    public void setNumSequencestotal(int numSequencestotal) {
        this.numSequencestotal = numSequencestotal;
    }
    
}
