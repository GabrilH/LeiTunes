package domain.playlists;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.ArrayQListWithSelection;

/**
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * An abstract class that implements Playlist
 *
 * Playlists have a name and consist of a list of distinct musics from a music library.
 * 
 * Removing a song from the library leads to automatic removal of the song from the 
 * playlist, if it is there.
 * 
 * Addition/removal of songs may be completely automatic and, hence, manual addition/removal 
 * might not be possible. The same applies to changes of order through moveUpSelected method.
 * 
 * At any time, one song in the playlist may be selected and one song of the 
 * playlist may be playing (i.e. the play action was performed via the playlist). 
 * A playlist keeps track that a song was played (until the end) through the playlist.
 * 
 */
public abstract class AbsPlaylist implements Playlist{

	protected MusicLibrary library;
	private String name;
	private ArrayQListWithSelection<ISong> songList;
	private boolean isPlaying;
	private Player player;

	/**
	 * Constructor of an abstract playlist
	 * 
	 * @param name the name of the playlist
	 * @param library the library 
	 */
	protected AbsPlaylist(String name, MusicLibrary library) {
		this.library = library;
		this.name = name;
		this.songList = new ArrayQListWithSelection<>();
		this.isPlaying = false;
		this.player = PlayerFactory.INSTANCE.getPlayer();
		player.addListener(this);
		library.registerListener(this);
	}
	
	/**
	 * Returns an iterator for the playlist
	 * 
	 * @return an iterator for the playlist
	 */
	@Override
	public Iterator<ISong> iterator() {
		return songList.iterator();
	}

	 /**
     * Returns the number of elements in the playlist.
     * 
     * @return the number of elements in the playlist
     */
	@Override
	public int size() {
		return songList.size();
	}

	/**
	 * Returns the selected song
	 * 
	 * @requires someSelected() == True
	 * @return the selected song
	 */
	@Override
	public ISong getSelected() {
		return songList.getSelected();
	}

	/**
	 * Indicates if there is a selected song
	 * 
	 * @return True if there is a selected song, False otherwise
	 */
	@Override
	public boolean someSelected() {
		return songList.someSelected();
	}

	/**
	 * Adds a song at the end of the playlist and makes
	 * it selected
	 * 
	 * @ensures someSelected() == True
	 */
	@Override
	public boolean add(ISong song) {
		
		int songListSize = songList.size();
		
		for(int i = 0; i < songListSize; i++) {
			if(song.equals(songList.get(i))) {
				songList.select(i);
				return false;
			}
		}

		songList.add(song);
		
		return true;
	}

	/**
	 * If someSelected() == True it removes the selected song of the
	 * list, otherwise nothing happens
	 */
	@Override
	public boolean remove() {
		
		if(songList.someSelected()) {
			songList.remove();
			return true;
		}
		
		return false;
	}

	/**
	 * Selects the song in index i of the playlist
	 * 
	 * @param i
	 * @requires 0 ≤ i < size()
	 */
	@Override
	public void select(int i) {
		songList.select(i);
	}

	/**
	 * Moves the current selected song up to position i, 
	 * shifting down all elements in the playlist from 
	 * positions i+1 to \old getIndexSelected()-1, 
	 * if movement in the playlist is possible 
	 * 
	 * @param i the index where this element is going to be moved
	 * @requires someSelected() && 0 <= i < getIndexSelected()
	 * @ensures \return ==> someSelected() && 
	 * 					getIndexSelected() == i  && 
	 * 					size() == \old(size()) 
	 */
	@Override
	public boolean moveUpSelected(int i) {

		int indexFrom = songList.getIndexSelected();
		ArrayQListWithSelection<ISong> newSongList = new ArrayQListWithSelection<>();
		
		if(i != songList.getIndexSelected()) {
			if(indexFrom < i) {
				for(int j = 0; j < songList.size(); j++) {
		    		
		    		if(j >= indexFrom && j <= i) {
		    			if(j == i)
		    				newSongList.add(songList.get(indexFrom));
		    			else
		    				newSongList.add(songList.get(j+1));
		    		}
		    		else
		    			newSongList.add(songList.get(j));
		    	}
			}
			
			else if(indexFrom > i)
    			for(int j = 0; j < songList.size(); j++) {
	    		
		    		if(j >= i && j <= indexFrom) {
		    			if(j == i)
		    				newSongList.add(songList.get(indexFrom));
		    			else
		    				newSongList.add(songList.get(j-1));
		    		}
		    		else
		    			newSongList.add(songList.get(j));	
		    	}
			
			this.songList = newSongList;
			this.songList.select(i);
			return true;
		}
		else
			return false;
 
	}

	/**
	 * Returns the index of the selected song, if any	 
	 * 
	 * @return the index of the selected song, if any
	 * @requires someSelected()
	 * @ensures 0 <= \return < size()
	 */
	@Override
	public int getIndexSelected() {
		return songList.getIndexSelected();
	}

	/**
	 * If getIndexSelected() < size() - 1 it selects the next song 
	 * of the playlist, otherwise the playlist stops having a selected song
	 * 
	 * @requires someSelected() == True
	 */
	@Override
	public void next() {
		songList.next();
		
	}

	/**
	 * If getIndexSelected() > 0 it selects the previous song of the
	 * playlist, otherwise the playlist stops having a selected song
	 * 
	 * @requires someSelected() == True
	 */
	@Override
	public void previous() {
		songList.previous();
		
	}

	/**
	 * Returns the name of the playlist
	 * 
	 * @return the name of the playlist
	 * @ensures \result != null
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Returns if a song is playing and the play action has been performed via the playlist
	 * 
	 * @return true if a song is playing and the play action was done through the playlist,
	 *         false otherwise
	 */
	@Override
	public boolean isPlaying() {
		return this.isPlaying;
	}

	/**
	 * Plays the selected song
	 * 
	 * @requires someSelected()
	 * @ensures isPlaying()
	 */
	@Override
	public void play() {
		library.stop();
		player.load(getSelected().getFilename());
		player.play();
		this.isPlaying = true;
	}

	/**
	 * Stops the playing song
	 * 
	 * @requires isPlaying()
	 */
	@Override
	public void stop() {
		this.isPlaying = false;
	}

	/**
	 * Reaction to property change events, namely those emitted by the player
	 * (can affect the selected song and song being played)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(isPlaying()) {
			
			if(evt.getNewValue().equals(Player.PlayingState.ENDED)) {
				getSelected().incTimesPlayed();
				next();
				if(someSelected())
					play();
			}
			
			else if(evt.getNewValue().equals(Player.PlayingState.STOPED))
				stop();
		}
	}

	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this playlist (can affect the content of the playlist)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {
		if(e instanceof SongRemovedLibraryEvent) {
			ISong song = e.getSong();
			
			for(int i = 0; i < songList.size(); i++) {
				if(song.equals(songList.get(i))) {
					songList.select(i);
					songList.remove();
					break;
				}
			}
		}
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n");
		sb.append("*-- Playlist ");
		sb.append(getName());
		sb.append("--*\n");
		int i = 0;
		for(ISong song : songList) {
			sb.append(i);
			sb.append(" ");
			sb.append(song.toString());	
			sb.append("\n");
			i++;
		}
		
		return sb.toString();
	}

}
