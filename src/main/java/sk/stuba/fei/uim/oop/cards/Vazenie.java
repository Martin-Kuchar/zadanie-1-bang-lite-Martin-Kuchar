package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Vazenie extends Card {
    
    public Vazenie() {
        this.name = "Väzenie";
    }

    @Override
    public void use(Player p, Pack d){
        p.setJail(this);
    }    
}
