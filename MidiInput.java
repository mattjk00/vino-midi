package midi;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import midi.Midi;
public class MidiInput implements Receiver {
	private Midi parent;
	
	public MidiInput(Midi parent) {
		this.parent = parent;
	}
	
	/**
	 * Is called when a MIDI message is received
	 */
	@Override
	public void send(MidiMessage msg, long timeStamp) {
		// extract the bytes from the message
		byte[] data = msg.getMessage();
		// parse the bytes into a note object
    	Note note = new Note(data[0], data[1], data[2]);

    	if (data[0] == -128) {
    		parent.receivedOffMessage(new Note(data[0], data[1], data[2]));
    	}
    	else if (data[0] == -112) {
    		parent.receivedOnMessage(new Note(data[0], data[1], data[2]));
    	}
    	else if (data[0] == -80) { 
    		parent.receivedParameterChange(data[2]);
    	}
    	else {
    		System.out.println("MIDI: {" + data[0] + ", " + data[1] + ", " + data[2] + "}");
    	}
	}
	
	@Override
	public void close() {
	}
}
