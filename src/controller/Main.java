package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import model.Database;
import model.Dealer;
import model.DoubleDownStrategy;
import model.Game;
import model.HitStrategy;
import model.Menu;
import model.Profile;
import model.SplitStrategy;
import model.StandStrategy;
import model.User;
import view.MainView;

/**
 * Entry point for the Blackjack application.
 * <p>
 * This class initializes the application by setting up the model, view, and
 * controller, and manages the main game loop.
 * </p>
 * <p>
 * The main responsibilities include:
 * <ul>
 *   <li>Loading existing user profiles from the database</li>
 *   <li>Initializing the main view and linking ActionListeners to the related Swing components</li>
 *   <li>Managing the game loop, including user turn, dealer turn, and result(s) handling</li>
 *   <li>Updating the database after each turn or after a new profile gets registered/deleted.</li>
 * </ul>
 * </p>
 * <p>
 * The game loop processes the gameplay according to the rules of the game, including
 * support for splitting, doubling down, hitting, and standing.
 * </p>
 */
public class Main {

	/**
     * Main method of the application.
     * <p>
     * Initializes the model and view, sets up all ActionListeners, and
     * manages the game loop for JBlackjack.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
	public static void main(String[] args) {
		//creating the model
		Menu menu = Menu.getInstance();
		Database db = Database.getInstance();
		try {
			db.load();
		} catch(IOException e) {
		}
		List<Profile> profili = db.getProfiles();
		menu.setProfiles(profili);
		
		//creating the view and making it visible
		MainView view = MainView.getInstance();
		view.setVisible(true);
		//applying the Observer pattern
		menu.addObserver(view);
		//creating menu's action listeners
		NewGameActionListener ngal = new NewGameActionListener();
		RegistrationActionListener real = new RegistrationActionListener();
		RulesActionListener rual = new RulesActionListener();
		UserSelectionActionListener ssal = new UserSelectionActionListener();
		DeleteActionListener delAl = new DeleteActionListener();
		//linking the action listeners to the buttons
		view.addNewGameListener(ngal);
		view.addRegistrationListener(real);
		view.addRulesListener(rual);
		view.addUserSelectionListener(ssal);
		view.addDeleteActionListener(delAl);
		
		//game loop starts
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//waiting for the player to start the game
			if (Game.initialized()) {
				//creates the game model
				Game game = Game.getInstance();
				User user = game.getUser();
				Dealer dealer = game.getDealer();
				//applying Observer-Observable between model and view
				game.addObserver(view);
				view.setGamePanel();
				menu.deleteObserver(view);
				user.addObserver(view);
				dealer.addObserver(view);
				
				//creating game's action listeners
				HitActionListener hal = new HitActionListener();
				StandActionListener sal = new StandActionListener();
				SplitActionListener spal = new SplitActionListener();
				DoubleDownActionListener dal = new DoubleDownActionListener();
				StartTurnActionListener stal = new StartTurnActionListener();
				//linking the listeners to the buttons
				view.addStandButtonListener(sal);
				view.addHitButtonListener(hal);
				view.addSplitButtonListener(spal);
				view.addDoubleDownButtonListener(dal);
				view.addStartButtonListener(stal);
				//making the game buttons not clickable
				view.setHitButtonState(false);
				view.setStandButtonState(false);
				view.setSplitButtonState(false);
				view.setDoubleDownButtonState(false);
				//waiting for the player to start the turn(by doing so, i sets his own bet)
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (game.getUserBet() >= 5) {
						//disabling the buttons related the first phase of the game
						view.setStartButtonState(false);
						view.setBetSpinnerState(false);
						//shuffling the deck
						game.shuffle();
						//making the main thread sleep
						try {
							Thread.sleep(700);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						game.giveCards();
						
						//another sleep
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//deciding whether the user can play
						if (!(dealer.getHand().handValue() == 21 && game.getRules() == false)) {
							//if he can play, the related buttons are linked with the listeners
							view.setHitButtonState(true);
							view.setStandButtonState(true);
							if (game.splittable())
								view.setSplitButtonState(true);
							if (game.doubleDownable())
								view.setDoubleDownButtonState(true);
							//his turn starts
							while (true) {
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								//if he got the best score possible
								if (user.getHand().handValue() == 21) {
									//and he's playing his last hand
									if (user.lastHand()) {
										//he ends his turn
										view.setHitButtonState(false);
										view.setStandButtonState(false);
										view.setSplitButtonState(false);
										view.setDoubleDownButtonState(false);
										break;
									}
									else {
										//he plays his other hand
										user.currentHand(2);
										user.setStrategy(null);
										if (game.doubleDownable())
											view.setDoubleDownButtonState(true);
									}
								}
								else if (user.getStrategy() instanceof SplitStrategy) {
									//player decided to split
									view.setSplitButtonState(false);
									if (!game.doubleDownable())
										view.setDoubleDownButtonState(false);
								}
								else if (user.getStrategy() instanceof DoubleDownStrategy) {
									//player decided to doubledown (hit + end of turn)
									//hit
									view.setSplitButtonState(false);
									if (user.lastHand()) {
										view.setDoubleDownButtonState(false);
										view.setHitButtonState(false);
										view.setStandButtonState(false);
										//his turn ends
										break;
									}
									else {
										//if he has another hand to play, then he keeps playing
										user.currentHand(2);
										user.setStrategy(null);
										if (!game.doubleDownable())
											view.setDoubleDownButtonState(false);
									}
								}
								else if (user.getStrategy() instanceof HitStrategy) {
									//if the user wants to hit
									view.setSplitButtonState(false);
									view.setDoubleDownButtonState(false);
									if (user.isBusted() || game.getUser().handValue() == 21) {
										//the added card can end his turn
										if (user.lastHand()) {
											view.setStandButtonState(false);
											view.setHitButtonState(false);
											break;
										}
										//or forces him to play the other hand
										else {
											user.currentHand(2);
											user.setStrategy(null);
											if (game.doubleDownable())
												view.setDoubleDownButtonState(true);
										}
									}
								}
								else if (user.getStrategy() instanceof StandStrategy) {
									//if the player wants to stand
									view.setSplitButtonState(false);
									//and has no other hands to play, the turn ends
									if (user.lastHand()) {
										view.setDoubleDownButtonState(false);
										view.setHitButtonState(false);
										view.setStandButtonState(false);
										break;
									}
									else {
										if (user.getSecondHand().handValue()==21) {
											view.setDoubleDownButtonState(false);
											view.setHitButtonState(false);
											view.setStandButtonState(false);
											break;
										}
										//otherwise he keeps playing
										else {
											user.currentHand(2);
											user.setStrategy(null);
											if (game.doubleDownable())
												view.setDoubleDownButtonState(true);
										}
									}
								}
								//if nothing stopped his turn, he has to make another choice
								else continue;
								
							}
						}
						//otherwise the turn ends
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						//if playing with USA rules, the dealer discovers the second card
						if (Game.getInstance().getRules())
							game.discoverSecond();
						
						//pause
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//if the dealer has to play, he gives himself a card until hitting 17 or higher
						if (dealer.canPlay(user)) {
							while(dealer.getHand().handValue() < 17) {
								dealer.play();
								//pause
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						//then the results are shown
						game.setResultHand(1);
						
						//pause after the first result is shown
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//if required(the player decided to split), the other result is shown
						if (user.getSecondHand()!=null) {
							game.removeResult();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							game.setResultHand(2);
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						//updating database before ending the turn
						try {
							Database.getInstance().save();
						} catch(FileNotFoundException exc) {
						} catch (IOException exception) {
							System.err.println("Invalid format");
						}
						
						//resetting anything related to the turn just played
						game.reset();
						
						//activating again the start button and bet spinner
						view.setStartButtonState(true);
						view.setBetSpinnerState(true);
					}
					//nothing happens until the player sets his bet and decides to start his turn
					else continue;
				}
			}
			//the game doesn't start unless the player clicks on the "New Game" button
			else continue;
		}
	}
}