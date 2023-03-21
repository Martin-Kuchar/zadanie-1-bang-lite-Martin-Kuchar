package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Vazenie extends Card {
    
    public Vazenie() {
        this.name = "Väzenie";
    }

    @Override
    public void use(Player p, Pack d, Player players[]){
        Player target = choosePlayer(p, players);
        if(target.hasJail() == null){
            target.setJail(this);
            p.removeCard(this, d);
        }
        else {
            System.out.println("Player already is in jail");
        }
    }    
}
