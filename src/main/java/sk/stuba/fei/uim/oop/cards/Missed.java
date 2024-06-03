package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Missed extends Card{

    public Missed() {
        super("Missed");
    }

    @Override
    public void use(Player p, Pack d, Player players[]) {
        System.out.println("Card not playable");
    }  
}
