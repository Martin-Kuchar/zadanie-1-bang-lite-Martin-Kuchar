package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Indiani extends Card {

    public Indiani() {
        this.name = "Indiani";
    }

    @Override
    public void use(Player p, Pack d) {
        if(p.hasCard(BangCard.class) == true) {
            p.removeCard(p.getCardByType(BangCard.class), d);
        }
        else{
            p.removeLives(1);
        }
    }
}
