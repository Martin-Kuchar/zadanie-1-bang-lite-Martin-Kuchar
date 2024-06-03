package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Dynamite extends Card {
    
    public Dynamite() {
        super("Dynamite");
    }

    @Override
    public void use(Player p, Pack d, Player players[]){
        if(p.hasDynamite() == null) {
            p.placeCard(this);
            p.removeCard(this, d);
        }
        else {
            System.out.println("Player already has dynamite");
        }
    }    
}
