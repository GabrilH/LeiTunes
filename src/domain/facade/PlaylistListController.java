package domain.facade;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.playlists.ManualPlaylist;
import domain.playlists.Playlist;
import domain.playlists.PlaylistList;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 * 
 * A class whose objects represent a controller of interactions
 * with PlaylistList type objects
 *
 */
public class PlaylistListController {
	
	private PlaylistList playlists;
	private MusicLibrary library;

	/**
	 * Constructor of the object, it receives the playlist list to be controlled,
	 * and the library of songs
	 * 
	 * @param playlists the playlist list to be controlled
	 * @param library the library of songs
	 */
	public PlaylistListController(PlaylistList playlists, MusicLibrary library) {
		this.playlists = playlists;
		this.library = library;
	}
	
	/**
	 * Inserts a new ManualPlaylist object to the list of Playlists controlled,
	 * the newly added Playlist becomes the selected one
	 * 
	 * @param name the name of the Playlist to be added
	 */
	public void createPlaylist(String name) {
		this.playlists.add(new ManualPlaylist(name, library));
	}
	
	/**
	 * If 0 ≤ i < size(), it selects the Playlist in the i index,
	 * otherwise it does nothing
	 * 
	 * @param i the index of the Playlist to be selected
	 */
	public void selectPlaylist(int i) {
		
		if(0 <= i && i < this.playlists.size())
			this.playlists.select(i);
	}
	
	/**
	 * Checks if there's a selected Playlist
	 * @return True if there's a selected Playlist, False otherwise
	 */
	public boolean somePlaylistSelected() {
		return this.playlists.someSelected();
	}
	
	/**
	 * Returns the selected Playlist
	 * @return the selected Playlist
	 * @requires somePlaylistSelected() == True
	 */
	public Playlist getSelectedPlaylist() {
		return this.playlists.getSelected();
	}
	
	/**
	 * Removes the selected Playlist, if it doesn't exist it does nothing
	 */
	public void removePlaylist() {
		this.playlists.remove();
	}
	
	/**
	 * Returns an iterator with the Playlists in the controlled list
	 * @return an iterator with the Playlists in the controlled list
	 */
	public Iterator<Playlist> iterator() {
		return this.playlists.iterator();
	}
	
	/**
	 * Returns the number of songs in the selected Playlist
	 * 
	 * @return the number of songs in the selected Playlist
	 * @requires somePlaylistSelected() == True
	 */
	public int numberOfSongs() {
		return this.playlists.getSelected().size();
	}
	
	/**
	 * If there's a selected song in the library it inserts it in the
	 * selected Playlist and it becomes selected, otherwise it does nothing
	 * 
	 * @requires somePlaylistSelected() == True
	 */
	public void addSong() {
		
		if(this.library.someSelected())
			this.playlists.getSelected().add(this.library.getSelected());
	}
	
	/**
	 * Selects the song in the given index of the selected Playlist
	 * 
	 * @param i the index of the song to be selected
	 * @requires somePlaylistSelected() == True
	 */
	public void selectSong(int i) {
		if(0 <= i && i < numberOfSongs())
			this.playlists.getSelected().select(i);
	}
	
	/**
	 * If somePlaylistSelected(), it checks getSelectedPlayList().someSelected(),
	 * otherwise it returns false
	 * 
	 * @return If somePlaylistSelected(), returns getSelectedPlayList().someSelected(),
	 * otherwise false
	 */
	public boolean someSongSelected() {
		
		if(somePlaylistSelected())
			return getSelectedPlaylist().someSelected();

		return false;
	}
	
	/**
	 * Deletes the selected song in the selected Playlist
	 * @requires somePlaylistSelected() == True
	 */
	public void removeSelectedSong() {
		this.playlists.getSelected().remove();
	}
	
	/**
	 * Asks the selected playlist to select the next song
	 * @requires somePlaylistSelected() == True
	 */
	public void nextSong() {
		this.playlists.getSelected().next();
	}
	
	/**
	 * Asks the selected playlist to select the previous song
	 * @requires somePlaylistSelected() == True
	 */
	public void previousSong() {
		this.playlists.getSelected().previous();
	}

	/**
	 * If someSongSelected(), it stops the currently playing song (if there's
	 * one playing) and starts to play, in order, all the songs of the selected
	 * Playlist starting from the currently selected song, increasing the number
	 * of times played of each song that reaches the end. Otherwise it does nothing.
	 */
	public void play() {
		
		if(someSongSelected()) {
			this.playlists.stop();	
			this.playlists.getSelected().play();
		}	
	}
	
	/**
	 * If there's a song currently playing, it stops playing it, otherwise it does nothing
	 */
	public void stop() {
		if(this.playlists.isPlaying())
			this.playlists.stop();
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("***** PLAYLISTS *****");
		sb.append(playlists.toString());
		
		return sb.toString();
	}
}
