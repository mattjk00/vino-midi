package midi;

/**
 * A MIDI Note. Has data to identify the channel, pitch, and velocity of the note.
 * @author Matthew
 *
 */
public class Note {
	private static final String[] NOTE_NAMES = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
	protected int channel;
	protected int pitch;
	protected int velocity;	

	/**
	 * Creates a note object from channel 0, with a pitch of 0 and velocity of 0
	 */
	public Note() {
		channel = 0;
		pitch = 0;
		velocity = 0;
	}

	/**
	 * Creates a note object with the given parameters
	 * @param channel
	 * @param pitch
	 * @param velocity
	 */
	public Note(int channel, int pitch, int velocity) {
		this.channel = channel;
		this.pitch = pitch;
		this.velocity = velocity;
	}

	/**
	 * Converts the notes pitch to a readable note name
	 * @return
	 */
	public String name() {
		// get the index of the pitch in the array
		int noteIndex = this.pitch % 12;
		// get the num e.g. C4, C-2
		int noteNum = (this.pitch / 12) - 2; 
		// return formatted name
		return NOTE_NAMES[noteIndex] + noteNum;
	}
	
	public static String name(int pitch) {
		// get the index of the pitch in the array
		int noteIndex = pitch % 12;
		// get the num e.g. C4, C-2
		int noteNum = (pitch / 12) - 1; 
		// return formatted name
		return NOTE_NAMES[noteIndex] + noteNum;
	}
	
	/**
	 * Finds the pitch of a given note name
	 * @param noteName
	 * @return
	 */
	public static int findPitch(String noteName) {
		for (int i = 1; i <= 127; i++) {
			if (noteName.equals(name(i))) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gives a String representation of the note
	 */
	public String toString() {
		return this.name() + " | velocity: " + this.velocity;
	}

	/**
	 * Returns true if this note equals another note. If the given note does not have a 
	 * number indication (e.g. C4, D2), the note will match with any octave
	 * @param note
	 * @return
	 */
	public Boolean equalsNote(String noteName) {
		return (this.name().contains(noteName));
	}
	
	/**
	 * Returns true if the given note has the exact same channel, pitch, and velocity
	 * @param note
	 * @return
	 */
	public Boolean equalsExact(Note note) {
		return (note.channel == this.channel && note.pitch == this.pitch && note.velocity == this.velocity);
	}
	
	/**
	 * Returns true if the given note has the same pitch as this note
	 * @param note
	 * @return
	 */
	public Boolean equalsPitch(Note note) {
		return (note.pitch == this.pitch);
	}
}
