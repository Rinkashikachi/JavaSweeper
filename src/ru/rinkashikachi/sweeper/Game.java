package ru.rinkashikachi.sweeper;

public class Game {
    public Game(int cols, int rows){
        Ranges.setSize(new Coords(cols, rows));
    }

    public Box getBox(Coords coord){
        return Box.values()[(coord.x + coord.y) % Box.values().length];
    }
}
