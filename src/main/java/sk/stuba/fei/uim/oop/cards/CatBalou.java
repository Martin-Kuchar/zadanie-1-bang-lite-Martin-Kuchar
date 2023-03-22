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

    public void rmHand(Player target, Pack d) {
        target.removeCard(target.getCards().get(rnd.nextInt(target.getCards().size())), d);
    }

    public void rmTable(Player target, Pack d) {
        target.removeCard(target.getCards().get(rnd.nextInt(target.getCards().size())), d);
    }

    @Override
    public void use(Player p, Pack d, Player players[]) {
        
        Player target = this.choosePlayer(p, players);
        if(target.getCards().size() != 0 && target.getTable().size() != 0) {
            int odhod = ZKlavesnice.readInt("chces odhodit zo stola alebo z ruky?(zadaj 0 pre stol alebo 1 pre ruku)");
            
            while (odhod != 0 || odhod != 1) {
                odhod = ZKlavesnice.readInt("chces odhodit zo stola alebo z ruky?(zadaj 0 pre stol alebo 1 pre ruku)");
            }
            if(odhod == 1) {
                this.rmHand(target, d);
            }
            else {
                this.rmTable(target, d);
            }
            p.removeCard(this, d);

        }

        else {
            System.out.println("Player " + target.getName() + " do not have any cards");
        }
        
    }    
}
