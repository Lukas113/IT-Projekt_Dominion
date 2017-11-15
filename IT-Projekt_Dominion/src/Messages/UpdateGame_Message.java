package Messages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Cards.Card;
import Cards.CardName;

/**
 * @author Lukas
 * @version 1.0
 * @created 31-Okt-2017 17:01:22
 */
public class UpdateGame_Message extends Message {

	
	private static final String ELEMENT_DECKPILE = "deckPile";
	private static final String ELEMENT_NEWHANDCARD = "newHandCard";
	
	private static final String ELEMENT_CHAT = "chat";
	private static final String ELEMENT_CURRENTPHASE = "currentPhase";
	private static final String ELEMENT_CURRENTPLAYER = "currentPlayer";
	private static final String ELEMENT_LOG = "log";
	private String chat = null;
	private String currentPhase = null;
	private String currentPlayer = null;
	private String log = null;
	
	private static final String ELEMENT_BUYS = "buys";
	private static final String ELEMENT_ACTIONS = "actions";
	private static final String ELEMENT_COINS = "coins";
	private static final String ATTR_DECKPILECARDNUMBER = "deckPileCardNumber";
	private static final String ATTR_DISCARDPILECARDNUMBER = "discardPileCardNumber";
	private Integer buys = null;
	private Integer actions = null;
	private Integer coins = null;
	private Integer deckPileCardNumber = null;
	private Integer discardPileCardNumber = null;
	
	private static final String ELEMENT_BUYEDCARD = "buyedCard";
	private static final String ELEMENT_PLAYEDCARD = "playedCard";
	private static final String ELEMENT_DISCARDPILETOPCARD = "discardPileTopCard";
	private Card buyedCard = null;
	private Card playedCard = null;
	private Card discardPileTopCard = null;
    
	private static final String ELEMENT_NEWHANDCARDS = "newHandCards";
    private HashMap<String, String> stringElements;
    private HashMap<String, Integer> integerElements;
    private HashMap<String, Card> cardElements;
    private HashMap<String, LinkedList<Card>> handCardElements;
    
    private HashMap<String, String> attrElements;
    private HashMap<String, Integer> attrValues;
    
    
    
    /**
     * Constructor fills the Top-Level XML List for simpler adding
     */
	public UpdateGame_Message(){
		super();
		//Top_Level Elements
		this.stringElements = new HashMap<String, String>();
		this.integerElements = new HashMap<String, Integer>();
		this.cardElements = new HashMap<String, Card>();
		this.handCardElements = new HashMap<String, LinkedList<Card>>();
		
		//Top_Level Attributes
		this.attrValues = new HashMap<String, Integer>();
		this.attrElements = new HashMap<String, String>();
		}

	/**
	 * 
	 * @param docIn
	 */
	@Override
	protected void addNodes(Document docIn){
        Element root = docIn.getDocumentElement();
        
        initializeMaps();
        
        //adds the Top-Level Elements to XML
        this.addContentElements(docIn, root, this.stringElements);
        this.addContentElements(docIn, root, this.integerElements);
        this.addContentElements(docIn, root, this.cardElements);
        
        //adds the Top-Level Element HANDCARDS to XML and the subElements HANDCARD
        Element handCardsElement = docIn.createElement(ELEMENT_NEWHANDCARDS);
        this.addContentElements(docIn, handCardsElement, this.handCardElements);
        root.appendChild(handCardsElement);
	}
	
	/**
	 * Stores the content of the variables into the HashMaps
	 * If the content is null, the Element just has no content, but probably an attribute. Per default they're in the stringElements
	 */
	private void initializeMaps() {
		this.stringElements.put(ELEMENT_CURRENTPLAYER, this.currentPlayer);
		this.stringElements.put(ELEMENT_CURRENTPHASE, this.currentPhase);
		this.stringElements.put(ELEMENT_CHAT, this.chat);
		this.stringElements.put(ELEMENT_LOG, this.log);
		this.stringElements.put(ELEMENT_DECKPILE, null);
		
		this.integerElements.put(ELEMENT_ACTIONS, this.actions);
		this.integerElements.put(ELEMENT_COINS, this.coins);
		this.integerElements.put(ELEMENT_BUYS, this.buys);
		
		this.cardElements.put(ELEMENT_PLAYEDCARD, this.playedCard);
		this.cardElements.put(ELEMENT_BUYEDCARD, this.buyedCard);
		this.cardElements.put(ELEMENT_DISCARDPILETOPCARD, this.discardPileTopCard);
		
		this.attrValues.put(ATTR_DECKPILECARDNUMBER, this.deckPileCardNumber);
		this.attrValues.put(ATTR_DISCARDPILECARDNUMBER, this.discardPileCardNumber);
		this.attrElements.put(ELEMENT_DECKPILE, ATTR_DECKPILECARDNUMBER);
		this.attrElements.put(ELEMENT_DISCARDPILETOPCARD, ATTR_DISCARDPILECARDNUMBER);
	}

	/**
	 * Helps the addNodes method to add elements in the given root
	 * If an element intends to have an attribute, it will be set
	 * 
	 * @param docIn, to create further elements
	 * @param root, the current root to add elements
	 * @param contents, generic HashMap, consists the elements or attributes as keys(<String>) and the input as values(<T>)
	 */
	private <T> void addContentElements(Document docIn, Element root, HashMap<String, T> content){
		Set<String> keys = content.keySet();
        for(String key : keys){
        	//If there is just one element but multiple possible Elements, the content has to be unpacked from the LinkedList
        	if(content.get(key) instanceof LinkedList<?> && content.get(key) != null){
        		LinkedList<Card> cardList = (LinkedList<Card>) content.get(key);
        		for(int i = 0; i < cardList.size(); i++){
                	Element element = docIn.createElement(key);
                	if(this.attrElements.containsKey(key))//If an element contains an attribute, it will be set
                		element.setAttribute(this.attrElements.get(key), this.attrValues.get(this.attrElements.get(key)).toString());
                	element.setTextContent(cardList.get(i).toString());
                	root.appendChild(element);
        		}
        	}else{//If an element has just one content, it can be resolved the "normal" way (one element, one content)
            	Element element = docIn.createElement(key);
            	if(this.attrElements.containsKey(key))//If an element contains an attribute, it will be set
            		element.setAttribute(this.attrElements.get(key), this.attrValues.get(this.attrElements.get(key)).toString());
            	if(content.get(key) != null && content.get(key).toString().length() > 0)//if content has changed, it will be set
            		element.setTextContent(content.get(key).toString());
            	root.appendChild(element);
        	}
        }
	}
	
	
	/**
	 * 
	 * @param docIn
	 */
	@Override
	protected void init(Document docIn){
		Element root = docIn.getDocumentElement();
		initializeMaps();
		
		this.parseContent(root, this.stringElements);
		this.parseContent(root, this.integerElements);
		this.parseContent(root, this.handCardElements);

	}
	
	/**
	 * Helps the init method to parse the content in the appropriate structure
	 * 
	 * @param root, consists the parsing elements
	 * @param content, generic HashMap, consist the elements or attributes as keys (<String>) and the input as values (<T>)
	 */
	private <T> void parseContent(Element root, HashMap<String, T> content){
		Set<String> keys = content.keySet();
		for(String key: keys){
			NodeList tmpElements = root.getElementsByTagName(key);
			if(tmpElements.getLength() > 0){
				Element element = (Element) tmpElements.item(0);
				//Checks if an element has an attribute. If yes, it will be stored in the HashMap attrValues (only Integers in this message)
				Set<String> attrKeys = this.attrValues.keySet();
				for(String attrKey: attrKeys){
					if(element.hasAttribute(attrKey)){
						this.attrValues.put(attrKey, Integer.parseInt(element.getAttribute(attrKey)));
					}
				}
				//Checks the generic type to ensure the correct put's into the structure
				try{HashMap<String, String> stringMap = (HashMap<String, String>) content;
				this.stringElements.put(key, element.getTextContent());
				}catch(Exception e){}
				try{HashMap<String, Integer> integerMap = (HashMap<String, Integer>) content;
				this.integerElements.put(key, Integer.parseInt(element.getTextContent()));
				}catch(Exception e){}
				try{HashMap<String, LinkedList<Card>> cardMap = (HashMap<String, LinkedList<Card>>) content;
				NodeList cardList = element.getElementsByTagName(key);
				LinkedList<Card> newHandCards = this.handCardElements.get(key);
				for(int i = 0; i < cardList.getLength(); i++){
					Element cardElement = (Element) cardList.item(i);
					newHandCards.add(Card.getCard(CardName.parseName(cardElement.getTextContent())));
				}
				this.handCardElements.put(key, newHandCards);
				}catch(Exception e){}
			}
		}
	}
	
	/**
	 * @author Bodo Gr�tter
	 * merges two UpdateGame_Messages together
	 * 
	 * @param a first message which gets merged with a second message
	 * @return the first message with the merged content
	 */
	public static UpdateGame_Message merge(UpdateGame_Message first, UpdateGame_Message second){
		if(first.getActions() == null)
			first.setActions(second.getActions());
		if(first.getBuys() == null)
			first.setBuys(second.getBuys());
		if(first.getCoins() == null)
			first.setCoins(second.getCoins());
		if(first.getChat() == null)
			first.setChat(second.getChat());
		if(first.getCurrentPhase() == null)
			first.setCurrentPhase(second.getCurrentPhase());
		if(first.getCurrentPlayer() == null)
			first.setCurrentPlayer(second.getCurrentPlayer());
		if(first.getDeckPileCardNumber() == null)
			first.setDeckPileCardNumber(second.getDeckPileCardNumber());
		if(first.getDiscardPileCardNumber() == null)
			first.setDiscardPileCardNumber(second.getDiscardPileCardNumber());
		if(first.getDiscardPileTopCard() == null)
			first.setDiscardPileTopCard(second.getDiscardPileTopCard());
		if(first.getLog() == null)
			first.setLog(second.getLog());
		if(first.getNewHandCards() == null)
			first.setNewHandCards(second.getNewHandCards());
		if(first.getPlayedCard() == null)
			first.setPlayedCards(second.getPlayedCard());
		if(first.getBuyedCard() == null)
			first.setBuyedCard(second.getBuyedCard());
		
		return first;
	}
	
	
	public String getCurrentPlayer(){
		return this.stringElements.get(ELEMENT_CURRENTPLAYER);
	}

	public String getCurrentPhase(){
		return this.stringElements.get(ELEMENT_CURRENTPHASE);
	}
	
	public String getChat(){
		return this.stringElements.get(ELEMENT_CHAT);
	}

	public String getLog(){
		return this.stringElements.get(ELEMENT_LOG);
	}

	public Card getPlayedCard(){
		return this.cardElements.get(ELEMENT_PLAYEDCARD);
	}

	public Card getBuyedCard(){
		return this.cardElements.get(ELEMENT_BUYEDCARD);
	}
	
	public Card getDiscardPileTopCard(){
		return this.cardElements.get(ELEMENT_DISCARDPILETOPCARD);
	}
	
	public Integer getDiscardPileCardNumber(){
		return this.attrValues.get(ATTR_DISCARDPILECARDNUMBER);
	}
	
	public Integer getDeckPileCardNumber(){
		return this.attrValues.get(ATTR_DECKPILECARDNUMBER);
	}

	public Integer getActions(){
		return this.integerElements.get(ELEMENT_ACTIONS);
	}

	public Integer getCoins(){
		return this.integerElements.get(ELEMENT_COINS);
	}
	
	public Integer getBuys(){
		return this.integerElements.get(ELEMENT_BUYS);
	}

	public LinkedList<Card> getNewHandCards(){
		return this.handCardElements.get(ELEMENT_NEWHANDCARDS);
	}


	
	public void setCurrentPlayer(String currentPlayer){
		this.currentPlayer = currentPlayer;
	}

	public void setCurrentPhase(String currentPhase){
		this.currentPhase = currentPhase;
	}

	public void setChat(String chat){
		this.chat = chat;
	}
	
	public void setLog(String log){
		this.log = log;
	}

	public void setPlayedCards(Card playedCard){
		this.playedCard = playedCard;
	}

	public void setBuyedCard(Card buyedCard){
		this.buyedCard = buyedCard;
	}
	
	public void setDiscardPileTopCard(Card discardPileTopCard){
		this.discardPileTopCard = discardPileTopCard;
	}
	
	public void setDiscardPileCardNumber(Integer discardPileCardNumber){
		this.discardPileCardNumber = discardPileCardNumber;
	}

	public void setDeckPileCardNumber(Integer deckPileCardNumber){
		this.deckPileCardNumber = deckPileCardNumber;
	}
	
	public void setActions(Integer actions){
		this.actions = actions;
	}

	public void setCoins(Integer coins){
		this.coins = coins;
	}
	
	public void setBuys(Integer buys){
		this.buys = buys;
	}

	//fill the handCardElementNames to use addContentElements
	public void setNewHandCards(LinkedList<Card> newHandCards){
		for(int i = 0; i < newHandCards.size(); i++)
			this.handCardElements.put(ELEMENT_NEWHANDCARD, newHandCards);	
	}
}//end UpdateGame_Message