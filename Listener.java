package midi;

/**
 * Can handle incoming MIDI messages
 * @author Matthew
 *
 */
public interface Listener {
	// these methods are called when a note ON or OFF message is received
	public void noteOn(Note note);
	public void noteOff(Note note);
	public void parameterChange(byte value);
}
