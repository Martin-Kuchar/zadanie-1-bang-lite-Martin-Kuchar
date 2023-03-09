package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Vedla extends Card{

    public Vedla() {
        this.name = "Vedla";
    }

    @Override
    public void use(Player p) {
        p.removeCard(this);
    }
    
}
