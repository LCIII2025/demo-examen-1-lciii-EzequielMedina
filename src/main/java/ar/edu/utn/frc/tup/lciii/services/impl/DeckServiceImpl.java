package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.models.Card;
import ar.edu.utn.frc.tup.lciii.models.CardSuit;
import ar.edu.utn.frc.tup.lciii.models.Deck;
import ar.edu.utn.frc.tup.lciii.services.DeckService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class DeckServiceImpl implements DeckService {

    @Override
    public Deck createDeck() {
        Deck deck = new Deck(new ArrayList<>());
        for(CardSuit cardSuit :CardSuit.values()) {
            for(int i = 1; i < 13; i++) {
                if(i == 8 || i == 9) {
                    break;
                } else if(i > 9) {
                    deck.getCards().add(new Card(cardSuit, i, BigDecimal.valueOf(0.5)));
                } else {
                    deck.getCards().add(new Card(cardSuit, i, BigDecimal.valueOf(i)));
                }
            }
        }
        return deck;
    }

    @Override
    public void shuffleDeck(Deck deck) {
        //TODO: Tomar el mazo (deck) que viene como parametro y mesclar las cartas
        //TODO usando el algoritmo de Fisher-Yates

        for ( int i = deck.getCards().size() - 1; i > 0; i-- ){
            int j = (int) (Math.random() * (i + 1));
            Card temp = deck.getCards().get(i);
            deck.getCards().set(i, deck.getCards().get(j));
            deck.getCards().set(j, temp);
        }




    }

    @Override
    public Card takeCard(Deck deck, Integer deckIndex) {
        //TODO: Tomar del mazo (deck) que viene como parametro la carta de la posición indicada en el parametro deckIndex
        return null;
    }
}
