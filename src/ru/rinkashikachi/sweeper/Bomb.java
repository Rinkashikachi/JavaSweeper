package ru.rinkashikachi.sweeper;


class Bomb {
    private Matrix bombMap;
    private int bombTotal;

    Bomb(int bombTotal){
        this.bombTotal = bombTotal;
    }

    void start(){
        bombMap = new Matrix(Box.ZERO);
        for(int i=0; i<bombTotal; i++)
            placeBomb();
    }

    Box get(Coords coord){
        return bombMap.get(coord);
    }

    private void placeBomb(){
        Coords coord = Ranges.getRandomCoord();
        bombMap.set(coord, Box.BOMB);
    }
}
