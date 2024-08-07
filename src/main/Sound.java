package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/backgroundMusic01.wav");
        soundURL[1] = getClass().getResource("/res/sound/getKey.wav");
        soundURL[2] = getClass().getResource("/res/sound/panicMusic01.wav");
        soundURL[3] = getClass().getResource("/res/sound/lockedDoor.wav");
        soundURL[4] = getClass().getResource("/res/sound/gameoverMusic.wav");
        soundURL[5] = getClass().getResource("/res/sound/backgroundMusic02.wav");
        soundURL[6] = getClass().getResource("/res/sound/backgroundMusic03.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();

    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop() {
        clip.stop();
    }

    public void setVolume(float volume) {
            FloatControl setVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume.setValue(volume);
    }
}
