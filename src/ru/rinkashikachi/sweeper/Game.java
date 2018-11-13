package ru.rinkashikachi.sweeper;

public class Game {
    private Bomb bombs;
    private Flag flags;
    private GameState state;

    public void start(){
        bombs.start();
        flags.start();
        state = GameState.PLAYING;
    }

    public Game(int cols, int rows, int bombNumber){
        Ranges.setSize(new Coords(cols, rows));
        bombs = new Bomb(bombNumber);
        flags = new Flag();
    }

    public GameState getState(){
        return state;
    }

    public Box getBox(Coords coord){
        if (flags.get(coord) == Box.OPENED)
            return bombs.get(coord);
        else
            return flags.get(coord);
    }

    public void leftButtonPressed(Coords coord){
        flags.setBoxAsOpened(coord);
    }

    public void rightButtonPressed(Coords coord){
        flags.toggleBoxAsFlagged(coord);
    }
}
