package Messages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Cards.Card;
import Cards.CardName;

/**
 * The client wants to start a Game. For this purpose the client chooses a singleplayer or multiplayer Game.
 * If the client wants to start a singleplayer Game, he/she starts a new Game with a Bot.
 * If the client wants to start a multiplayer Game, he/she starts a new Game with another Player
 * 
 * @author Lukas
 * @version 1.0
 * @created 31-Okt-2017 17:01:13
 */
public class CreateGame_Message extends Message {

	private static final String ATTR_BUYCARDNUMBER = "buyCardNumber";
	private static final String ELEMENT_BUYCARD = "buyCard";
	private static final String ELEMENT_BUYCARDS = "buyCards";
	private static final String ELEMENT_DECKPILE = "deckPile";
	private static final String ELEMENT_OPPONENT = "opponent";
	private final static String ELEMENT_HANDCARDS = "handCards";
	private final static String ELEMENT_DECKCARD = "deckCard";
	private final static String ELEMENT_HANDCARD = "handCard";
	private HashMap<CardName, Integer> buyCards;
	private LinkedList<Card> handCards;
	private Stack<Card> deckPile;
	private String opponent;


	public CreateGame_Message(){
		super();
        this.deckPile = new Stack<Card>();
        this.buyCards = new HashMap<CardName, Integer>();
        this.handCards = new LinkedList<Card>();
	}

	/**
	 * 
	 * @param docIn
	 */
	@Override
	protected void addNodes(Document docIn){
        Element root = docIn.getDocumentElement();
		
        //insert all deckCards in the right order into the XML_Document
		Element deckPile = docIn.createElement(ELEMENT_DECKPILE);
		for(Card card: this.deckPile){
			Element deckCard = docIn.createElement(ELEMENT_DECKCARD);
			deckCard.setTextContent(card.getCardName().toString());
			deckPile.appendChild(deckCard);
		}
		root.appendChild(deckPile);
		
		//insert all buyCards (Cards that can be bought during the Game) created from Game into the XML_Document
		Element buyCards = docIn.createElement(ELEMENT_BUYCARDS);
		Set<CardName> cardSet = this.buyCards.keySet();
		for(CardName cardName: cardSet){
			Element buyCard = docIn.createElement(ELEMENT_BUYCARD);
			buyCard.setAttribute(ATTR_BUYCARDNUMBER, Integer.toString(this.buyCards.get(cardName)));
			buyCard.setTextContent(cardName.toString());
			buyCards.appendChild(buyCard);
		}
		root.appendChild(buyCards);
		
		//insert all handCards in the right order into the XML_Document
		Element handCards = docIn.createElement(ELEMENT_HANDCARDS);
		for(Card card: this.handCards){
			Element handCard = docIn.createElement(ELEMENT_HANDCARD);
			handCard.setTextContent(card.getCardName().toString());
			handCards.appendChild(handCard);
		}
		root.appendChild(handCards);
		
		//insert opponents name to show on client
		Element opponent = docIn.createElement(ELEMENT_OPPONENT);
		opponent.setTextContent(this.opponent);
		root.appendChild(opponent);
	
	}
	
	/**
	 * 
	 * @param docIn
	 */
	@Override
	protected void init(Document docIn){
		Element root = docIn.getDocumentElement();
		
		//creates the deckPile from XML_Document with new CardObjects in the correct language
		NodeList tmpElements = root.getElementsByTagName(ELEMENT_DECKPILE);
        if (tmpElements.getLength() > 0) {
            Element deckPile = (Element) tmpElements.item(0);
            NodeList deckElements = deckPile.getElementsByTagName(ELEMENT_DECKCARD);
            for(int i = deckElements.getLength() -1; i >= 0; i--){
            	Element deckCard = (Element) deckElements.item(i);
            	this.deckPile.push(Card.getCard(CardName.parseName(deckCard.getTextContent())));
            }
        }
        
        //creates all buyCards from XML_Document with new CardObjects in the correct language
        tmpElements = root.getElementsByTagName(ELEMENT_BUYCARDS);
        if(tmpElements.getLength() > 0){
        	Element buyCards = (Element) tmpElements.item(0);
        	NodeList buyElements = buyCards.getElementsByTagName(ELEMENT_BUYCARD);
        	for(int i = buyElements.getLength() -1; i >= 0; i--){
        		Element buyCard = (Element) buyElements.item(i);
        		Integer numOfCards = Integer.parseInt(buyCard.getAttribute(ATTR_BUYCARDNUMBER));
        		this.buyCards.put(CardName.parseName(buyCard.getTextContent()), numOfCards);
        	}
        }
        
        //creates all handCards from XML_Document with new CardObjects in the correct language
        tmpElements = root.getElementsByTagName(ELEMENT_HANDCARDS);
        if(tmpElements.getLength() > 0){
        	Element handCards = (Element) tmpElements.item(0);
        	NodeList handElements = handCards.getElementsByTagName(ELEMENT_HANDCARD);
        	for(int i = handElements.getLength() -1; i >= 0; i--){
        		Element handCard = (Element) handElements.item(i);
//        		this.handCards.add(Card.getCard(handCard.getTextContent()));
        	}
        }
        
        //sets the opponents name
        tmpElements = root.getElementsByTagName(ELEMENT_OPPONENT);
        if(tmpElements.getLength() > 0){
        	Element opponent = (Element) tmpElements.item(0);
        	this.opponent = opponent.getTextContent();
        }
	}
	


	public HashMap<CardName, Integer> getBuyCards(){
		return this.buyCards;
	}


	public Stack<Card> getDeckPile(){
		return this.deckPile;
	}

	public String getOpponent(){
		return this.opponent;
	}
	

	
	public LinkedList<Card> getHandCards(){
		return this.handCards;
	}

	
	
	public void setHandCards(LinkedList<Card> handCards){
		this.handCards = handCards;
	}
	
	

	public void setBuyCards(HashMap<CardName, Integer> buyCards){
		this.buyCards = buyCards;
	}


	public void setDeckPile(Stack<Card> deckPile){
		this.deckPile = deckPile;
	}

	public void setOpponent(String opponent){
		this.opponent = opponent;
	}
}//end CreateGame_Message