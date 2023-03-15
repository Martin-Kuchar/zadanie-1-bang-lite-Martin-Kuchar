package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.pack.Pack;
import sk.stuba.fei.uim.oop.player.Player;

public class Pivo extends Card {

    public Pivo(){
        this.name = "Pivo";
    }
    @Override
    public void use(Player p, Pack d, Player players[]){
        System.out.println("You gain 1 life");
        p.addLives(1);
        p.removeCard(this, d);
    }
}
