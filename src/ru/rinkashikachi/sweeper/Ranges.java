package ru.rinkashikachi.sweeper;

import java.util.ArrayList;

public class Ranges {
    private static Coords size;
    private static ArrayList<Coords> allCoords;

    static void setSize(Coords _size) {
        size = _size;
        allCoords = new ArrayList<Coords>();
        for (int y=0; y<size.y; y++)
            for (int x=0; x<size.x; x++)
                allCoords.add(new Coords(x, y));
    }

    public static Coords getSize() {
        return size;
    }

    public static ArrayList<Coords> getAllCoords(){
        return allCoords;
    }

    static boolean inRange(Coords coord){
        return coord.x >= 0 && coord.x <= size.x &&
               coord.y >=0 && coord.y <= size.y;
    }
}
