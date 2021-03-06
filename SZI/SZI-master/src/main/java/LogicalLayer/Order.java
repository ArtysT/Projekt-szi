package LogicalLayer;

import java.util.Calendar;

public class Order {

    //------------------------------------------------------------------------------------------------------------------
    //AL 2015-06-06
    int tableNumber; //nr stolika
    Meal meal;  //danie z menu
    long orderTime; //czas zamowienia, jako int, podlagajacy inkrementacji
    int prepareTime; //czas przygotowania, wywodzi sie z menu
    boolean VIP; //czy zamowienie jest dla VIPA
    boolean liquid; //czy jest to napoj (ma znaczenie w drzewie decyzyjnym)


    public Order(Meal meal, int tableNumber, long orderTime, boolean VIP){
        this.tableNumber = tableNumber;
        this.meal = meal;
        prepareTime = meal.getTime();
        liquid = meal.getType() == 0;
        this.orderTime = orderTime;
        this.VIP = VIP;
    }

    public boolean isLiquid(){
        return liquid;
    }

    public boolean isVIP(){
        return VIP;
    }

    public boolean isLongPreparedTime(){
        return prepareTime > 5;
    }

    public boolean isWaitedLong(){
        Calendar cal = Calendar.getInstance();
        long now = cal.getTimeInMillis();
        if ((orderTime - now)/8000 > 0) return true;
        return false;
    }
    public Meal getMeal(){
        return meal;
    }

    public boolean equals(Object order){
        if (!(order instanceof Order)) return false;
        if ((this.tableNumber == ((Order) order).tableNumber) &&
                (this.meal.equals(((Order)order).meal)) &&
                (this.orderTime == ((Order)order).orderTime) &&
                (this.VIP == ((Order)order).VIP)) {
            return true;
        }
        return false;
    }

    public int hashCode(){
        return (int) orderTime;
    }

}
