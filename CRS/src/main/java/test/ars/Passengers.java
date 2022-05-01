package test.ars;


import java.util.Date;

public class Passengers {
    int flightNumber;
    int ticketNumber;
    String fullName;
    String passportNumber;
    String nationality;
    Date birthDate;


    public Passengers(int flightNumber, int ticketNumber, String fullName, String passportNumber, String nationality,
                      Date birthDate) {
        super();
        this.flightNumber = flightNumber;
        this.ticketNumber = ticketNumber;
        this.fullName = fullName;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.birthDate = birthDate;
    }
    public int getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
    public int getTicketNumber() {
        return ticketNumber;
    }
    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


}
