package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class BangCard extends Card{

    public BangCard() {
        this.name = "Bang";
    }

    @Override
    public void use(Player p, Pack d, Player players[]) {
        
        Player target = choosePlayer(p, players);
        Card v = target.hasVedla();
        if(v != null) {
            target.removeCard(v, d);
        }
        else{
            target.removeLives(1);
        }
        p.removeCard(v, d);
    }
}
