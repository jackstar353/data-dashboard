package ie.mydashaboard.datadashboard.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String countryName;
    private long totalCases;
    private String variantName;

    @Transient
    private List<Covid> cases;


  
    public Country(String countryName, long totalCases) {
        this.countryName = countryName;
        this.totalCases = totalCases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(long totalCases) {
        this.totalCases = totalCases;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    @Override
    public String toString() {
        return "Country [countryName=" + countryName + ", totalCases=" + totalCases + "]";
    }

    public Country() {
    }
    public List<Covid> getCases() {
        return cases;
    }

    public void setCases(List<Covid> cases) {
        this.cases = cases;
    }


}
