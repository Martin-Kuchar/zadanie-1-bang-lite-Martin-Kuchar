package sk.stuba.fei.uim.oop.player;

import java.util.ArrayList;
import java.util.Random;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.pack.Pack;

public class Player {

    private final String name;
    private int lives;
    private Random rnd;
    private boolean isAlive;
    private ArrayList<Card> cards;//araylist pre karty na ruke
    private ArrayList<Card> tableCards;

    public Player(String name, Pack p) { //inicializacia hraca
        this.name = name;   //zapisanie mena
        this.lives = 4;     //zapisanie pociatocnich zivotov
        this.isAlive = true;
        rnd = new Random();

        this.cards = new ArrayList<Card>();
        this.tableCards = new ArrayList<Card>();
        for(int i = 0; i < 4; i++){
            addCard(p);
        }
    }
    
    public String getName() {
        return this.name;
    }

    public Card hasJail() {
        for (Card c : this.tableCards) {
            if(c instanceof Vazenie) {
                return c;
            }
        }
        return null;
    }

    public void setJail(Card c) {
        this.tableCards.add(c);
    }

    public boolean escapeJail(Pack p) {
        if(this.rnd.nextInt(4) == 0) {
            System.out.println("Yous succesfuly escaped jail!");
            p.addCard(this.hasJail());
            this.tableCards.remove(this.hasJail());
            return true;
        }
        System.out.println("You saddly didnt escape jail. Turn over.");
        this.tableCards.remove(this.hasJail());
        return false;
    }
    
    public Card hasDynamite() {
        for (Card c : this.tableCards) {
            if(c instanceof Dynamit) {
                return c;
            }
        }
        return null;
    }

    public void setDynamite(Card c) {
        this.tableCards.add(c);
    }

    public void detonateDynamite(Player nextPlayer, Pack p) {
        if(rnd.nextInt(8) == 0) {
            this.removeLives(3, p);
            p.addCard(this.hasDynamite());
        }
        else {
            nextPlayer.setDynamite(this.hasDynamite());
        }
        this.tableCards.remove(this.hasDynamite());
    }

    public Card hasBarrel() {
        for (Card c : this.tableCards) {
            if(c instanceof Barrel) {
                return c;
            }
        }
        return null;
    }

    public void setBarrel(Card c) {
        this.tableCards.add(c);
    }

    public void removeBarrel(Card c, Pack d) {
        this.tableCards.remove(c);
        d.addCard(c);
    }
    
    public boolean checkBarrel() {
        if(this.rnd.nextInt(3) == 0) {
            return true;
        }
        return false;
    }

    public int getLives() { //ziskanie poctu zivotov
        return this.lives;
    }

    public void addLives(int lives) {   //pridanie zivotov
        this.lives += lives;
    }

    public void removeLives(int lives, Pack p) { //odstranenie zivotov
        this.lives -= lives;
        if (this.lives <=0) {
            kill(p);
        }
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void kill(Pack p) {    //zabitie hraca
        System.out.println("Player " + this.getName() + " has died");
        this.isAlive = false;
        for (Card c : this.cards) {
            p.addCard(c);
        }
        this.cards.removeAll(cards);
    }

    public void addCard(Pack p) {   //potiahnutie karty z balicku
        this.cards.add(p.drawCard());
    }

    public Card removeCard(Card c, Pack p) { //vratenie karty do balicku
        p.addCard(c);
        return cards.remove(cards.indexOf(c));
    }

    public Card hasVedla() {
        for(Card c : cards){
            if(c instanceof Vedla) {
                return c;
            }
        }
        return null;
    }

    public Card hasBang() {
        for(Card c : cards){
            if(c instanceof BangCard) {
                return c;
            }
        }
        return null;
    }

    public boolean hasCard(Class<?> c) { //zistenie ci ma hrac kartu podla druhu karty
        for(Card i : cards){
            if(i.getClass() == c) {
                return true;
            }
        }
        return false;
    }

    public Card getCardByType(Class<?> c) { //ziskanie karty podla druhu
        for(Card i : cards){
            if(i.getClass() == c) {
                return i;
            }
        }
        return null;
    }

    public ArrayList<Card> getCards() { //return vsetkich kariet
        return this.cards;
    }

    public void setCards(ArrayList<Card> c) {   //nastavenie kriet
        this.cards = c;
    }
}
