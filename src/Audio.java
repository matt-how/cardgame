import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import java.io.IOException;
import java.nio.file.Paths;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Lewis on 2/22/2017.
 */

public class Audio {
	
	//Creating instantiations of the sound buffers and sounds we are going to use
	
    public static SoundBuffer soundBuffer = new SoundBuffer();
    public static Sound sound = new Sound();
    public static void main(){

    }
	
	/*
	*	The two methods below are called by driver to mute and set volume in the options menu
	*/	

    public static void volumeOn()
    {
        sound.setVolume(100);
    }

    public static void volumeOff()
    {
        sound.setVolume(0);
    }

	/*
	*	The methods below all play sounds for each fx within the game,
	*	they work by creating a sound buffer that loads the wav file needed
	*	for that particular sound effect in to memory, ready for a sound
	*	method to play them
	*/
	
	
	/* Button click sound effect */
    public static void buttonClick()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("button.wav"));
        } catch (IOException ex) {
            System.err.println("Failed to load the sound:");}
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* sword sound effect */
    public static void sword()
    {
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/sword.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* Ice attack sound effect */
    public static void blizzard()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/blizzard.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }
	
	/* Fireball 1 sound effect */
    public static void fireball()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/fireball1.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }
	
	/* Fireball 2 sound effect */
    public static void fireball2()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/fireball2.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* Electric buff sound effect */
    public static void electricBoost()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/electricboost.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* Earthtype boost sound effect */
    public static void earthBoost()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/earthboost.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* Healing rain sound effect */
    public static void healingRain()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/rainheal.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* Poison dart sound effect */
    public static void poisonDart()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/poisondart.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* Ice bolt sound effect */
    public static void iceBolt()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/icebolt.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }
	
	/* Lightning attack sound effect */
    public static void lightning()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/lightningbolt.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* Running buff sound effect */
    public static void running()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/running.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }

	/* Water type buff sound effect */
    public static void waterBoost()
    {
        SoundBuffer soundBuffer = new SoundBuffer();
        try
        {
            soundBuffer.loadFromFile(Paths.get("gamesounds/waterboost.wav"));
        } catch (IOException ex){
            System.err.println("Err: File not found");
        }
        sound.setBuffer(soundBuffer);
        sound.play();
    }
}
