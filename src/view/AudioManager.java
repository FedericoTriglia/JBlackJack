package view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Singleton class responsible for managing audio playback.
 * <p>
 * Provides a simple method to play audio files stored in the "resources" folder.
 * Only one instance of this class exists during the application's lifetime.
 * </p>
 */
public class AudioManager {
	private static AudioManager instance;
	
	/**
     * Returns the single instance of the AudioManager.
     *
     * @return the AudioManager instance
     */
	public static AudioManager getInstance() {
		if (instance == null)
			instance = new AudioManager();
		return instance;
	}
	
	/**
     * Private constructor to enforce singleton pattern.
     */
	private AudioManager() {
	}
	
	/**
     * Plays an audio file from the "resources" folder.
     * <p>
     * The method supports audio files compatible with javax.sound.sampled.
     * If an exception occurs while loading or playing the audio, the stack trace is printed.
     * </p>
     *
     * @param filename the name of the audio file to play (relative to the "resources" folder)
     */
	public void play(String filename) {
		Path path = FileSystems.getDefault().getPath("");
		path = path.toAbsolutePath();
		String pathAudio = path + "/resources/" + filename;
		try {
			InputStream in = new FileInputStream(pathAudio);
			in = new BufferedInputStream(in);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
}