package SapperPackage;

public class Game {
    private BombAndFlagClass bomb;
    private BombAndFlagClass flag;
    private WinLosePlaying state;
    private Coords firstClickCoords = null;
    private boolean firstClick = true;


    private int flagsLeft;
    public Game(int cols, int rows, int amountBombs) {
        Ranges.setSize(new Coords(cols,rows));
        bomb = new BombAndFlagClass(amountBombs);
        flag = new BombAndFlagClass(amountBombs);
        flagsLeft = amountBombs;

    }

    public WinLosePlaying getState() {
        return state;
    }

    public void start(){
      bomb.start();
      flag.start();
      state = WinLosePlaying.PLAYING;
      firstClickCoords = null;
    }
    public enumlist getBox(Coords coords){
        if (flag.getFlag(coords) == enumlist.emptyspot){
            return bomb.getBomb(coords);
        }
        else {
            return flag.getFlag(coords);
        }
    }
    private void winOrNot(){
        if (state == WinLosePlaying.PLAYING){
            if (flag.getCountClosed() == bomb.amountBombs()){
                state = WinLosePlaying.WIN;
            }
        }
    }
    private void open(Coords coords){
        switch (flag.getFlag(coords)){
            case emptyspot: return;
            case flag: return;
            case notopenedspot:
                switch (bomb.getBomb(coords)){
                    case zero :openNear(coords); return;
                    case hidingbomb:openBomb(coords); return;
                    default: flag.setOpened(coords);return;
                }

        }
    }

    private void openBomb(Coords bombsCoords) {
        state = WinLosePlaying.BOMBED;
        flag.setBomb(bombsCoords);
        for (Coords coords: Ranges.getAllCoords()){
            if (bomb.getBomb(coords) == enumlist.hidingbomb){
                flag.setOpenedToClosedBomb(coords);
            } else{
                flag.setNoBomb(coords);
            }
        }
    }

    private void openNear(Coords coords) {
        flag.setOpened(coords);
        for(Coords near : Ranges.getCoordsNear(coords)){
            open(near);
        }
    }

    public void pressLB(Coords coords) {
        if (end()) {
            return;
        }
        if (firstClickCoords == null) {
            firstClickCoords = coords;
            if (bomb.getBomb(firstClickCoords) == enumlist.hidingbomb) {
                while (bomb.getBomb(firstClickCoords) == enumlist.hidingbomb) {
                    bomb.start();
                }
            }
        }

        open(coords);
        winOrNot();
    }



    public void pressRB(Coords coords) {
        if (end()){
            return;
        }
        flag.setFlag(coords);
    }
    public void delFlag(Coords coords) {
        if (end()){
            return;
        }
        flag.setNonFlag(coords);
    }

    private boolean end(){
        if (state == WinLosePlaying.PLAYING){
            return false;
        }
        start();
        flagsLeft = bomb.amountBombs();
        return true;
    }
}
