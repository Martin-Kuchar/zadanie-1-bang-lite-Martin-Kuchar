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
    private boolean isOnTurn;
    private boolean barrel;
    private boolean dynamit;
    private boolean vazenie;
    private ArrayList<Card> cards;//araylist pre karty na ruke
    private ArrayList<Card> tableCards;

    public Player(String name, Pack p) { //inicializacia hraca
        this.name = name;   //zapisanie mena
        this.lives = 4;     //zapisanie pociatocnich zivotov
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
        return this.vazenie;
    }

    public void setJail(boolean b) {
        this.vazenie = b;
    }

    public void escapeJail(Pack p) {
        if(this.rnd.nextInt(4) == 0) {
            System.out.println("Yous succesfuly escaped jail!");
        }
        else{
            System.out.println("You saddly didnt escape jail. Turn over.");
        }
        for (Card c : tableCards) {
            if(c.getName() == "Väzenie") {
                this.cards.add(c);
                this.tableCards.remove(c);
                removeCard(c, p);
            }
        }
        setJail(false);
    }
    
    public boolean hasDynamite() {
        return this.dynamit;
    }

    public void setDynamite(boolean b) {
        this.dynamit = b;
    }

    public void detonateDynamite(Player nextPlayer, Pack p) {
        if(rnd.nextInt(8) == 0) {
            this.removeLives(3);
            for (Card c : tableCards) {
                if(c.getName() == "Dynamit") {
                    this.cards.add(c);
                    this.tableCards.remove(c);
                    removeCard(c, p);
                }
            }
        }
        else {
            this.setDynamite(false);
            for (Card c : tableCards) {
                if(c.getName() == "Dynamit") {
                    nextPlayer.playCard(c, nextPlayer, p);
                    this.cards.add(c);
                    this.tableCards.remove(c);
                    removeCard(c, p);
                }
            }
        }
    }

    public int getLives() { //ziskanie poctu zivotov
        return this.lives;
    }

    public void addLives(int lives) {   //pridanie zivotov
        this.lives += lives;
    }

    public void removeLives(int lives) { //odstranenie zivotov
        this.lives -= lives;
    }

    public void toggleActive() {    //zmena active stavu
        this.isOnTurn ^= true;
    }

    public void addCard(Pack p) {   //potiahnutie karty z balicku
        this.cards.add(p.drawCard());
    }

    public Card removeCard(Card c, Pack p) { //vratenie karty do balicku
        p.addCard(c);
        return cards.remove(cards.indexOf(c));
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
        return this.cards.get(-1);
    }

    public ArrayList<Card> getCards() { //return vsetkich kariet
        return this.cards;
    }

    public void setCards(ArrayList<Card> c) {   //nastavenie kriet
        this.cards = c;
    }

    private void placeCard(Card c, Player p, Pack d) { //polozenie karty pred seba
        this.tableCards.add(c);
        this.cards.remove(c); 
        c.use(p, d);
    }

    public void playCard(Card c, Player p, Pack d) {    //zahranie karty
        if(c.getClass() == Dynamit.class || c.getClass() == Barrel.class || c.getClass() == Vazenie.class) {
            placeCard(c, p, d);
        }
        else {
            c.use(p, d);
            this.removeCard(c, d);
        }
    }
    

}
