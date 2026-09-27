package model;

import java.util.Arrays;

/**
 * Avatar related enum: the idea is to bijectively associate a constant
 * with its related representation.
 */
public enum Avatar {
	DEALER, CAT, DEER, DOG, EAGLE, FOX, GORILLA, HORSE, MONKEY, OWL, PANDA, PENGUIN, RABBIT, SHARK, SNAKE, 
	TIGER, WOLF;
	
	/**
     * Returns the enum constant name formatted with only the first letter uppercase
     * and the rest lowercase.
     *
     * @return the formatted name of the enum constant
     */
	@Override
	public String toString() {
		return this.name().charAt(0)+this.name().substring(1).toLowerCase();
	}

	/**
     * Converts a string to its corresponding {@code Avatar} constant.
     * <p>
     * It has been realized using the Stream API.
     * </p>
     *
     * @param avatarString the string to convert
     * @return the matching {@code Avatar}, or {@code null} if none matches
     */
	public static Avatar fromString(String avatarString) {
		return Arrays.stream(Avatar.values()) //Stream<Avatar>
						.filter(a -> a.name().equals(avatarString.trim().toUpperCase())) //Stream<Avatar>
						.findFirst() //Optional<Avatar>
						.orElse(null); //Avatar
	}
	
	/**
     * Returns an array of avatar names.
     * <p>
     * It has been realized using the Stream API.
     * </p>
     *
     * @return array of avatar names
     */
	public static String[] getAnimals() {
		return Arrays.stream(Avatar.values()) //Stream<Avatar>
						.filter(a -> (!(a.name().equals("DEALER")))) //Stream<Avatar>
						.map(Avatar::name) //Stream<String>
						.toArray(String[]::new);
	}
}