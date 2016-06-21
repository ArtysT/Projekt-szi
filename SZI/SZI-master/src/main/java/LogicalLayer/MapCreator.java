
package LogicalLayer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * klasa wczytuj�ca map� z pliku i przekszta�acj�ca j� na map� z krzes�ami
 */
public class MapCreator {
	
	private List<List<Object>> map; //numer wiersza <numery kolumn>
	private final int TABLE = 1;
	private final int NOTHING = 0;
	
	/**
	 * Wczytuje map� z pliku dla zadanej �cie�ki do pliku.
	 * @param path �cie�ka do pliku
	 * @throws IOException wyj�tek zostaje wyrzucony, je�li nie ma pliku o podanej �cie�ce.
	 */
	public void loadMapFromFile(Path path) throws IOException{
		 map = new ArrayList<List<Object>>(Map.MAP_HEIGHT);
		Scanner in = new Scanner(path);
		int tableNumber = 1;											//sto�y s� numerowane zawsze od jedynki
		for(int i = 0; i < Map.MAP_HEIGHT; i++){
			List<Object> columns = new ArrayList<Object>(Map.MAP_WIDTH);
			if(! in.hasNextLine()){ 
				in.close();
				map = null;
				throw new IllegalArgumentException("Za ma�o wierszy, mapa powinna mie�"
					+ "wysoko�� = " + Map.MAP_HEIGHT);
			}
			for(int j = 0; j < Map.MAP_WIDTH; j++){
				if(! in.hasNextInt()){
					in.close();
					map = null;
					throw new IllegalArgumentException("Za ma�o kolumn w wierszu "
							+ (i + 1) + " powinno by� " + Map.MAP_WIDTH + " kolumn!");
				}
				int number = in.nextInt();
				if(number != TABLE && number!= NOTHING){
					in.close();
					map = null;
					throw new IllegalArgumentException("Plik z map� ma z�y format powinien zawiera�"
							+ "tylko " + TABLE + " i " + NOTHING + "(b��d w wierszu: " + (i + 1) +
							" kolumnie: " + (j + 1));
				}
				else{
					Object toAdd;
					if(number == TABLE){
						toAdd = new Table(tableNumber, new Coordinates(i, j));
						tableNumber++;
					}
					else{
						toAdd = NOTHING;
					}
						columns.add(toAdd);
				}
			}
			map.add(columns);
		}
		in.close();
	}
	
	public List<List<Object>> getMap() {
		return map;
	}

	/**
	 * Dodaje do wczytanej wcze�niej mapy krzes�a.
	 * @return false, je�li nie wczytano wcze�niej mapy (za pomoc� metody loadMapFromFile)
	 */
	public boolean createMapWithChairs(){
		if(map == null) return false;
		
		for(int i = 0; i < map.size(); i++){
			for(int j = 0; j < map.get(i).size(); j++){
				if(map.get(i).get(j) instanceof Table){
					if(i - 1 >= 0){    //je�eli istnieje mapa w g�r�
						if(! (map.get(i - 1).get(j) instanceof Table)){  //je�eli tam nie ma sto�u
							map.get(i - 1).remove(j);
							Seat seat = new Seat(new Coordinates(i - 1, j));
							seat.setState(Map.CHAIR);
							map.get(i - 1).add(j, seat);
						}
						if(i + 1 < Map.MAP_HEIGHT){  //w d�
							if(! (map.get(i + 1).get(j) instanceof Table)){  //je�eli tam nie ma sto�u
								map.get(i + 1).remove(j);
								Seat seat = new Seat(new Coordinates(i + 1, j));
								seat.setState(Map.CHAIR);
								map.get(i + 1).add(j, seat);
							}
						}
						if(j - 1 >= 0){   // w lewo
							if(! (map.get(i).get(j - 1) instanceof Table)){  //je�eli tam nie ma sto�u
								map.get(i).remove(j - 1);
								Seat seat = new Seat(new Coordinates(i, j - 1));
								seat.setState(Map.CHAIR);
								map.get(i).add(j - 1, seat);
							}
						}
						if(j + 1 < Map.MAP_WIDTH){   // w prawo
							if(! (map.get(i).get(j + 1) instanceof Table)){  //je�eli tam nie ma sto�u
								map.get(i).remove(j + 1);
								Seat seat = new Seat(new Coordinates(i, j + 1));
								seat.setState(Map.CHAIR);
								map.get(i).add(j + 1, seat);
							}
						}
					}
				}
			}
		}
		return true;	
	}

	public static void main(String[] args) {
		/*
		MapCreator mapCreator = new MapCreator();
		try {
			mapCreator.loadMapFromFile(Paths.get(Control.FILE_PATH));
			mapCreator.createMapWithChairs();
			List<List<Object>> map = mapCreator.getMap();

			for(List<Object> l : map){
				for(Integer i : l){
					System.out.print(i + " ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	}
}
