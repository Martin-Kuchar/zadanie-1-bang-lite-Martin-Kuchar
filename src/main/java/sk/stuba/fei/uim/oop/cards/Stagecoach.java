package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Stagecoach extends Card {
    
    public Stagecoach() {
        super("Stagecoach");
    }

    @Override
    public void use(Player p, Pack d, Player players[]){
        p.addCard(d);
        p.addCard(d);
        p.removeCard(this, d);
    }    
}
