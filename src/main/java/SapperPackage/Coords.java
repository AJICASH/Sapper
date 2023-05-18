package SapperPackage;

public class Coords {
    public int x;
    public int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Coords){
            Coords coords = (Coords) obj;
            return coords.x == x && coords.y == y;
        }
        return super.equals(obj);
    }
}

