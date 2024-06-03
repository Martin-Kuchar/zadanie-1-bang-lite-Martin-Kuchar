package sk.stuba.fei.uim.oop.cards;

import java.util.Random;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class CatBalou extends Card {
    
    private Random rnd = new Random();

    public CatBalou() {
        super("Cat Balou");
    }

    public void rmHand(Player target, Pack d) {
        target.removeCard(target.getCards().get(rnd.nextInt(target.getCards().size())), d);
    }

    public void rmTable(Player target, Pack d) {
        target.removeTableCard(target.getTable().get(rnd.nextInt(target.getTable().size())), d);
    }

    @Override
    public void use(Player p, Pack d, Player players[]) {      
        Player target = this.choosePlayer(p, players);
        
        if(target.getCards().size() != 0 && target.getTable().size() != 0) {
            int odhod = 0;
            do {
                odhod = ZKlavesnice.readInt("do you want to discrad card from hand or table? (0 for hand 1 for table)");
            } while(odhod < 0 || odhod > 1);

            if(odhod == 0) {
                this.rmHand(target, d);
            }
            else {
                this.rmTable(target, d);
            }
            p.removeCard(this, d);
        }
        else if(target.getCards().size() != 0 && target.getTable().size() == 0) {
            System.out.println("Player " + target.getName() + " had cards only on hand");
            this.rmHand(target, d);
            p.removeCard(this, d);
        }
        else if(target.getCards().size() == 0 && target.getTable().size() != 0) {
            System.out.println("Player " + target.getName() + " had cards only on table");
            this.rmTable(target, d);
            p.removeCard(this, d);
        }
        else {
            System.out.println("Player " + target.getName() + " do not have any cards");
        }
    }    
}
