
package LogicalLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentuj?ca map? z r??nymi krzes?ami i sto?ami.


 */
public class Map {

	public static final int FREE_FIELD = 0;
	public static final int TABLE = 1;
	public static final int GREEN_CHAIR = 2;
	public static final int RED_CHAIR = 3;
	public static final int CHAIR = 4;
	
	public static final int MAP_WIDTH = 21;
	public static final int MAP_HEIGHT = 17;

	
	private List<List<Object>> map;
	
	public Map(List<List<Object>> map){
		this.map = map;
	}
	
	/**
	 * Zwraca id obiektu znajduj?cego si? pod wskazanymi wsp??rz?dnymi na mapie.
	 * @param coordinates wsp??rzedne obiektu.
	 * @return zwraca id obiektu (patrz sta?e w klasie Map) lub -1, je?li podane wsp??rzedne wykraczaj?
	 * poza map?.
	 */
	public Object getObjectId(Coordinates coordinates){
		if(!checkIfCoordinatesAreInMap(coordinates)) return null;
		
		return map.get(coordinates.getRow()).get(coordinates.getColumn());
	}
	/**
	 * Zmienia stan krzes?a na zielone, czerwone albo zwyk?e.
	 * @param coordinates wsp??rz?dne krzes?a
	 * @param chairState stan, w kt?ry wej?? ma krzes?o (patrz sta?e w klasie Map).
	 * @return false, je?li podane wsp??rz?dne wykraczaj? poza map?, albo pod podanymi wsp??rzednymi
	 * nie ma krzes?a, w przeciwnym przypadku true.
	 */
	public boolean setChairAs(Coordinates coordinates, int chairState){
		Object id = getObjectId(coordinates);
		if(id == null) return false;
		if(! (id instanceof Seat)) return false;
	//	if(id != GREEN_CHAIR && id != RED_CHAIR && id != CHAIR) return false;
		if(chairState != GREEN_CHAIR && chairState != RED_CHAIR && chairState != CHAIR) return false;
		//map.get(coordinates.getRow()).remove(coordinates.getColumn());
		//map.get(coordinates.getRow()).add(coordinates.getColumn(), chairState);
		((Seat) id).setState(chairState);
		return true;
	}
	
	/**
	 * Zwraca list? wsp??rzednych wszystkich sto??w na mapie.
	 * @return lista wsp??rzednych wszystkich sto??w.
	 */
	public List<Coordinates> getAllTablesCoordinates(){
		List<Coordinates> list = new ArrayList<Coordinates>();
		for(int i = 0; i < MAP_HEIGHT; i++){
			for(int j = 0; j < MAP_WIDTH; j++){
				if(map.get(i).get(j) instanceof Table)
					list.add(new Coordinates(i, j));
			}
		}
		return list;
	}

	/**
	 * Zwraca koordynaty sto?u o zadanym numerze.
	 * @param tableNumber numer sto?u
	 * @return koordynaty sto?u o zadanym numerze, null, je?li nie ma sto?u o takim numerze
	 */
	public Coordinates getCoordinatesForTableNumber(int tableNumber){
		List<Coordinates> tables = getAllTablesCoordinates();

		for(Coordinates coordinates : tables){
			Table table = (Table) getObjectId(coordinates);

			if(table.getTableNumber() == tableNumber){
				return coordinates;
			}
		}
		return null;
	}

	public List<Coordinates> getAllCoordinates(){
		List<Coordinates> list = new ArrayList<Coordinates>();
		for(int i = 0; i < MAP_HEIGHT; i++){
			for(int j = 0; j < MAP_WIDTH; j++){
				//if(map.get(i).get(j) == TABLE)
					list.add(new Coordinates(i, j));
			}
		}
		return list;
	}
	
	public static boolean checkIfCoordinatesAreInMap(Coordinates coordinates){
		return (coordinates.getRow() >= 0 && coordinates.getRow() < MAP_HEIGHT &&
				coordinates.getColumn() >= 0 && coordinates.getColumn() < MAP_WIDTH);
	}

	public List<List<Object>> getMap(){
		return map;
	}


	
}