package SapperPackage;


public enum enumlist {
    notopenedspot,
    one,
    two,
    three,
    hidingbomb,

    emptyspot,
    flag,
    explosion,
    defusedbomb;

    public Object image;
    enumlist getNextNumber(){
        return enumlist.values()[this.ordinal() + 1];
    }
}
