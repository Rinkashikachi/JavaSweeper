package ru.rinkashikachi.sweeper;

public class Game {
    private Bomb bombs;

    public void start(){
        bombs.start();
    }

    public Game(int cols, int rows, int bombNumber){
        Ranges.setSize(new Coords(cols, rows));
        bombs = new Bomb(bombNumber);
    }

    public Box getBox(Coords coord){
        return bombs.get(coord);
    }
}
