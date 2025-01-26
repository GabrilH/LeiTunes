package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;
import domain.facade.ISong;

/**
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A concrete class of SmartPlaylist() where objects represent a smart playlist
 * where the N songs with the highest rating of a determined library are 
 * (where N represents a constant)
 */
public class MostLikedSongsPlaylist extends SmartPlaylist{
	
	/**
	 * The constructor of a most liked songs playlist
	 * 
	 * @param library the library that the playlist will use
	 */
	public MostLikedSongsPlaylist(MusicLibrary library) {
		super("Most Liked", library);
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
		int addedIndex = super.getIndexSelected();
		
		int size = super.size();
		
		for(int i = 0; i < size - 1; i++) {
			
			super.select(i);
			
			//sort
			if(song.getRating().isGreaterThan(super.getSelected().getRating())) {
				super.select(addedIndex);
				super.moveUpSelected(i);				
				break;
			}	
		}
		
		if(size > N)
			removeAutomatic(N);
	}
	
	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this playlist (can affect the content of the playlist)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {
		super.processEvent(e);
		if(e instanceof SongRatedLibraryEvent) {
			addAutomatic(e.getSong());
		}
	}
}
