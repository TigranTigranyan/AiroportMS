package model;

import java.time.LocalDate;

public class Company {
    private int ID_Comp;
    private String Comp_name;
    private LocalDate Founding_date;

    public Company(String comp_name, LocalDate founding_date) {
        Comp_name = comp_name;
        Founding_date = founding_date;
    }
    public Company(){

    };

    public Company(int ID_Comp, String comp_name, LocalDate founding_date) {
        this.ID_Comp = ID_Comp;
        Comp_name = comp_name;
        Founding_date = founding_date;
    }

    public int getID_Comp() {
        return ID_Comp;
    }

    public void setID_Comp(int ID_Comp) {
        this.ID_Comp = ID_Comp;
    }

    public String getComp_name() {
        return Comp_name;
    }

    public void setComp_name(String comp_name) {
        Comp_name = comp_name;
    }

    public LocalDate getFounding_date() {
        return Founding_date;
    }

    public void setFounding_date(String founding_date) {
        Founding_date = LocalDate.parse(founding_date);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("ID_Comp=").append(ID_Comp);
        sb.append(", Comp_name='").append(Comp_name).append('\'');
        sb.append(", Founding_date='").append(Founding_date).append('\'');
        sb.append("}\n");
        return sb.toString();
    }
}
