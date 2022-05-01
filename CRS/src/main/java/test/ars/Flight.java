
package test.ars;
public class Flight {
    int flightNumber;
    String airlineName;
    String source;
    String destination;
    int capacity;

    public Flight(int fNo,String aName,String src,String dest,int capacity)
    {
        this.flightNumber=fNo;
        this.airlineName=aName;
        this.source = src;
        this.destination = dest;
        this.capacity = capacity;
    }
    public int getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getAirlineName() {
        return airlineName;
    }
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}
