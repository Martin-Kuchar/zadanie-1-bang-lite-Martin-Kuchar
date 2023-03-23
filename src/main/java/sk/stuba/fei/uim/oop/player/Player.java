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
            System.out.println("Dynamite exploeded in fornt " + this.getName() + " you loose 3 lives!");
            this.removeLives(3, p);
            p.addCard(this.hasDynamite());
        }
        else {
            System.out.println("Dynamite didnt explode passing to player " + nextPlayer.getName() + "!");
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
            System.out.println(this.getName() + " ducked behind barrel!");
            return true;
        }
        return false;
    }

    public int getLives() {
        return this.lives;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public void removeLives(int lives, Pack p) {
        this.lives -= lives;
        if (this.lives <=0) {
            kill(p);
        }
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void kill(Pack p) {
        System.out.println("Player " + this.getName() + " has died");
        this.isAlive = false;
        for (Card c : this.cards) {
            p.addCard(c);
        }
        this.cards.removeAll(cards);
    }

    public void addCard(Pack p) {
        if(!p.isEmpty()){
            this.cards.add(p.drawCard());
        }
        else {
            System.out.println("You ran out of cards in deck!");
        }
    }

    public Card removeCard(Card c, Pack p) {
        p.addCard(c);
        return cards.remove(cards.indexOf(c));
    }

    public Card removeTableCard(Card c, Pack p) {
        p.addCard(c);
        return tableCards.remove(tableCards.indexOf(c));
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

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void setCards(ArrayList<Card> c) {
        this.cards = c;
    }

    public ArrayList<Card> getTable() {
        return this.tableCards;
    }
    public void setTable(ArrayList<Card> c) {
        this.tableCards = c;
    }
}
