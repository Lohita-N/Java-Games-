package game;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound 
{
	private Clip clip;
	private AudioInputStream AIS;
	
	Sound(String file)
	{
		try
		{
			AIS  = AudioSystem.getAudioInputStream(new File(file));
			clip = AudioSystem.getClip();
			clip.open(AIS);
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
	}
	
	public void play(int volumControl)
	{
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(volumControl);
		clip.loop(1);
	}
}
