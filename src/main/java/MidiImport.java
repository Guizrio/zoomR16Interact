import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 23/02/2016.
 */
public class MidiImport {
    private Transmitter mid;

    public MidiImport() {
    }

    public void test(){

    }


    public static void main(String[] args) throws InterruptedException {
//        MidiImport mid = new MidiImport();
//        mid.test();
        List<Transmitter> transmitters;
        Transmitter transmitter;
        Receiver ourVirtualReceiver;
        MyReceiver myReceiver;
        Sequencer sequencer;

        MidiDevice mid = null;
        for(MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()){
            System.out.println(info.getName() + info.getDescription() + "\n\n");

            if(info.getName().equalsIgnoreCase("ZOOM R16_R24 Audio Interface")){
                System.out.println("yes");
                System.out.println(info);
                try {
                    mid = MidiSystem.getMidiDevice(info);
                    if(mid.getMaxTransmitters() == 0){  //test if device has got MIDI IN port
                        continue;
                    }
                    if(!mid.isOpen()){
                        mid.open();
                    }

                    transmitters = new ArrayList<>(mid.getTransmitters());
                    transmitter = mid.getTransmitter();

                    myReceiver = new MyReceiver();

                    transmitter.setReceiver(myReceiver);

                    Thread.sleep(10000);

//                    sequencer = MidiSystem.getSequencer();
//                    ourVirtualReceiver = sequencer.getReceiver();
//
//                    transmitter.setReceiver(ourVirtualReceiver);
//
//                    sequencer.open();
//                    Sequence sequence = null;
//                    try {
//                        sequence = new Sequence(Sequence.PPQ, 24, 1);
//                        Track track = sequence.createTrack();
//
//                        sequencer.setSequence(sequence);
//
//                        sequencer.start();
//                        sequencer.startRecording();
//
//                        for (int i = 0; i < 1000; i++) {
////                        Sequence sequence = sequencer.getSequence();
//
//                            System.out.println(track.size());
//
//                            Thread.sleep(10);
//                        }
//
//                        sequencer.stopRecording();
//                        sequencer.stop();
//
//                    } catch (InvalidMidiDataException e) {
//                        e.printStackTrace();
//                    }
//
//                    sequencer.close();

                    if(mid.isOpen()){
                        mid.close();
                    }

                    System.out.println("fini");

                } catch (MidiUnavailableException e) {
                    System.err.println("An unknow error occured. You could retry or unplug and replug the zoom R16 device");
                    e.printStackTrace();
                }
            }
        }
    }
}
