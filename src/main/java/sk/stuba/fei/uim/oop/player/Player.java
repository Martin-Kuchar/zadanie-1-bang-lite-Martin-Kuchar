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

    public boolean isInJail() {
        for (Card c : this.tableCards) {
            if(c instanceof Vazenie) {
                return true;
            }
        }
        return false;
    }

    public void setJail(Card c) {
        this.tableCards.add(c);
    }

    public void escapeJail(Pack p, Player nextPlayer, Player players[]) {
        if(this.rnd.nextInt(4) == 0) {
            System.out.println("Yous succesfuly escaped jail!");
            for (Card c : tableCards) {
                if(c instanceof Vazenie) {
                    p.addCard(c);
                    this.tableCards.remove(c);
                }
            }
        }
        else{
            System.out.println("You saddly didnt escape jail. Turn over.");
            for (Card c : tableCards) {
                if(c instanceof Vazenie) {
                    c.use(nextPlayer, p, players);
                    this.tableCards.remove(c);
                }
            }
        }
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

    public void detonateDynamite(Player nextPlayer, Pack p, Player players[]) {
        if(rnd.nextInt(8) == 0) {
            this.removeLives(3);
            for (Card c : tableCards) {
                if(c instanceof Dynamit) {
                    p.addCard(c);
                    this.tableCards.remove(c);
                }
            }
        }
        else {
            for (Card c : tableCards) {
                if(c instanceof Dynamit) {
                    c.use(nextPlayer, p, players);
                    this.tableCards.remove(c);
                }
            }
        }
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

    public void removeLives(int lives) { //odstranenie zivotov
        this.lives -= lives;

        if(this.lives < 1) {
            this.kill(null);
        }
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void kill(Pack p) {    //zabitie hraca
        this.isAlive = false;
        for (Card c : this.cards) {
            this.removeCard(c, p);
        }
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

    //public void playCard(Card c, Pack d, Player players[]) {    //zahranie karty
    //    c.use(this, d, players);
    //    this.removeCard(c, d);
    //}
}
