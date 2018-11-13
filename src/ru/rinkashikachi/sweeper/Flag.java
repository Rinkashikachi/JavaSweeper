package ru.rinkashikachi.sweeper;

class Flag {
    private Matrix flagMap;

    void start(){
        flagMap = new Matrix(Box.CLOSED);
    }

    Box get(Coords coord){
        return flagMap.get(coord);
    }

    void setBoxAsOpened(Coords coord){
        flagMap.set(coord, Box.OPENED);
    }

    void setBoxAsClosed(Coords coord){
        flagMap.set(coord, Box.CLOSED);
    }

    void toggleBoxAsFlagged(Coords coord){
        switch (flagMap.get(coord)){
            case FLAGGED: setBoxAsClosed(coord); break;
            case CLOSED: setBoxAsFlagged(coord); break;
        }
    }

    void setBoxAsFlagged(Coords coord){
        flagMap.set(coord, Box.FLAGGED);
    }
}
