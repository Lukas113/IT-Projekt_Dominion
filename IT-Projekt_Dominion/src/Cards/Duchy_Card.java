package Cards;

import Server_GameLogic.Player;

/**
 * @author René
 * @version 1.0
 * @created 31-Okt-2017 16:58:06
 */
public class Duchy_Card extends Victory_Card {


	public Duchy_Card(){
		this.cardName = "Duchy";
		this.cost = 5;
		this.type = "victory";
	}

	/**
	 * 
	 * @param player
	 */
	@Override
	public void executeCard(Player player){
		player.setVictoryPoints(player.getVictoryPoints() + 3);
	}
	
}//end Duchy_Card