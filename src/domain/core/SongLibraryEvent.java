package domain.core;

import domain.facade.ISong;
import util.observer.Event;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A concrete class of SongLibraryEvent, which represents
 * a generic event about a songs in a library
 * 
 */
public abstract class SongLibraryEvent implements Event{

	private ISong song;
	private MusicLibrary library;

	protected SongLibraryEvent(ISong song, MusicLibrary library) {
		this.song = song;
		this.library = library;
	}

	/**
     * Returns the song on which the event happened
     * @return the song on which the event happened
     */
	public ISong getSong() {
		return this.song;
	}

	/**
	 * Returns the library on which the event happened
	 * @return the library on which the event happened
	 */
	public MusicLibrary getLibrary() {
		return this.library;
	}
}
