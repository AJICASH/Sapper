package SapperPackage;

public class Game {
    Matrix bombMaxtrix;
    public Game(int cols, int rows) {
        Ranges.setSize(new Coords(cols,rows));
    }
    public void start(){
        bombMaxtrix = new Matrix(enumlist.emptyspot);
//        bombMaxtrix.setMatrix(new Coords(6,0), enumlist.hidingbomb);
    }
    public enumlist getBox(Coords coords){
        return bombMaxtrix.getMatrix(coords);
    }
}
