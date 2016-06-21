package LogicalLayer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class OrdersFactory implements Runnable {
    public void run() {
        int counter = 0;
        while(true/*counter < 3*/){
            try {
                TimeUnit.SECONDS.sleep(15);
                OrdersService ordersService = null;
                try {
                    ordersService = OrdersService.getInstance();
                    ordersService.makeOrder(10);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter ++;
        }
    }
}
