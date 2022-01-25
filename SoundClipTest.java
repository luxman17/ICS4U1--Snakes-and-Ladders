import java.net.URL;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.File;
public class SoundClipTest{
    //plays the sound
    public static void playSound(final String path){
        try{
            final File audioFile=new File(path);
            AudioInputStream audioIn=AudioSystem.getAudioInputStream(audioFile);
            Clip clip=AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            long duration=getDurationInSec(audioIn);
            //System.out.println(duration);
            //We need to delay it otherwise function will return
            //duration is in seconds we are converting it to milliseconds
            Thread.sleep(duration*1000);
        }catch(LineUnavailableException | UnsupportedAudioFileException | MalformedURLException | InterruptedException exception){
            exception.printStackTrace();
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    //Gives duration in seconds for audio files
    public static long getDurationInSec(final AudioInputStream audioIn){
        final AudioFormat format=audioIn.getFormat();
        double frameRate=format.getFrameRate();
        return (long)(audioIn.getFrameLength()/frameRate);
    }
    ////////main//////
    public static void main(String $[]){
        //SoundClipTest test=new SoundClipTest();
        SoundClipTest.playSound("/home/dev/Downloads/mixkit-sad-game-over-trombone-471.wav");
    }
}
