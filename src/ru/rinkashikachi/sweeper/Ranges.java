package ru.rinkashikachi.sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    private static Coords size;
    private static ArrayList<Coords> allCoords;
    private static Random random = new Random();


    public static void setSize (Coords _size)
    {
        size = _size;
        allCoords = new ArrayList<Coords>();
        for (int y = 0; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCoords.add(new Coords(x,y));
    }

    public static Coords getSize()
    {
        return size;
    }

    public static ArrayList<Coords> getAllCoords ()
    {
        return allCoords;
    }

    static boolean inRange (Coords coord)
    {
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }

    static Coords getRandomCoord ()
    {
        return new Coords(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coords> getCoordsAround (Coords coord)
    {
        Coords around;
        ArrayList<Coords> list = new ArrayList<Coords>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++)
            for (int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coords(x, y)))
                    if (!around.equals(coord))
                        list.add(around);
        return list;
    }


}
