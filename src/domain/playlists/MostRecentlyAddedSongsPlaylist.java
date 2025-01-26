package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.facade.ISong;

/**
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A concrete class of SmartPlaylist() where objects represent a playlist
 * where the N songs that were recently played of a determined library are 
 * (where N represents a constant)
 */
public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist{
	
	/**
	 * Constructor of a most recent added songs playlist
	 * 
	 * @param library the library that the playlist will use
	 */
	protected MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
		super("Most Recently Added", library);
	}
	
	/**
	 * Used for the automatic inserts that are made on a 
	 * smart playlist
	 * 
	 * @param song the song that will be added
	 */
	@Override
	public void addAutomatic(ISong song) {
		super.addAutomatic(song);
		
		if(super.size() > N)
			super.removeAutomatic(0);
	}
	
	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this playlist (can affect the content of the playlist)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {
		super.processEvent(e);
		if(e instanceof SongAddedLibraryEvent) {
			addAutomatic(e.getSong());
		}
	}
	
}
