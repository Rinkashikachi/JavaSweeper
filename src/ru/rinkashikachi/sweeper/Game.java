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

    private void openBoxesAround(Coords coord){
        flags.setBoxAsOpened(coord);
        for (Coords around: Ranges.getCoordsAround(coord))
            openBox(around);
    }

    private void checkWinner(){
        if (state == GameState.PLAYING)
            if (flags.getClosedBoxes() == bombs.getTotalBombs())
                state = GameState.WON;
    }

    private void openBox(Coords coord){
        switch (flags.get(coord)){
            case OPENED : setBoxesAroundNumberAsOpened(coord); return;
            case FLAGGED: return;
            case CLOSED :
                switch (bombs.get(coord)){
                    case ZERO: openBoxesAround(coord); return;
                    case BOMB: openBombs(coord); return;
                    default  : flags.setBoxAsOpened(coord); return;
                }
        }
    }

    void setBoxesAroundNumberAsOpened(Coords coord){
        if (bombs.get(coord) != Box.BOMB)
            if (flags.countFlaggedAround(coord) == bombs.get(coord).getNumber())
                for (Coords around: Ranges.getCoordsAround(coord))
                    if (flags.get(around) == Box.CLOSED)
                        openBox(around);
    }

    private void openBombs(Coords bomb){
        state = GameState.BOMBED;
        flags.setBoxAsBombed(bomb);
        for (Coords coord: Ranges.getAllCoords())
            if (bombs.get(coord) == Box.BOMB)
                flags.setBombAsOpened(coord);
            else
                flags.setFlaggedAsClear(coord);
    }

    public void leftButtonPressed(Coords coord){
        if (gameOver()) return;
        openBox(coord);
        checkWinner();
    }

    public void rightButtonPressed(Coords coord){
        if (gameOver()) return;
        flags.toggleBoxAsFlagged(coord);
    }

    private boolean gameOver(){
        if (state == GameState.PLAYING)
            return false;
        start();
        return true;
    }
}
