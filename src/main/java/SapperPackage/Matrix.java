package SapperPackage;

class Matrix {
    private enumlist [][] matrix;

    Matrix(enumlist box) {
        matrix = new enumlist[Ranges.getSize().x][Ranges.getSize().y];
        for (Coords coords: Ranges.getAllCoords()){
            matrix [coords.x][coords.y] = box;
        }
    }

    enumlist getMatrix(Coords coords) {
        if (Ranges.inRange(coords)){
            return matrix [coords.x][coords.y];
        }else return null;
    }

     void setMatrix(Coords coords, enumlist box) {
         if (Ranges.inRange(coords)){
             matrix [coords.x][coords.y] = box;
         }
    }
}
