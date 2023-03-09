package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.pack.Pack;

public abstract class Card {

    protected String name;

    public String getName(){
        return this.name;
    }

    public abstract void use(Player p, Pack d);

    
}