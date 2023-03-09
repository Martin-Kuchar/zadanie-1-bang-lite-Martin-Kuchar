package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Dynamit extends Card {
    
    @Override
    public void use(Player p, Pack d){
        p.setDynamite(true);
    }    
}
