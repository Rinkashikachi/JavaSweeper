package ru.rinkashikachi.sweeper;


class Bomb
{
    private Matrix bombMap;
    private int bombTotal;

    Bomb (int totalBombs)
    {
        this.bombTotal = totalBombs;
        fixBombsCount();
    }

    void start()
    {
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < bombTotal; j++)
            placeBomb ();
    }

    Box get (Coords coord)
    {
        return bombMap.get(coord);
    }

    private void fixBombsCount ()
    {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y /3;
        if (bombTotal > maxBombs)
            bombTotal = maxBombs;
    }

    private void placeBomb ()
    {
        while (true)
        {
            Coords coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(new Coords(coord.x, coord.y), Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }

    }

    private void incNumbersAroundBomb (Coords coord)
    {
        for (Coords around : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs()
    {
        return bombTotal;
    }
}
