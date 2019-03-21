package manager;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundManager {
    private String path;
    private Clip clip;
    private AudioInputStream sound;

    public SoundManager(String path) {
        this.path = path;
    }

    public void init() {
        try {
            sound =
                    AudioSystem.getAudioInputStream(
                            SoundManager.class.getResource(
                                    path
                            )
                    );
            clip = AudioSystem.getClip();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (LineUnavailableException e){
            e.printStackTrace();
        }
    }

    public void loop(int count) {
        if (clip != null) {
            clip.loop(count);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip = null;
        }
    }

    public void play() {
        if (clip == null) {
            return;
        }
        try {
            if (!clip.isOpen()) {
                clip.open(sound);
            }
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
