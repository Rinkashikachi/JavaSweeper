package ru.rinkashikachi.sweeper;

class Matrix
{
    private Box [] [] matrix;

    Matrix (Box defaultBox)
    {
        matrix = new Box [Ranges.getSize().x] [Ranges.getSize().y];
        for (Coords coord : Ranges.getAllCoords())
            matrix [coord.x] [coord.y] = defaultBox;
    }

    Box get (Coords coord)
    {
        if (Ranges.inRange (coord))
            return matrix [coord.x] [coord.y];
        return null;
    }

    void set (Coords coord, Box box)
    {
        if (Ranges.inRange (coord))
            matrix [coord.x] [coord.y] = box;
    }

}
