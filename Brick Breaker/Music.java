package game;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

	public class Music extends Thread
	{
		File file;
		AudioInputStream audioStream;
		Clip clip;

@Override
	public void run()
	{
	try
	{
		file = new File("src/images/RR2.wav");
		audioStream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
	{
		e.printStackTrace();
	}

	}
}