package common;

import elements.*;

import java.util.List;

public class MetaInf {
    public Position pos;
    public List<Wall> walls;
    public List<Coin> coins;
    public List<Monster> monsters;
    public List<String> text;
    public Door door;

    public MetaInf(Position p, List<Wall> w, List<Coin> c, List<Monster> m, Door d, List<String> t){
        pos = p;
        walls = w;
        coins = c;
        monsters = m;
        door = d;
        text = t;
    }
}
