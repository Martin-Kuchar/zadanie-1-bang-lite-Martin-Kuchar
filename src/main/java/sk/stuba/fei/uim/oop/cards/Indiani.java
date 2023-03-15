package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Indiani extends Card {

    public Indiani() {
        this.name = "Indiani";
    }

    @Override
    public void use(Player p, Pack d, Player players[]) {
        Card b = p.hasBang();
        if(b != null) {
            p.removeCard(b, d);
        }
        else{
            p.removeLives(1);
        }
        p.removeCard(this, d);
    }
}
