package ie.mydashaboard.datadashboard.data;

public class CovidInput {
    private String id;
    private String location;
    private String date;
    private String variant;
    private String num_sequences;
    private String perc_sequences;
    private String total_sequences;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getVariant() {
        return variant;
    }
    public void setVariant(String variant) {
        this.variant = variant;
    }
    public String getNum_sequences() {
        return num_sequences;
    }
    public void setNum_sequences(String num_sequences) {
        this.num_sequences = num_sequences;
    }
    public String getPerc_sequences() {
        return perc_sequences;
    }
    public void setPerc_sequences(String perc_sequences) {
        this.perc_sequences = perc_sequences;
    }
    public String getTotal_sequences() {
        return total_sequences;
    }
    public void setTotal_sequences(String total_sequences) {
        this.total_sequences = total_sequences;
    }
   
}
