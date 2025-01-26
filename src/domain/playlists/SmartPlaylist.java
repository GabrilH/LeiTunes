package domain.playlists;

import domain.core.MusicLibrary;
import domain.facade.ISong;

/**
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 * 
 * A concrete class of AbsPlaylist that does not allow the removal
 * or shifting of the songs positions
 *
 */
public abstract class SmartPlaylist extends AbsPlaylist{
	
	protected static final int N = 5;
	
	/**
	 * The constructor of a smart playlist
	 * 
	 * @param name the name of the playlist
	 * @param library the library that the playlist will use
	 */
	protected SmartPlaylist(String name, MusicLibrary library) {
		super(name, library);
	}
	
	/**
	 * Used for the automatic inserts that are made on a 
	 * smart playlist
	 * 
	 * @param song the song that will be added
	 */
	protected void addAutomatic(ISong song) {
		super.add(song);
	}
	
	/**
	 * Used for the automatic removals that are made on a
	 * smart playlist
	 * 
	 * @param index the index of the song that will be removed
	 */
	protected void removeAutomatic(int index) {
		super.select(index);
		super.remove();
	}
	
	/**
	 * Doesn't allow to add songs manually to the playlist
	 */
	@Override
	public boolean add(ISong song) {
		return false;
	}
	
	/**
	 * Doesn't allow to remove songs manually from the playlist
	 */
	@Override
	public boolean remove() {
		return false;
	}
	
}
