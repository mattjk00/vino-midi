# Vino-Midi

A simple wrapper on the Java Midi API. The goal of vino-midi is to make it easier to setup a midi event listener in java.

Example:
```java
Midi midi = new Midi();
midi.addListener(new Listener() {
    @Override
    public void noteOn(Note note) {
        System.out.println("On: " + note.name());
    }
    @Override
    public void noteOff(Note note) {
        System.out.println("Off: " + note.name());
    }
    @Override
    public void parameterChange(byte value) {
        System.out.println(value);
    }
});
```