package org.geogebra.desktop.sound;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.SwingUtilities;

import org.geogebra.common.jre.util.Base64;
import org.geogebra.common.kernel.geos.GeoAudio;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoFunction;
import org.geogebra.common.main.App;
import org.geogebra.common.sound.SoundManager;
import org.geogebra.common.util.AsyncOperation;
import org.geogebra.common.util.StringUtil;
import org.geogebra.common.util.debug.Log;
import org.geogebra.desktop.sound.mp3transform.Decoder;

/**
 * Class to handle GeoGebra sound features. Calls to midi and streaming audio
 * methods are managed from here.
 * 
 * @author G. Sturr
 * 
 */
public class SoundManagerD implements SoundManager {

	private App app;
	private MidiSoundD midiSound;
	private FunctionSoundD functionSound;

	private static final int SOUNDTYPE_NONE = -1;
	private static final int SOUNDTYPE_MIDI = 0;
	private static final int SOUNDTYPE_FUNCTION = 1;
	int currentSoundType = SOUNDTYPE_NONE;

	private boolean isRunning = false;
	private boolean isPaused = false;

	/**
	 * Constructor
	 * 
	 * @param app
	 */
	public SoundManagerD(App app) {
		this.app = app;
	}

	// ====================================
	// Getters/setters
	// ====================================

	public boolean isRunning() {
		return isRunning;
	}

	public boolean isPaused() {
		return isPaused;
	}

	/**
	 * Retrieves field midiSound. Creates a new instance of MidiSound if none
	 * exists.
	 */
	MidiSoundD getMidiSound() {
		if (midiSound == null) {
			try {
				midiSound = new MidiSoundD(app);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return midiSound;
	}

	/**
	 * Retrieves field functionSound. Creates a new instance of FunctionSound if
	 * none exists.
	 */
	private FunctionSoundD getFunctionSound() {
		if (functionSound == null) {
			try {
				functionSound = new FunctionSoundD();
			} catch (Exception e) {
				Log.error("Problem in getFunctionSound(): " + e.getMessage());
			}
		}
		return functionSound;
	}

	// ====================================
	// Sound playing methods
	// ====================================

	/**
	 * Plays a single note using the midi sequencer.
	 * 
	 * @param note
	 * @param duration
	 * @param instrument
	 * @param velocity
	 */
	@Override
	public void playSequenceNote(final int note, final double duration,
			final int instrument, final int velocity) {
		try {
			stopCurrentSound();
			currentSoundType = SOUNDTYPE_MIDI;
			getMidiSound().playSequenceNote(note, duration, instrument);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * decoder to play MP3s
	 */
	Decoder decoder;

	/**
	 * Plays an audio file with the .mid / .mp3
	 * 
	 * @param fileName
	 */
	@Override
	public void playFile(GeoElement geoElement, final String fileName) {

		SwingUtilities.invokeLater(() -> {

			try {
				if (fileName.startsWith("data:") || fileName.startsWith("#")
						|| !(fileName.endsWith(".midi")
						&& fileName.endsWith(".mid"))) {

					InputStream is;

					if (fileName.startsWith(StringUtil.mp3Marker)) {

						String mp3base64 = fileName
								.substring(StringUtil.mp3Marker.length());

						byte[] mp3 = Base64.decode(mp3base64);

						is = new ByteArrayInputStream(mp3);

					} else if (fileName.startsWith("#")) {
						// eg PlaySound["#12345"] to play from GeoGebraTube
						String id = fileName.substring(1);

						String url = app.getURLforID(id);

						// #5094
						is = new URL(url).openStream();

					} else if (fileName.startsWith("http:")
							|| fileName.startsWith("https:")) {

						is = new URL(fileName).openStream();

					} else {
						// assume local file
						// eg c:\
						// eg file://
						is = new FileInputStream(fileName);

					}

					if (decoder == null) {
						decoder = new Decoder();
					}

					Thread thread = new Thread(
							new PlayMP3Thread(decoder, fileName, is));
					thread.start();

					return;

				}

				// not .mp3, must be .mid
				stopCurrentSound();
				currentSoundType = SOUNDTYPE_MIDI;
				getMidiSound().playMidiFile(fileName);

			} catch (Exception e) {
				e.printStackTrace();
			}

		});

	}

	/**
	 * Plays a sequence of notes generated by the string noteSring using the
	 * midi sequencer.
	 * 
	 * @param noteString
	 * @param instrument
	 */
	@Override
	public void playSequenceFromString(String noteString, int instrument) {
		try {
			stopCurrentSound();
			currentSoundType = SOUNDTYPE_MIDI;
			getMidiSound().playSequenceFromJFugueString(noteString, instrument);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays a tone generated by the time-valued input function f(t) for t = min
	 * to t = max seconds. Also allows adjustment of the sampling rate and bit
	 * depth.
	 * 
	 * @param f
	 * @param min
	 * @param max
	 * @param sampleRate
	 * @param bitDepth
	 */
	@Override
	public void playFunction(final GeoFunction f, final double min,
			final double max, final int sampleRate, final int bitDepth) {
		try {
			stopCurrentSound();
			currentSoundType = SOUNDTYPE_FUNCTION;
			getFunctionSound().playFunction(f, min, max, sampleRate, bitDepth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays a tone generated by the time-valued input function f(t) for t = min
	 * to t = max seconds.
	 * 
	 * @param f
	 * @param min
	 * @param max
	 */
	@Override
	public void playFunction(final GeoFunction f, final double min,
			final double max) {
		try {
			stopCurrentSound();
			currentSoundType = SOUNDTYPE_FUNCTION;
			getFunctionSound().playFunction(f, min, max);
		} catch (Exception e) {
			Log.error("Problem in playFunction(): " + e.getMessage());
		}
	}

	// ====================================
	// Control methods
	// ====================================

	/**
	 * Stops all sound creation and closes all sound-related resources.
	 */
	public void stopCurrentSound() {
		if (midiSound != null) {
			midiSound.stop();
		}
		if (functionSound != null) {
			functionSound.pause(true);
		}
	}

	/**
	 * Pauses/resumes current sound.
	 * 
	 * @param doResume
	 *            : true = resume play, false = pause
	 */
	@Override
	public void pauseResumeSound(boolean doResume) {

		if (currentSoundType == SOUNDTYPE_MIDI && midiSound != null) {
			midiSound.pause(!doResume);
		}

		if (currentSoundType == SOUNDTYPE_FUNCTION && functionSound != null) {
			functionSound.pause(!doResume);
		}

		isPaused = !doResume;
	}

	@Override
	public void loadGeoAudio(GeoAudio geo) {
		// not implemented here.
	}

	@Override
	public int getDuration(GeoAudio geoAudio) {
		// not implemented here.
		return 0;
	}

	@Override
	public int getCurrentTime(GeoAudio geoAudio) {
		// not implemented here.
		return 0;
	}

	@Override
	public void checkURL(String url, AsyncOperation<Boolean> callback) {
		// not implemented here.
	}

	@Override
	public void play(GeoAudio geo) {
		playFile(geo, geo.getSrc());
	}

	@Override
	public void pause(GeoAudio geo) {
		// not implemented here.
	}

	@Override
	public boolean isPlaying(GeoAudio geo) {
		// not implemented here.
		return false;
	}

	@Override
	public void setCurrentTime(GeoAudio geoAudio, int pos) {
		// not implemented here.
	}
}
