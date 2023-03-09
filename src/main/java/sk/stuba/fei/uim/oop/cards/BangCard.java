package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class BangCard extends Card{

    public BangCard() {
        this.name = "Bang";
    }

    @Override
    public void use(Player p) {
        if(p.hasCard(Vedla.class) == true) {
            p.getCardByType(Vedla.class).use(p);;
        }
        else{
            p.removeLives(1);
        }

    }
}
