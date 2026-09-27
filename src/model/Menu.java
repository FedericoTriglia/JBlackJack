package model;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Singleton class representing the main menu and user management for the blackjack game.
 * <p>
 * It handles user profiles, the dealer profile, game rules, number of decks, and the
 * currently active user. This class also extends {@link Observable} to notify observers
 * when changes occur, such as adding or removing profiles, or updating the current user.
 * </p>
 */
public class Menu extends Observable {
	private static Menu instance;
	private Profile currentUser;
	private List<Profile> userProfiles;
	private Profile dealerProfile;
	private int decksNumber;
	private boolean americanRules;
	
	/**
     * Returns the singleton instance of the Menu.
     *
     * @return the single Menu instance
     */
	public static Menu getInstance() {
		if (instance == null)
			instance = new Menu();
		return instance;
	}
	
	/**
     * Private constructor to enforce the singleton pattern.
     */
	private Menu() {
		decksNumber = 2;
		userProfiles = new ArrayList<>();
		dealerProfile = new Profile("Dealer", Avatar.DEALER);
	}
	
	/**
     * Returns the dealer profile.
     *
     * @return the dealer's Profile instance
     */
	public Profile getDealerProfile() {
		return dealerProfile;
	}
	
	/**
     * Searches for a user profile by nickname.
     *
     * @param s the nickname to search for
     * @return the Profile with the given nickname, or null if not found
     */
	public Profile hasProfile(String s) {
		for (Profile p: userProfiles)
			if (p.getNickname().equals(s))
				return p;
		return null;
	}
	
	/**
     * Returns the currently active user.
     *
     * @return the current user's Profile, or null if not assigned.
     */
	public Profile getCurrentUser() {
		return currentUser;
	}
	
	/**
     * Returns the list of user profiles.
     *
     * @return the list of Profiles
     */
	public List<Profile> getUsers() {
		return userProfiles;
	}
	
	/**
     * Returns the number of decks configured for the game.
     *
     * @return the number of decks
     */
	public int getDecksNumber() {
		return decksNumber;
	}
	
	/**
     * Sets the number of decks to use in the game.
     * Only values from 2 to 6 are accepted.
     *
     * @param n the number of decks
     */
	public void setDecksNumber(int n) {
		if (n>=2 && n<=6)
			decksNumber = n;
	}
	
	/**
     * Returns whether American blackjack rules are enabled.
     *
     * @return true if American rules are used, false otherwise
     */
	public boolean getRules() {
		return americanRules;
	}
	
	/**
     * Sets whether American blackjack rules should be used.
     *
     * @param b true to use American rules, false otherwise
     */
	public void setRules(boolean b) {
		americanRules = b;
	}
	
	/**
     * Sets the avatar for the current user.
     *
     * @param a the avatar to set
     */
	public void setAvatar(Avatar a) {
		if (a != Avatar.DEALER && currentUser != null)
			currentUser.setAvatar(a);
	}
	
	/**
     * Sets the currently active user by nickname.
     *
     * @param user the nickname of the user to set as current
     */
	public void setCurrentUser(String user) {
		if (user != null && !user.equals("Dealer"))
			for (Profile p: userProfiles)
				if (p.getNickname().equals(user))
					currentUser = p;
	}
	
	/**
     * Adds a new user profile if the nickname is valid (unique) and not already used.
     * Automatically sets the added profile as the current user.
     *
     * @param pp the Profile to add
     */
	public void addProfile(Profile pp) {
		boolean b = true;
		if (pp.getNickname().equals("Dealer") || pp.getNickname().trim().isEmpty())
			b = false;
		for (Profile p : userProfiles)
			if (pp.getNickname().equals(p.getNickname()))
				b = false;
		if (b) {
			currentUser = pp;
			userProfiles.add(currentUser);
			setChanged();
			notifyObservers(currentUser);
		}
	}
	
	/**
     * Returns an array of nicknames of all user profiles.
     * <p>
     * This method has been realized using the java Stream API
     * </p>
     *
     * @return an array of user nicknames
     */
	public String[] getNicks() {
		return userProfiles.stream() //Stream<Profile>
								.filter(p -> !p.getNickname().equals("Dealer")) //Stream<Profile>
				                .map(Profile::getNickname) //Stream<String>
				                .toArray(String[]::new);
	}
	
	/**
     * Sets the list of user profiles.
     * If no current user is set, the first profile in the list becomes the current user.
     *
     * @param profiles the list of Profiles to set
     */
	public void setProfiles(List<Profile> profiles) {
		for (Profile p: profiles)
			if (!p.getNickname().equals("Dealer")) {
				userProfiles.add(p);
				if (currentUser == null)
					currentUser = p;
			}
	}
	
	/**
     * Deletes a user profile from the menu.
     * Updates the current user if needed and notifies observers.
     *
     * @param pp the Profile to delete
     * @return true if the profile was deleted, false if not found
     */
	public boolean deleteProfile(Profile pp) {
		for (Profile p: userProfiles)
			if (p.equals(pp)) {
				if (p.equals(currentUser))
					currentUser = null;
				userProfiles.remove(p);
				if (!userProfiles.isEmpty() && currentUser == null)
					currentUser = userProfiles.get(0);
				setChanged();
				notifyObservers(pp.getNickname());
				return true;
			}
		return false;
	}
	
	/**
	 * This method returns a map associating to each profile(nickname) the related score(an integer).
	 * It has been realized using the java Stream API.
	 */
	public Map<String, Integer> getMapPlayersScore() {
		return userProfiles.stream() //Stream<Profile>
								.collect(Collectors.toMap(
											Profile::getNickname,
											Profile::getScore
								));
	}
	
	/**
	 * This method returns a map associating to each avatar(shown as a string) the set of profiles currently using it.
	 * It has been realized using the java Stream API.
	 */
	public Map<String, Set<Profile>> getMapAvatarSetPlayers() {
		return userProfiles.stream() //Stream<Profile>
								.collect(groupingBy(
										(p -> p.getAvatar().toString()),
										toSet()
								));
	}
	
	/**
	 * This method returns the set of profiles linked to the given avatar.
	 * It has been realized using the java Stream API.
	 * 
	 * @param a The avatar to use to select profiles.
	 */
	public Set<Profile> getSetPlayersForAvatar(Avatar a) {
		return userProfiles.stream() //Stream<Profile>
								.filter(p -> p.getAvatar() == a) //Stream<Profile>
								.collect(toSet());
	}
	
	/**
	 * This method returns a map associating to each score realized the related set of players.
	 * It has been realized using the Stream API.
	 */
	public Map<Integer, Set<Profile>> getMapScoreSetPlayers() {
		return userProfiles.stream() //Stream<Profile>
								.collect(groupingBy(
										Profile::getScore,
										toSet()
								));
	}
	
	/**
	 * This method returns an ordered list of the profiles(nicknames), sorting them by score(highest to lowest)
	 */
	public List<String> getTotalRanking() {
		return userProfiles.stream() //Stream<Profile>
						.sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore())) //Stream<Profile>
						.map(Profile::getNickname) //Stream<String>
						.collect(Collectors.toList()); //List<String>
	}
	
	/**
	 * This method returns an ordered list of the players, sorting them by
	 * number of hands won (highest to lowest).
	 * <p>
	 * It has been realized using the Stream API.
	 * </p>
	 */
	public List<String> getWonRanking() {
		return userProfiles.stream() //Stream<Profile>
								.sorted(Comparator.comparingInt(Profile::getWon).reversed()) //Stream<Profile>
								.map(Profile::getNickname) //Stream<String>
								.collect(Collectors.toCollection(ArrayList::new)); //ArrayList<String>
	}
	
	/**
	 * This method returns an ordered list of the players, sorting them by
	 * number of blackjack realized (highest to lowest).
	 * <p>
	 * It has been realized using the Stream API.
	 * </p>
	 */
	public List<String> getBJRanking() {
		return userProfiles.stream() //Stream<Profile>
								.sorted(Comparator.comparingInt(Profile::getBJ).reversed()) //Stream<Profile>
								.map(Profile::getNickname) //Stream<String>
								.collect(Collectors.toList()); //List<String>
	}
	
	/**
	 * This method returns an ordered list of the players, sorting them by
	 * number of hands played (highest to lowest).
	 * <p>
	 * It has been realized using the Stream API.
	 * </p>
	 */
	public List<String> getPlayedRanking() {
		return userProfiles.stream() //Stream<Profile>
								.sorted(Comparator.comparingInt(Profile::getPlayed).reversed()) //Stream<Profile>
								.map(Profile::getNickname) //Stream<String>
								.collect(Collectors.toList()); //List<String>
	}
	
	/**
	 * This method ranks the players by score, but only if the score is >= to the input integer given.
	 * <p>
	 * It has been realized using the Stream API.
	 * </p>
	 * 
	 * @param n the minimum score to use to select profiles
	 */
	public List<String> getMinTotalRanking(int n) {
		return userProfiles.stream() //Stream<Profile>
						.filter(p -> p.getScore() >= n)
						.sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore())) //Stream<Profile>
						.map(Profile::getNickname) //Stream<String>
						.collect(Collectors.toList()); //List<String>
	}
}