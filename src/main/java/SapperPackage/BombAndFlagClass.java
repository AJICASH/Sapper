package SapperPackage;

class BombAndFlagClass {
    private Matrix bombMatrix;
    private int amountBombs;
    private int countClosed;
    private Matrix flagMatrix;
    BombAndFlagClass(int amountBombs){
        this.amountBombs = amountBombs;
        changeAmountOfBombs();
    }
    public int amountBombs(){
        return amountBombs;
    }
    void start (){
        bombMatrix = new Matrix(enumlist.zero);
        for (int i = 0; i < amountBombs; i++){
            placebomb();
        }
        flagMatrix = new Matrix(enumlist.notopenedspot);
        countClosed = Ranges.getSize().x * Ranges.getSize().y;
    }
    enumlist getBomb(Coords coords){
        return bombMatrix.getMatrix(coords);
    }
    private void placebomb(){
        while (true){
            Coords coords = Ranges.getRndCoords();
            if (enumlist.hidingbomb == bombMatrix.getMatrix(coords)){
                continue;
            }
            bombMatrix.setMatrix(coords, enumlist.hidingbomb);
            increaseNumbersNearBomb(coords);
            break;
        }

    }
    private void changeAmountOfBombs(){
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (amountBombs > maxBombs){
            amountBombs = maxBombs;
        }
    }
    private void increaseNumbersNearBomb(Coords coords){
        for (Coords near: Ranges.getCoordsNear(coords)){
            if (enumlist.hidingbomb != bombMatrix.getMatrix(near)){
                bombMatrix.setMatrix(near, bombMatrix.getMatrix(near).getNextNumber());
            }
        }
    }
    enumlist getFlag (Coords coords){
        return flagMatrix.getMatrix(coords);
    }
    public void setOpened(Coords coords) {
        flagMatrix.setMatrix(coords, enumlist.emptyspot);
        countClosed--;
    }

    public void setFlag(Coords coords) {
        flagMatrix.setMatrix(coords, enumlist.flag);
    }
    public void setNonFlag(Coords coords) {
        flagMatrix.setMatrix(coords, enumlist.notopenedspot);
    }

    public int getCountClosed() {
        return countClosed;
    }

    public void setBomb(Coords coords) {
        flagMatrix.setMatrix(coords,enumlist.explosion);
    }

    public void setOpenedToClosedBomb(Coords coords) {
        if (flagMatrix.getMatrix(coords) == enumlist.notopenedspot){
            flagMatrix.setMatrix(coords, enumlist.emptyspot);
        }
    }

    public void setNoBomb(Coords coords) {
        if (flagMatrix.getMatrix(coords) == enumlist.flag){
            flagMatrix.setMatrix(coords, enumlist.defusedbomb);
        }
    }
}
