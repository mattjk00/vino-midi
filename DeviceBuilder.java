package midi;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;

import midi.Midi;

/**
 * Used for creating and opening MIDI devices
 * @author MattConnor
 *
 */
public class DeviceBuilder {
	//private MidiDevice midiDevice;
	private MidiDevice[] midiDevices;
	
	public DeviceBuilder() {
	}
	
	/**
	 * Tries and sets up a midi device with a given device name
	 * @param deviceName
	 */
	public void setup(Midi parent, String deviceName)
	{
		Info[] info = MidiSystem.getMidiDeviceInfo();
		for (Info i : info)
		{
			if (i.getName().equals(deviceName))
			{
				try {
					midiDevices = new MidiDevice[1]; // create an array with one element
					// open the midi device and set the transmitter to read messages
					midiDevices[0] = MidiSystem.getMidiDevice(i);
					midiDevices[0].open();
					Transmitter t = midiDevices[0].getTransmitter();
					t.setReceiver(new MidiInput(parent));
				} catch (MidiUnavailableException e) {
					System.out.println("Unable to open midi device! - " + deviceName);
				}
			}
		}
		System.out.println(midiDevices[0]);
	}
	
	/**
	 * Tries to set up all available midi devices
	 */
	public void setup(Midi parent)
	{
		Info[] info = MidiSystem.getMidiDeviceInfo();
		midiDevices = new MidiDevice[info.length]; // create an array for all midi devices
		for (int i = 0; i < info.length; i++)
		{
			try {
				// open the midi device and set the transmitter to read messages
				midiDevices[i] = MidiSystem.getMidiDevice(info[i]);
				midiDevices[i].open();
				Transmitter t = midiDevices[i].getTransmitter();
				t.setReceiver(new MidiInput(parent));
			} catch (MidiUnavailableException e) {
				System.out.println("Unable to open midi device! - " + midiDevices[i].getDeviceInfo().getName());
				midiDevices[i] = null;
			}
		}
		// clean up the array
		midiDevices = cleanArray(midiDevices);
		for (MidiDevice md : midiDevices) {
			System.out.println("Opened: " + md.getDeviceInfo().getName());
		}
	}
	
	/**
	 * Cleans out any unopened or null MIDI devices from a given array
	 * @param d
	 * @return
	 */
	private MidiDevice[] cleanArray(MidiDevice[] d) {
		int deviceCount = 0;
		for (MidiDevice device : d) {
			if (device != null) {
				deviceCount++;
			}
		}
		MidiDevice[] clean = new MidiDevice[deviceCount];
		int added = 0;
		for (MidiDevice device : d) {
			if (device != null && added < deviceCount) {
				clean[added++] = device;
			}
		}
		return clean;
	}
	
	/**
	 * Closes any open MIDI devices
	 */
	public void close()
	{
		for (MidiDevice device : midiDevices) {
			if (device != null) {
				device.close();
			}
		}
	}
}
