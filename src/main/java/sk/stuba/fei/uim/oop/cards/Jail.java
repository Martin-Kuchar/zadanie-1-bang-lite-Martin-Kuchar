package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Jail extends Card {
    
    public Jail() {
        super("Jail");
    }

    @Override
    public void use(Player p, Pack d, Player players[]){
        Player target = choosePlayer(p, players);
        
        if(target.hasJail() == null){
            target.placeCard(this);
            p.removeCard(this, d);
        }
        else {
            System.out.println("Player already is in jail");
        }
    }    
}
