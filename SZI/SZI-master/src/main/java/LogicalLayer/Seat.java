package LogicalLayer;


public class Seat {

    //Koordynaty
    protected Coordinates coords;

    private int state;  //stan krzes�a

    public int getState() {
        return state;
    }

    /**
     * @author Dominik Demski
     * @param state

     */
    public void setState(int state) {
        this.state = state;
    }

    public Seat ( Coordinates coords ) {
        this.coords = coords;
    }

    //ustawia koordynaty krzesla
    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }

    //zwraca koordynaty krzesla
    public Coordinates getCoords() {
        return this.coords;
    }


}
