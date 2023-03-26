package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Barrel extends Card {

    public Barrel() {
        this.name = "Barrel";
    }

    @Override
    public void use(Player p, Pack d, Player players[]) {
        if(p.hasBarrel() == null){
            p.placeCard(this);
            p.removeCard(this, d);
        }
        else {
            System.out.println("Player already has barrel");
        }
    }
}
