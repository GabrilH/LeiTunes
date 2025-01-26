package domain.playlists;

import java.util.ArrayList;
import java.util.Iterator;

import domain.core.MusicLibrary;
import util.adts.AbsQListWithSelection;
import util.adts.QListWithSelection;

/**
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A concrete class of AbsQListWithSelection<Playlist>() that implements 
 * QListWithSelection<Playlist> where objects represent a list of playlists
 * of a MusicLibrary. In each moment there can be only one of these playlists 
 * selected
 */
public class PlaylistList extends AbsQListWithSelection<Playlist> implements QListWithSelection<Playlist> {

	private boolean isPlaying;
	
	/**
	 * The constructor of a list of playlists
	 * 
	 * @param library the library that the list will use
	 */
	public PlaylistList(MusicLibrary library) {
		super(new ArrayList<>());
		this.isPlaying = false;
		super.add(new MostLikedSongsPlaylist(library));
		super.add(new MostRecentlyAddedSongsPlaylist(library));
	}
	
	/**
	 * If someSelected() == true, if there is any selected song on the selected
	 * playlist, determines the interruption of the song that is playing 
	 * (if that is the case) and starts playing, by order, all the songs of
	 * the list from the selected song (or until is interrupted in any way)
	 * 
	 *  @requires someSelected() == true
	 *  
	 */
	public void play() {
		
		Playlist selectedPlaylist = super.getSelected();
		
		if(selectedPlaylist.someSelected()) {
			if(isPlaying())
				stop();
			
			super.getSelected().play();
			this.isPlaying = true;
		}
			
	}
	
	/**
	 * Checks if there is a song playing that was started playing in any of
	 * the list's playlists
	 * 
	 * @return if there is a song playing that was started playing in any of the list's playlists
	 */
	public boolean isPlaying() {
		return this.isPlaying;
	}
	
	/**
	 * If isPlaying() == true, stops the song that was playing
	 * 
	 * @requires isPlaying() == true
	 */
	public void stop() {
		this.isPlaying = false;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("\n");
		
		Iterator<Playlist> it = super.iterator();
		while(it.hasNext())
			sb.append(it.next());

		return sb.toString();
	}
}
