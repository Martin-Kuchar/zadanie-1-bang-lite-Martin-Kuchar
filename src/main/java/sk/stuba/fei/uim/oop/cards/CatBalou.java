package sk.stuba.fei.uim.oop.cards;

import java.util.Random;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class CatBalou extends Card {
    
    private Random rnd = new Random();

    public CatBalou() {
        this.name = "Cat Balou";
    }

    @Override
    public void use(Player p, Pack d, Player players[]) {
        int odhod = ZKlavesnice.readInt("chces odhodit zo stola alebo z ruky?(zadaj 0 pre stol alebo 1 pre ruku)");

        if(odhod == 1) {
            p.removeCard(p.getCards().get(rnd.nextInt(p.getCards().size())), d);
        }
        else {
            //odstran jail/dynamit/barrel
        }
        
    }    
}
