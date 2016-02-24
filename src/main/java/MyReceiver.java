import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 24/02/2016.
 */
public class MyReceiver implements Receiver {

    ArrayList<MidiMessage> messages;

    public MyReceiver() {
        messages = new ArrayList<>();
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {

        messages.add(message);

        if(message != null) {
            for (int i = 0; i < message.getMessage().length; i++) {
                System.out.print("\tByte" + i + ": " + message.getMessage()[i]);
            }
            System.out.println("");
        }
    }

    @Override
    public void close() {

    }

}
