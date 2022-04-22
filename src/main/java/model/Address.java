package model;

public class Address {
    private int ID_address;
    private String Country;
    private String City;

    public Address(){

    };

    public Address( String country, String city) {
        Country = country;
        City = city;
    }

    public int getID_address() {
        return ID_address;
    }

    public void setID_address(int ID_address) {
        this.ID_address = ID_address;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("ID_address=").append(ID_address);
        sb.append(", Country='").append(Country).append('\'');
        sb.append(", City='").append(City).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
