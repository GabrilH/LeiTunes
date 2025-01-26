package domain.playlists;

import domain.core.MusicLibrary;

/**
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 * 
 * A class that extends AbsPlaylist, where its objects represent a playlist,
 * inside of them there are songs that were manually placed there
 * These playlists allow the removal and shifting of the song's positions
 *
 */
public class ManualPlaylist extends AbsPlaylist {

	/**
	 * Constructor of a manual playlist
	 * 
	 * @param name the name of the playlist
	 * @param library the library that the playlist will use
	 */
	public ManualPlaylist(String name, MusicLibrary library) {
		super(name, library);
				
	}
	
}
