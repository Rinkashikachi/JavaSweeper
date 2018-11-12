package ru.rinkashikachi.sweeper;

public class Game {
    Matrix bombMap;

    public void start(){
        bombMap = new Matrix(Box.ZERO);
        bombMap.set(new Coords(0,0), Box.BOMB);
        bombMap.set(new Coords(0,1), Box.NUM1);
        bombMap.set(new Coords(1,0), Box.NUM1);
        bombMap.set(new Coords(1,1), Box.NUM1);
    }

    public Game(int cols, int rows){
        Ranges.setSize(new Coords(cols, rows));
    }

    public Box getBox(Coords coord){
        return bombMap.get(coord);
    }
}
