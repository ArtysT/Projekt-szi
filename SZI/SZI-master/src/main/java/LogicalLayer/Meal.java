
package LogicalLayer;


public class Meal {
    private final String name; //nazwa posi�ku
    private String ingredients; // sk�adniki posi�ku 
    private int type; //typ konstystencji: 0 - p�ynna, 1 - p�ynna, ale g�sta, 2- sta�a 
    private int temp; // temperatura podania: 0 - na zimno, 1 - tempreatura pokojowa, 2 - ciep�e, ale nie gor�ce, 3 - gor�ce
    private int area; // pole powierzchni - ile talerz z posi�kiem zajmuje miejsca
    private int time; // czas przygotowania, prawdopodobnie b�dzie podawany w sekundach
    
    public Meal(String name,String ingredients, int type, int temp, int area, int time){
        this.name = name;
        this.ingredients = ingredients;
        this.type = type;
        this.temp = temp;
        this.area = area;
        this.time = time;
    }
    
    public String getName(){
        return name;
    }
    
    public String getIngredients(){
        return ingredients;
    }
    
    public int getType(){
        return type;
    }
    
    public int getTemp(){
        return temp;
    }
    
    public int getArea(){
        return area;
    }
    
    public int getTime(){
        return time;
    }

    public boolean equals(Meal meal){
        if (this.name.equals(meal.getName()) &&
                this.ingredients.equals(meal.getIngredients()) &&
                this.type == meal.getType() &&
                this.temp == meal.getTemp() &&
                this.area == meal.getArea() &&
                this.time == meal.getTime() ) return true;
        return false;
    }
}
