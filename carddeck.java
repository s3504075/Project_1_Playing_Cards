
///COP 2805 C
//Giancarlo Fruzzetti
//Project 1 Due 1/22/2023

package edu.cop280f5.playingcards;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

enum CardType { none, Ace, Jack, Queen, King }
enum Suit {Clubs, Diamonds, Hearts, Spades}

class Card
{

    private Integer pointValue;
    private boolean isface;
    private CardType ctype;
    private boolean dealt=false;

    private Suit TypesofSuits;

    public Card(Suit TypesofSuits, CardType ctype, Integer pointValue, boolean isface, boolean dealt)
    {
        this.pointValue=pointValue;
        this.isface=isface;
        this.ctype=ctype;
        this.TypesofSuits=TypesofSuits;
        this.dealt=dealt;
    }

    public Integer getPointValue() {
        return pointValue;
    }
    public Suit getSuit()
    {
        return TypesofSuits;
    }
    public CardType getType()
    {
        return ctype;
    }

    public boolean getFace()
    {
        return isface;
    }

    public boolean isDealt()
    {
        return dealt;
    }

    public void setDealt(boolean dealt)
    {
        this.dealt = dealt;
    }

    @Override
    public String toString() //displays suit as ascii escape code
    {
        var s="";
        //String s = "Value:" + pointValue + " Suit:" + TypesofSuits + " Card Type:" + ctype + " Face Card:" + isface+ " Dealt:" + dealt + "\n";
        if (TypesofSuits==Suit.Clubs)
        {
            s = "Value:" + pointValue + " Suit:" + "\u2663" + " Card Type:" + ctype + " Face Card:" + isface+ " Dealt:" + dealt + "\n";
        }
        else if (TypesofSuits==Suit.Diamonds)
        {
            s = "Value:" + pointValue + " Suit:" + "\u2666" + " Card Type:" + ctype + " Face Card:" + isface+ " Dealt:" + dealt + "\n";
        }
        else if (TypesofSuits==Suit.Hearts)
        {
            s = "Value:" + pointValue + " Suit:" + "\u2665" + " Card Type:" + ctype + " Face Card:" + isface+ " Dealt:" + dealt + "\n";
        }
        else
        {
            s = "Value:" + pointValue + " Suit:" + "\u2660" + " Card Type:" + ctype + " Face Card:" + isface+ " Dealt:" + dealt + "\n";
        }

        return s;
    }
}



public class carddeck
{
    ArrayList<Card> Deck=new ArrayList<>();


    ArrayList<Card> SelectHand(int n) //gets n random cards out of the deck and
    {
        // get random array of undealt cards
        ArrayList<Card> playerHand = new ArrayList<>();
        var index = -1;

        // seed random generator with current time for variety
        Random r = new Random(System.currentTimeMillis());
        for (var i = 0; i < n; )
        {
            index = r.nextInt(Deck.size());
            Card playerCard = Deck.get(index); //get random card out of the deck by index value
            //System.out.println("getting " + index);
            if (playerCard.isDealt() == false) //see if the card is dealt already
            {
                playerHand.add(playerCard); //if not add to the hand
                playerCard.setDealt(true); //change it to dealt
                i++; //next card issued
            }
        }
        return playerHand;
    }

    public void displayHand(ArrayList<Card> hand)
    {
        int handsize=hand.size();

        System.out.println("Size of hand is " + handsize);

        for(int j=0; j<handsize; j++)
        {
            System.out.println(hand.get(j));
        }
    }

    @Override
    public String toString()
    {
        var s = "Here is your Card Deck:\n";
        for (Card c : Deck) {
            s += "\t";
            s += c;
            s += "\n";
        }
        return s;

    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String txtblock = """
                Welcome to the Playing Card Simulator! 
                I will now create your deck of 52 cards and deal a random hand of size 5.
                """;
        String CompleteDeck; //holds the deck for displaying
        String PlayerHandforDisp; //holds the player hand to display

        System.out.println(txtblock);


        carddeck plc = new carddeck();
        Integer pv;

        //setup deck
        for (Suit TypesofSuits : Suit.values()) //club,diamond,heart,spade
        {
            for (int i = 1; i < CardType.values().length; i++) //ace,jack,queen,king
            {
                if (CardType.values()[i] == CardType.Ace)
                {
                    pv = 1;
                }
                else
                {
                    pv = 10;  //one of the other ones
                }
                Card C = new Card(TypesofSuits, CardType.values()[i], pv, true,false);
                plc.Deck.add(C);
            } //end for
            for (int i = 2; i < 11; i++) //non face cards
            {
                Card C = new Card(TypesofSuits, CardType.values()[0], i, false,false); //suit, cardtype will be none, pointvalue, isface, dealt
                plc.Deck.add(C);
            } //end for
        } //end for
        CompleteDeck=plc.toString();
        System.out.println(CompleteDeck);

        //give the player their hand
        System.out.println("Enter the number of cards you wish to get in your player hand: ");
        int numcards=input.nextInt();
        if(numcards <= 7) {
            ArrayList<Card> playerHand = plc.SelectHand(numcards); //set the player hand of size numcards
            //PlayerHandforDisp=playerHand.toString();
            plc.displayHand(playerHand);
        }
        else {
            System.out.println("Hand is too big!!");
        }


    } //end Main
} //end Class