package SapperPackage;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coords size;
    public static ArrayList<Coords> allCoords;
    private static Random random = new Random();
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
    static boolean inRange(Coords coords){
        return coords.x >= 0 && coords.x < size.x && coords.y >= 0 && coords.y < size.y;
    }
    static Coords getRndCoords(){
        return new Coords(random.nextInt(size.x),random.nextInt(size.y));
    }
    static ArrayList<Coords> getCoordsNear(Coords coords){
        Coords near;
        ArrayList<Coords> list = new ArrayList<Coords>();
        if (coords.y % 2 == 0){
            for (int i = coords.x - 1; i <= coords.x + 1; i++ ){
                for (int j = coords.y - 1; j <= coords.y + 1; j++ ) {
                    boolean flag = true;
                    if (j != coords.y && i == coords.x + 1){
                        flag = false;
                    }
                    if (inRange(near = new Coords(i,j)) && flag){
                        if (!near.equals(coords)){
                            list.add(near);
                        }
                    }
                }
            }
        }
        else {
            for (int i = coords.x - 1; i <= coords.x + 1; i++ ){
                for (int j = coords.y - 1; j <= coords.y + 1; j++ ) {
                    boolean flag = true;
                    if (j != coords.y && i == coords.x - 1){
                        flag = false;
                    }
                    if (inRange(near = new Coords(i,j)) && flag){
                        if (!near.equals(coords)){
                            list.add(near);
                        }
                    }
                }
            }
        }
        return list;
    }
}
