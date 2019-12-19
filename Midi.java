package midi;

import java.util.LinkedList;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiDevice.Info;

/**
 * Used for handling MIDI information
 * @author Matthew
 *
 */
public class Midi {
	private DeviceBuilder deviceBuilder;
	private LinkedList<Listener> listeners;
	
	public Midi() {
		deviceBuilder = new DeviceBuilder();
		deviceBuilder.setup(this);
		listeners = new LinkedList<Listener>();
	}
	
	public Midi(String device) {
		deviceBuilder = new DeviceBuilder();
		deviceBuilder.setup(this, device);
		listeners = new LinkedList<Listener>();
	}
	
	/**
	 * Prints all device info
	 */
	public static void printDevices() {
		Info[] info = MidiSystem.getMidiDeviceInfo();
		for (Info i : info) {
			System.out.println(i);
		}
	}
	
	/**
	 * Adds a listener for MIDI messages
	 * @param l
	 */
	public void addListener(Listener l) {
		if (l == null) {
			throw new NullPointerException("Listener cannot be null.");
		} else {
			listeners.add(l);
		}
	}
	
	/**
	 * Called when a note ON message is received
	 * @param note
	 */
	public void receivedOnMessage(Note note) {
		for (Listener l : listeners) {
			l.noteOn(note);
		}
	}
	
	/**
	 * Called when a note OFF message is received
	 * @param note
	 */
	public void receivedOffMessage(Note note) {
		for (Listener l : listeners) {
			l.noteOff(note);
		}
	}
	
	/**
	 * Called when a parameter (like a rotary) is changed
	 * @param value
	 */
	public void receivedParameterChange(byte value) {
		for (Listener l : listeners) {
			l.parameterChange(value);
		}
	}
	
	/**
	 * Closes all opened devices
	 */
	public void close() {
		deviceBuilder.close();
	}
}
