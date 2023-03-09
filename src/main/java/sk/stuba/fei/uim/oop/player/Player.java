package sk.stuba.fei.uim.oop.player;

import java.util.ArrayList;
import java.util.Random;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.Vedla;
import sk.stuba.fei.uim.oop.pack.Pack;

public class Player {

    private final String name;
    private int lives;
    private Random rnd;
    private boolean isOnTurn;
    private boolean barrel;
    private boolean dynamit;
    private boolean vazenie;
    private ArrayList<Card> cards;//araylist pre karty na ruke

    public Player(String name, Pack p) { //inicializacia hraca
        this.name = name;   //zapisanie mena
        this.lives = 4;     //zapisanie pociatocnich zivotov
        rnd = new Random();

        this.cards = new ArrayList<Card>();
        for(int i = 0; i < 4; i++){
            addCard(p);
        }
        System.out.println(this.cards.get(0).getName());
    }
    
    public String getName() {
        return this.name;
    }

    public boolean isInJail() {
        return this.vazenie;
    }

    public void setJail(boolean b) {
        this.vazenie = b;
    }

    public void escapeJail() {
        if(this.rnd.nextInt(4) == 0) {
            System.out.println("Yous succesfuly escaped jail!");
        }
        else{
            System.out.println("You saddly didnt escape jail. Turn over.");
        }
        setJail(false);
    }
    
    public boolean hasDynamite() {
        return this.dynamit;
    }

    public void setDynamite(boolean b) {
        this.dynamit = b;
    }

    public void detonateDynamite(Player nextPlayer) {
        if(rnd.nextInt(8) == 0) {
            this.removeLives(3);
        }
        else {
            this.setDynamite(false);
            nextPlayer.setDynamite(true);
        }
    }

    public int getLives() {
        return this.lives;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public void removeLives(int lives){
        this.lives -= lives;
    }

    public void toggleActive() {
        this.isOnTurn ^= true;
    }

    public void addCard(Pack p) {
        this.cards.add(p.drawCard());
    }

    public Card removeCard(Card c, Pack p) {
        p.addCard(c);
        return cards.remove(cards.indexOf(c));
    }

    public boolean hasCard(Class<?> c) {
        for(Card i : cards){
            if(i.getClass() == c) {
                return true;
            }
        }
        return false;
    }

    public Card getCardByType(Class<?> c) {
        for(Card i : cards){
            if(i.getClass() == c) {
                return i;
            }
        }
        return this.cards.get(-1);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void setCards(ArrayList<Card> c) {
        this.cards = c;
    }

    public void playCard(Card c, Player p, Pack d) {
        if(c.getClass() == Vedla.class) {
            System.out.println("card not playable");
        }
        else{
            c.use(p, d);
            this.removeCard(c, d);
        }
    }
    

}
