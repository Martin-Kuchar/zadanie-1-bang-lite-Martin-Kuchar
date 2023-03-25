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
        Card c = target.hasBarrel();
        Card v = target.hasVedla();

        if(c != null && target.checkBarrel() == true) {
            target.removeTableCard(c, d);
        }
        else if(v != null) {
            System.out.println(target.getName() + " used miss card!");
            target.removeCard(v, d);
        }
        else{
            target.removeLives(1, d);
        }
        p.removeCard(this, d);
    }
}
