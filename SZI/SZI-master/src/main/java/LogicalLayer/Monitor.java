
package LogicalLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dominik Demski
 * klasa rejestruj�ca s�uchaczy zdarzenia przemieszczenia si� kelnera
 * i informuj�ca ich o przemieszczeniu si�.
 */
public class Monitor {
	private List<OnMoveListener> listeners = new ArrayList<OnMoveListener>();
	
	/**
	 * Rejestruje s�uchacza zdarzenia przemieszczenia si� kelnera (musi on implementowa�
	 * interfejs listener).
	 * @param listener s�uchacz
	 */
	public void registerListener(OnMoveListener listener){
		listeners.add(listener);
	}
	
	/**
	 * Zawiadamia wszystkich s�uchaczy o przemieszczeniu si� kelnera
	 * (podaje �cie�k� przej�cia).
	 * @param path �cie�ka, kt�r� pokona� kelner.
	 */
	public void callListenersOnMove(List<Coordinates> path){
		for(OnMoveListener listener : listeners){
			listener.onMove(path);
		}
	}
	
	private static Monitor instance;
	private Monitor(){}
	
	public static Monitor getInstance(){
		if (instance == null) instance = new Monitor();
		return instance;
	}
}
