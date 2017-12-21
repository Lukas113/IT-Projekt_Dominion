package Cards;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;

import Messages.Failure_Message;
import Messages.Interaction;
import Messages.Message;
import Messages.UpdateGame_Message;
import Server_GameLogic.Game;
import Server_GameLogic.Phase;
import Server_GameLogic.Player;

/**
 * @author Rene
 * @version 1.0
 * @created 31-Okt-2017 16:58:09
 */
public class Mine_Card extends Card {


	public Mine_Card(){
		this.cardName = CardName.Mine;
		this.cost = 5;
		this.type = CardType.Action;
	}

	/**
	 * 
	 * @param player
	 */
	@Override
	public UpdateGame_Message executeCard(Player player){
		this.player = player;
		this.game = player.getGame();
		
		UpdateGame_Message ugmsg = new UpdateGame_Message();
		
		//#DisposeCards# Chose cards to dispose
		ugmsg.setLog(player.getPlayerName()+": #played# "+"#"+this.cardName.toString()+"#"+" #card#. #DisposeCard#");
		
		// update game Messages -> XML 
		ugmsg.setInteractionType(Interaction.Mine);
		ugmsg.setPlayedCards(this);
		
		return ugmsg;
	}
	/**
	 * @author Bodo Gruetter
	 * 
	 * @param the from the player discarded Card     
	 * @return a linkedlist with all available cards
	 */
	
	public Message executeMine(Card discardedCard){
		if((discardedCard.getCardName() == CardName.Copper) || (discardedCard.getCardName() == CardName.Silver)){
			UpdateGame_Message ugmsg = new UpdateGame_Message();
			
			player.getHandCards().remove(discardedCard); // removes the selected card
			
			// add a treasure card with a higher value than the removed one
			if (discardedCard.getCardName() == CardName.Copper){
				Silver_Card silverCard = game.getSilverPile().pop();
				player.getHandCards().add(silverCard);
				ugmsg.setBoughtCard(silverCard);
				ugmsg.setLog(this.player.getPlayerName()+": #disposed# "+"#"+discardedCard.toString()+"#"+", #received# "+"#"+CardName.Silver.toString()+"#");
			} else if (discardedCard.getCardName() == CardName.Silver){
				Gold_Card goldCard = game.getGoldPile().pop();
				player.getHandCards().add(goldCard);
				ugmsg.setBoughtCard(goldCard);
				ugmsg.setLog(this.player.getPlayerName()+": #disposed# "+"#"+discardedCard.toString()+"#"+", #received# "+"#"+CardName.Gold.toString()+"#");
			}
			
			/* checks if the game is ended after playing this card and who wons
			* it
			*/
			if (this.game.checkGameEnding()) {
				this.player.setActualPhase(Phase.Ending);
				this.game.checkWinner();

				this.player.sendToOpponent(this.player, this.player.getPlayerSuccessMsg());
				return this.player.getPlayerSuccessMsg();
			}
			
			if (this.player.getActions() == 0 || !this.player.containsCardType(this.player.getHandCards(), CardType.Action))
				ugmsg = UpdateGame_Message.merge((UpdateGame_Message) this.player.skipPhase(), ugmsg);
			return ugmsg;
		}

		// returns failure if discarded card is not copper or silver
		return new Failure_Message(); 
		
		
	}
	
}//end Mine_Card