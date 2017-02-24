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
    public static SoundBuffer soundBuffer = new SoundBuffer();
    public static Sound sound = new Sound();
    public static void main(){

    }

    public static void volumeOn()
    {
        sound.setVolume(100);
    }

    public static void volumeOff()
    {
        sound.setVolume(0);
    }

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
