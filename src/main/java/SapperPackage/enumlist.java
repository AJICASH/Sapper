package SapperPackage;


public enum enumlist {
    zero,
    one,
    two,
    three,
    four,
    five,
    six,
    hidingbomb,

    emptyspot,
    notopenedspot,
    flag,
    explosion,
    defusedbomb;

    public Object image;
    enumlist getNextNumber(){
        return enumlist.values()[this.ordinal() + 1];
    }
}
