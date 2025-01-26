package domain.facade;

import domain.core.MusicLibrary;
import domain.playlists.PlaylistList;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A class whose objects contain a MusicLibrary and a PlaylistList and
 * provides the controllers to each object
 */
public class LEITunes {
	
	private MusicLibrary library;
	private PlaylistList playlists;
	private PlaylistListController plController;
	private MusicLibraryController msController;
	
	/**
	 * Constructor of the object
	 */
	public LEITunes() {
		this.library = new MusicLibrary();
		this.playlists = new PlaylistList(library);
		this.plController = new PlaylistListController(playlists, library);
		this.msController = new MusicLibraryController(library);
	}

	/**
	 * Returns the controller to the PlaylistList
	 * @return the controller to the PlaylistList
	 */
	public PlaylistListController getPlaylistController() {
		return this.plController;
	}

	/**
	 * Returns the controller to the MusicLibrary
	 * @return the controller to the MusicLibrary
	 */
	public MusicLibraryController getMusicLibraryController() {
		return this.msController;
	}
}
