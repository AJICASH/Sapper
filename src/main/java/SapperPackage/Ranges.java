package SapperPackage;

import java.util.ArrayList;

public class Ranges {
    private static Coords size;
    public static ArrayList<Coords> allCoords;

    public static void setSize (Coords _size){
        size = _size;
        allCoords = new ArrayList<Coords>();
        for (int y = 0; y < size.y; y++){
            for (int x = 0; x < size.y; x++){
                if (y % 2 == 1){
                    allCoords.add(new Coords(x,y));
                }
                allCoords.add(new Coords(x,y));
            }
        }
    }
    public static Coords getSize(){
        return size;
    }
    public static ArrayList<Coords> getAllCoords (){
        return allCoords;
    }

}
