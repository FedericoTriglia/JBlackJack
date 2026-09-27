package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Singleton class that manages the persistence of user profiles.
 * <p>
 * Profiles are stored in memory as a list and can be saved to or
 * loaded from a file located in the {@code data/} directory.
 * </p>
 */
public class Database {
    private static final String NOME_FILE = "JBJ.data";
    private List<Profile> profiles;
    private static Database instance;
    
    
    /**
     * Returns the single instance of this database.
     * If it does not exist yet, it is created.
     *
     * @return the database singleton instance
     */
    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }
    
    /**
     * Creates a new empty database.
     * <p>
     * Private constructor to enforce the singleton pattern.
     * </p>
     */
    private Database() {
        profiles = new ArrayList<>();
    }
    
    /**
     * Adds a profile to the database.
     *
     * @param p the profile to add
     */
    public void addProfile(Profile p) {
        profiles.add(p);
    }
    
    /**
     * Removes a profile from the database.
     *
     * @param p the profile to remove
     */
    public void removeProfile(Profile p) {
    	profiles.remove(p);
    }

    /**
     * Returns all profiles currently in the database.
     *
     * @return the list of profiles
     */
    public List<Profile> getProfiles() {
        return profiles;
    }

    /**
     * Saves the profiles to the data file.
     * <p>
     * If the {@code data/} directory does not exist, it is created.
     * Each profile is serialized to a single line using
     * {@link Profile#castToString()}.
     * </p>
     *
     * @throws IOException if an I/O error occurs while writing
     */
    public void save() throws IOException {
        Files.createDirectories(Paths.get("data"));
        
        FileWriter writer = null;
        try {
            writer = new FileWriter("data/"+NOME_FILE);
            for (Profile profile : profiles) {
                writer.append(profile+"\n");
            }
        } finally {
            if (writer != null) writer.close();
        }
    }
    
    /**
     * Loads profiles from the data file and adds them to the current list.
     * <p>
     * Each line of the file is deserialized into a {@link Profile}
     * using {@link Profile#castFromString(String)}.
     * </p>
     *
     * @throws IOException if an I/O error occurs while reading
     */
    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/"+NOME_FILE));
        String line = reader.readLine();
        while (line != null) {
            Optional.of(Profile.castFromString(line)).ifPresent(profiles::add);
            line = reader.readLine();
        }
        reader.close();
    }
}