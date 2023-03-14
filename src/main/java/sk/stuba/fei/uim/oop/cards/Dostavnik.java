package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Dostavnik extends Card {
    
    public Dostavnik() {
        this.name = "Dostavnik";
    }

    @Override
    public void use(Player p, Pack d){
        p.addCard(d);
        p.addCard(d);
    }    
}
