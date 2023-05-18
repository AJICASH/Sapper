package SapperPackage;

public class Game {
    private BombClass bomb;
    public Game(int cols, int rows, int amountBombs) {
        Ranges.setSize(new Coords(cols,rows));
        bomb = new BombClass(amountBombs);
    }
    public void start(){
      bomb.start();
    }
    public enumlist getBox(Coords coords){
        return bomb.getBomb(coords);
    }
}
