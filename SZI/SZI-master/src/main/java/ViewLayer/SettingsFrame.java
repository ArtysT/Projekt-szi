package ViewLayer;

import LogicalLayer.IfWaiterGoThread;
import LogicalLayer.OrdersFactory;
import LogicalLayer.decisionTree.CurrentCreatingMeal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingsFrame extends JPanel {

    private JButton start;
    private MapPanel mapPanel;
    private static JRadioButton Ag;
    private static JRadioButton Gen;

    private static ExecutorService threads = Executors.newCachedThreadPool();

    public SettingsFrame(final MapPanel mapPanel){

        this.mapPanel = mapPanel;
        setBackground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        Ag = new JRadioButton("A*");
        Gen = new JRadioButton("Algorytm Genetyczny");

        ButtonGroup algorithmsButtons = new ButtonGroup();
        algorithmsButtons.add(Ag);
        algorithmsButtons.add(Gen);

        this.add(Ag);
        this.add(Gen);


        start = new JButton("START");
        /*start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mapPanel.waiterCoordinates.setColumn(1);
                mapPanel.waiterCoordinates.setRow(1);
                mapPanel.play();
                mapPanel.run();
            }
        });*/ //to powodowalo ze klient sie ruszal po przycisnieciu start, teraz jest juz to niepotrzebne

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getSelectedAlgorithms().equals("Ag") || getSelectedAlgorithms().equals("Gen")){
                    threads.execute(new OrdersFactory()); //sluzy do losowania zamowien
                    //---------------------------------------------------------------------
                    //takie sobie przyjmuje zalozenie ze mam 5 dzialajacych kucharzy
                    threads.execute(new CurrentCreatingMeal());
                    //threads.execute(new CurrentCreatingMeal());
                    //threads.execute(new CurrentCreatingMeal());
                    //threads.execute(new CurrentCreatingMeal());
                    //threads.execute(new CurrentCreatingMeal());
                    //---------------------------------------------------------------------
                    threads.execute(new IfWaiterGoThread());
                }

            }
        });

        add(start);

    }

    public static String getSelectedAlgorithms(){
        if (Ag.isSelected()) {
            return "Ag";
        }else if (Gen.isSelected()){
            return "Gen";
        }else{
            return "Brak";
        }

    }


}
