package lecteur;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import interfaces.Acceuil;



public class AudioControledPlayerGUI extends JPanel {

    private Thread audioPlayerThread;
    private AudioControledPlayer player;
    private Action load;
    private Action play;
    private Action stop;
    private JCheckBox mute;
    private JFrame frame;
   // private JPanel contentPane;
    private JFileChooser chooser;
    private File audioFile;
    private JSlider panSlider;
    private JSlider gainSlider;
    private JSlider sampleRateSlider;
    private JPanel controls;
    private JLabel title;
    private JLabel panLabel;
    private JLabel gainLabel;
    private JLabel sampleRateLabel;

    public void initPlayerAndControls() {
        player.init();

        title.setText("Lecture en cours: " + audioFile.getName());

        if (controls != null)
            this.remove(controls);
        controls = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        if (player.getPanControl() != null) {
            panSlider = new JSlider(
                    (int) player.getPanControl().getMinimum() * 10,
                    (int) player.getPanControl().getMaximum() * 10);
            panSlider.setValue((int) player.getPanControl().getValue());
            panSlider.setPaintLabels(true);
            panSlider.setPaintTicks(true);
            panSlider.setPaintTrack(true);
            panSlider.setMinorTickSpacing(1);
            panSlider.setMajorTickSpacing((int) player.getPanControl()
                    .getMaximum()
                    * 10 - (int) player.getPanControl().getMinimum() * 10);
            panSlider.setSnapToTicks(true);

            panLabel = new JLabel("Pan:" + (float) panSlider.getValue());
            gbc.weightx = 0;
            gbc.gridwidth = 1;
            controls.add(panLabel, gbc);
            gbc.weightx = 1;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            controls.add(panSlider, gbc);

            panSlider.addChangeListener(new ChangeListener() {

                public void stateChanged(ChangeEvent arg0) {

                    float pan = panSlider.getValue();
                    panLabel.setText("Pan:" + pan);
                    player.getPanControl().setValue(pan);

                }

            });

        }

        if (player.getMasterGainControl() != null) {
            gainSlider = new JSlider((int) player.getMasterGainControl()
                    .getMinimum(), (int) player.getMasterGainControl()
                    .getMaximum());
            gainSlider.setValue((int) player.getMasterGainControl().getValue());
            gainSlider.setPaintLabels(true);
            gainSlider.setPaintTicks(true);
            gainSlider.setPaintTrack(true);
            gainSlider.setMinorTickSpacing(1);
            gainSlider.setMajorTickSpacing((int) player.getMasterGainControl()
                    .getMaximum()
                    - (int) player.getMasterGainControl().getMinimum());
            gainSlider.setSnapToTicks(true);

            gainLabel = new JLabel("Gain:" + (float) gainSlider.getValue());
            gbc.weightx = 0;
            gbc.gridwidth = 1;
            controls.add(gainLabel, gbc);
            gbc.weightx = 1;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            controls.add(gainSlider, gbc);

            gainSlider.addChangeListener(new ChangeListener() {

                public void stateChanged(ChangeEvent arg0) {

                    float gain = gainSlider.getValue();
                    gainLabel.setText("Gain:" + gain);
                    player.getMasterGainControl().setValue(gain);

                }

            });

        }

        if (player.getSampleRateControl() != null) {
            sampleRateSlider = new JSlider((int) player.getSampleRateControl()
                    .getMinimum(), (int) player.getSampleRateControl()
                    .getMaximum());
            sampleRateSlider.setValue((int) player.getSampleRateControl()
                    .getValue());
            sampleRateSlider.setPaintLabels(true);
            sampleRateSlider.setPaintTicks(true);
            sampleRateSlider.setPaintTrack(true);
            sampleRateSlider.setMinorTickSpacing(1);
            sampleRateSlider.setMajorTickSpacing((int) player
                    .getSampleRateControl().getMaximum()
                    - (int) player.getSampleRateControl().getMinimum());
            sampleRateSlider.setSnapToTicks(true);

            sampleRateLabel = new JLabel("Rate:"
                    + (float) sampleRateSlider.getValue());
            gbc.weightx = 0;
            gbc.gridwidth = 1;
            gbc.weighty = 1.0;
            controls.add(sampleRateLabel, gbc);
            gbc.weightx = 1;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            controls.add(sampleRateSlider, gbc);

            sampleRateSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent arg0) {

                    float sampleRate = sampleRateSlider.getValue();
                    sampleRateLabel.setText("Rate:" + sampleRate);
                    player.getSampleRateControl().setValue(sampleRate);

                }

            });

        }

        if (player.getMuteControl() != null) {

            mute = new JCheckBox();
            mute.setText("Mute");

            gbc.weightx = 0;
            gbc.gridwidth = 1;
            gbc.weighty = 1.0;
            controls.add(mute, gbc);

            mute.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent a) {
                    player.getMuteControl().setValue(
                            !player.getMuteControl().getValue());

                }

            });

        }

        controls.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.BLUE), "Controls"));
        this.add(controls, BorderLayout.SOUTH);
        this.paintAll(this.getGraphics());
    }

    public AudioControledPlayerGUI() {

        player = new AudioControledPlayer();
        //frame = new JFrame("AudioPlayer");
       // contentPane = new JPanel(new BorderLayout());
        //chooser = new JFileChooser();
//        chooser.setFileFilter(new FileFilter() {
//            @Override
//            public boolean accept(File f) {
//                if (f.getName().endsWith(".wav"))
//                    return true;
//                else if(f.getName().endsWith(".mp3"))
//                    return true;
//                else
//                	return false;
//            }
//
//            @Override
//            public String getDescription() {
//                return null;
//            }
//        });

        this.setPreferredSize(new Dimension(400, 300));

        stop = new AbstractAction() {

            public void actionPerformed(ActionEvent arg0) {
                // Thread.currentThread().interrupt();
                player.stop();
            }
        };
        stop.putValue(Action.SMALL_ICON, new ImageIcon("D:\\ESGI\\projets\\projet annuel\\2013-2014\\Java\\images\\boutonStop.png"));

        controls = new JPanel();
        controls.setPreferredSize(new Dimension(300,300));

        panSlider=new JSlider();
        controls.add(panSlider);

        final JSlider lostSlider = new JSlider(0, 100);

        play = new AbstractAction() {
        	
            public void actionPerformed(ActionEvent arg0) {
            	if(audioFile != null){
            	 player.setFile(audioFile);
                
                initPlayerAndControls();
               System.out.println(audioFile.getPath());
                 player.setFile(audioFile);

                audioPlayerThread = new Thread(player);
                audioPlayerThread.start();
                player.getLine().addLineListener(new LineListener() {

                    public void update(LineEvent le) {
                        if (le.getType() == LineEvent.Type.STOP) {
                            play.setEnabled(true);
                            stop.setEnabled(false);
                        }
                        if (le.getType() == LineEvent.Type.START) {
                            play.setEnabled(false);
                            stop.setEnabled(true);
                        }
                    }
                });
            }}
        };

        play.putValue(Action.SMALL_ICON, new ImageIcon("D:\\ESGI\\projets\\projet annuel\\2013-2014\\Java\\images\\boutonLecture.png"));

//        load = new AbstractAction() {
//            public void actionPerformed(ActionEvent arg0) {
////                int returnVal = chooser.showOpenDialog(frame);
////                if (returnVal == JFileChooser.APPROVE_OPTION) {
//
//                   // audioFile = chooser.getSelectedFile();
//                    System.out.print(audioFile.getName());
//                    player.setFile(audioFile);
//                   
//                    initPlayerAndControls();
//                  
//
//             
//            }
//        };
//        load.putValue(Action.SMALL_ICON, new ImageIcon("load.png"));

        JToolBar toolbar = new JToolBar();
        toolbar.add(load);
        toolbar.add(play);
        toolbar.add(stop);
        //toolbar.add(mute);

        this.add(toolbar, BorderLayout.NORTH);
        title = new JLabel("");
        this.add(title, BorderLayout.CENTER);
        this.add(controls,BorderLayout.SOUTH);


    }

	public File getAudioFile() {
		return audioFile;
	}

	public void setAudioFile(File audioFile) {
		this.audioFile = audioFile;
	}
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        new AudioControledPlayerGUI();
    }
    

}

