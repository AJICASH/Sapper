package SapperPackage;

class BombClass {
    private Matrix bombMatrix;
    private int amountBombs;

    BombClass (int amountBombs){
        this.amountBombs = amountBombs;
        changeAmountOfBombs();
    }
    void start (){
        bombMatrix = new Matrix(enumlist.notopenedspot);
        for (int i = 0; i < amountBombs; i++){
            placebomb();
        }
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
}
