package model;

public class Passanger {
    private int ID_psg;
    private String Psg_name;
    private String Psg_phone_num;
    private int Psg_address;

    public Passanger( String psg_name, String psg_phone_num,int psg_address) {
        Psg_name = psg_name;
        Psg_phone_num = psg_phone_num;
        Psg_address = psg_address;
    }

    public Passanger(int ID_psg, String psg_name, String psg_phone_num, int psg_address) {
        this.ID_psg = ID_psg;
        Psg_name = psg_name;
        Psg_phone_num = psg_phone_num;
        Psg_address = psg_address;
    }

    public int getID_psg() {
        return ID_psg;
    }

    public void setID_psg(int ID_psg) {
        this.ID_psg = ID_psg;
    }

    public String getPsg_name() {
        return Psg_name;
    }

    public void setPsg_name(String psg_name) {
        Psg_name = psg_name;
    }

    public String getPsg_phone_num() {
        return Psg_phone_num;
    }

    public void setPsg_phone_num(String psg_phone_num) {
        Psg_phone_num = psg_phone_num;
    }

    public int getPsg_address() {
        return Psg_address;
    }

    public void setPsg_address(int psg_address) {
        this.Psg_address = psg_address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Passanger{");
        sb.append("ID='")
                .append(ID_psg)
                .append('\'');
        sb.append(", Name='")
                .append(Psg_name)
                .append('\'');
        sb.append(", Phone Number=").append(Psg_phone_num);
        sb.append(", Address=").append(Psg_address).append('\'');
        sb.append('}');
        sb.append("\n");

        return sb.toString();
    }
}
