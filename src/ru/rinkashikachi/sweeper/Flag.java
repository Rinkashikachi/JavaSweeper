package ru.rinkashikachi.sweeper;

class Flag {
    private Matrix flagMap;
    private int closedBoxes;

    void start(){
        flagMap = new Matrix(Box.CLOSED);
        closedBoxes = Ranges.getSize().x *
                      Ranges.getSize().y;
    }

    Box get(Coords coord){
        return flagMap.get(coord);
    }

    void setBoxAsOpened(Coords coord){
        flagMap.set(coord, Box.OPENED);
        closedBoxes--;
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

    void setBoxAsBombed(Coords coord){
        flagMap.set(coord, Box.BOMBED);
    }

    int getClosedBoxes(){
        return closedBoxes;
    }

    void setBombAsOpened(Coords coord){
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    void setFlaggedAsClear(Coords coord){
        if (flagMap.get(coord) == Box.FLAGGED)
            flagMap.set(coord, Box.NOBOMB);
    }



    int countFlaggedAround(Coords coord){
        int count = 0;
        for (Coords around: Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGGED)
                count++;
        return count;
    }
}
