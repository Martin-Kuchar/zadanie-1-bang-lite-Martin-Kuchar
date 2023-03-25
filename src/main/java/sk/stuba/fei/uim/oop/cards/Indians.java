package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Indians extends Card {

    public Indians() {
        this.name = "Indians";
    }

    @Override
    public void use(Player p, Pack d, Player players[]) {
        for (Player target : players) {
            if(target.isAlive() && target != p){

                Card b = target.hasBang();
                if(b != null) {
                    target.removeCard(b, d);
                }
                else{
                    target.removeLives(1, d);
                }
            }
        }
            p.removeCard(this, d);
    }
}
