package lecteur;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioControledPlayer implements Runnable {

    private SourceDataLine line;
    private File file;
    private AudioFormat audioFormat;
    private AudioInputStream audioInputStream;
    private AudioInputStream audioInputStream0;
    private Mixer mixer;
    private FloatControl masterGainControl;
    private BooleanControl muteControl;
    private FloatControl panControl;
    private FloatControl sampleRateControl; 
    private boolean stop = false;
    private static final int BUFFER_SIZE = 176400; // 44100 x 16 x 2 / 8
    public void stop() {
        stop = true;
    }

    public void init() {
    	byte[]  buffer = new byte[BUFFER_SIZE];
        try {
        	// Pour pouvoir Lire les fichier nous devons dabors le convertir sous format PCM
        	System.out.println(" acp "+file.getPath());
        	audioInputStream0 = AudioSystem.getAudioInputStream(file);
            audioInputStream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,audioInputStream0);
            
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        //Recherche du mixer logiciel Java Sound Audio Engine
        for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {

            if (mixerInfo.getName().compareTo("Java Sound Audio Engine") == 0) {
                mixer = AudioSystem.getMixer(mixerInfo);
                break;
            }

        }

        // Il est n�cessaire de conna�tre le format audio du fichier
        // d'entr�e
        // pour permettre � java de cr�er l'objet DataLine ad�quat
        audioFormat = audioInputStream.getFormat();

        // En plus du format du flux audio d'entr�e il est n�cessaire de
        // sp�cifier le type de DataLine qu'on veut
        // ici le DataLine qu'on souhaite est un SourceDataLine qui permet
        // la
        // lecture (targetDataLine permet l'enregistrement).

        DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                audioFormat);

        // On r�cup�re le DataLine ad�quat et on l'ouvre
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            //line = (SourceDataLine) mixer.getLine(info);
             line.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

        if (line.isControlSupported(BooleanControl.Type.MUTE)) {
            muteControl = (BooleanControl) line
                    .getControl(BooleanControl.Type.MUTE);
        }
        if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            masterGainControl = (FloatControl) line
                    .getControl(FloatControl.Type.MASTER_GAIN);
        }
        if (line.isControlSupported(FloatControl.Type.PAN)) {
            panControl = (FloatControl) line.getControl(FloatControl.Type.PAN);
        }
        if (line.isControlSupported(FloatControl.Type.SAMPLE_RATE)) {
            sampleRateControl = (FloatControl) line
                    .getControl(FloatControl.Type.SAMPLE_RATE);

        }

    }

    public void run() {

        stop = false;
        // Avant toute chose il est n�cessaire d'ouvrir la ligne
        try {
            line.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
            // TODO Auto-generated catch block

        }
        // pour que le flux soit effectivement redirig� sur la carte son il
        // faut
        // demarrer la ligne
        line.start();

        // il faut maintenant �crire sur la ligne. Travail comme sur un
        // inputStream quelconque
        try {
            byte bytes[] = new byte[1024];
            int bytesRead = 0;
            while (((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1)
                    && !stop) {

                line.write(bytes, 0, bytesRead);
            }

            audioInputStream.close();
        } catch (IOException io) {
            io.printStackTrace();
            return;
        }

        // on ferme la ligne � la fin
        line.close();
    }

    public void setFile(File file) {
        this.file = file;
    }

    public SourceDataLine getLine() {
        return line;
    }

    public void test() {
        System.out.println("HELLLO");
    }

    public FloatControl getMasterGainControl() {
        return masterGainControl;
    }

    public void setMasterGainControl(FloatControl masterGainControl) {
        this.masterGainControl = masterGainControl;
    }

    public BooleanControl getMuteControl() {
        return muteControl;
    }

    public void setMuteControl(BooleanControl muteControl) {
        this.muteControl = muteControl;
    }

    public FloatControl getPanControl() {
        return panControl;
    }

    public void setPanControl(FloatControl panControl) {
        this.panControl = panControl;
    }

    public FloatControl getSampleRateControl() {
        return sampleRateControl;
    }

    public void setSampleRateControl(FloatControl sampleRateControl) {
        this.sampleRateControl = sampleRateControl;
    }

}

