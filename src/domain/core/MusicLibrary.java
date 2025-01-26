package domain.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.ArrayQListWithSelection;
import util.adts.QListWithSelection;
import util.observer.AbsSubject;
import util.observer.Subject;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A class that extends AbsSubject<SongLibraryEvent,
 * where objects represent music libraries, 
 * meaning that in each moment only a maximum of
 * one of the songs of a library can be selected.
 * This class implements QListWithSelection<ISong>, 
 * Subject<SongLibraryEvent> and PropertyChangeListener
 * 
 */
public class MusicLibrary extends AbsSubject<SongLibraryEvent> implements QListWithSelection<ISong>, Subject<SongLibraryEvent>, PropertyChangeListener {

	private ArrayQListWithSelection<ISong> library;
	private boolean isPlaying;
	private Player player;
	
	/**
	 * Constructor of a library
	 */
	public MusicLibrary() {
		super();
		this.library = new ArrayQListWithSelection<>();
		this.isPlaying = false;
		this.player = PlayerFactory.INSTANCE.getPlayer();
		player.addListener(this);
	}
	
	/**
	 * Returns the size of the library
	 * 
	 * @return size of the library
	 */
	@Override
	public int size() {
		return this.library.size();
	}
	
	/**
	 * Returns the song in the given index
	 */
	@Override
	public ISong get(int i) {
		return this.library.get(i);
	}
	
	/**
	 * Adds an element at the end of the library and makes
	 * it selected
	 * 
	 * @ensures someSelected() == True
	 */
	@Override
	public void add(ISong e) {
		SongAddedLibraryEvent added = new SongAddedLibraryEvent(e, this);
		emitEvent(added);
		this.library.add(e);
	}
	
	/**
	 * Returns an iterator for the library
	 * 
	 * @return an iterator for the library
	 */
	@Override
	public Iterator<ISong> iterator() {
		return this.library.iterator();
	}
	
	/**
	 * Selects the song in index i of the
	 * library
	 * 
	 * @param i the given index of the library
	 * @requires 0 ≤ i < size()
	 */
	@Override
	public void select(int i) {
		this.library.select(i);
	}
	
	/**
	 * Indicates if there is a selected song
	 * 
	 * @return True if there is a selected song, False otherwise
	 */
	@Override
	public boolean someSelected() {
		return this.library.someSelected();
	}
	
	/**
	 * Returns the index of the selected song
	 * 
	 * @requires someSelected() == True
	 * @return the index of the selected song
	 */
	@Override
	public int getIndexSelected() {
		return this.library.getIndexSelected();
	}
	
	/**
	 * If getIndexSelected() < size() - 1 it selects the next song 
	 * of the list, otherwise the list stops having a selected song
	 * 
	 * @requires someSelected() == True
	 */
	@Override
	public void next() {
		this.library.next();
	}
	
	/**
	 * If getIndexSelected() > 0 it selects the previous song of the
	 * library, otherwise the library stops having a selected song
	 * 
	 * @requires someSelected() == True
	 */
	@Override
	public void previous() {
		this.library.previous();
	}
	
	/**
	 * If someSelected() == True it removes the selected song of the
	 * library, otherwise nothing happens
	 */
	@Override
	public void remove() {
		int beforeSize = library.size();
		SongRemovedLibraryEvent removed = new SongRemovedLibraryEvent(getSelected(), this);
		this.library.remove();
		
		if(library.size() < beforeSize) //Check if a song has been removed
			emitEvent(removed);
			
	}
	
	/**
	 * Returns the selected song
	 * 
	 * @requires someSelected() == True
	 * @return the selected song
	 */
	@Override
	public ISong getSelected() {
		return this.library.getSelected();
	}

	/**
     * This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(isPlaying()) {
			
			if(evt.getNewValue().equals(Player.PlayingState.ENDED)) {
				getSelected().incTimesPlayed();
			}
			
			else if(evt.getNewValue().equals(Player.PlayingState.STOPED))
				stop();
		}
	}
	
	/**
	 * If someSelected() == True determines the interruption of 
	 * the song that is playing (if that is the case) and starts
	 * playing the selected song
	 * 
	 * @requires someSelected() == True
	 */
	public void play() {
		player.load(getSelected().getFilename());
		player.play();
		this.isPlaying = true;
	}
	
	/**
	 * Checks if a song is playing which has been played through
	 * the library
	 * 
	 * @return whether a song is playing which has been played through the library
	 */
	public boolean isPlaying() {
		return this.isPlaying;
	}
	
	/**
	 * If isPlaying() == True stops the song that was playing
	 * 
	 * @requires isPlaying() == True
	 */
	public void stop() {
		this.isPlaying = false;
	}
	
	/**
	 * Increments the selected song's rating
	 * 
	 * @ensures getRating().equals(\old(getRating().inc())
	 */
	public void incRateSelected() {
		this.library.getSelected().incRating();
		SongRatedLibraryEvent incRating = new SongRatedLibraryEvent(getSelected(), this);
		emitEvent(incRating);
	}
	
	/**
	 * Decrements the selected song's rating
	 * 
	 * @ensures getRating().equals(\old(getRating().dec())
	 */
	public void decRateSelected() {
		this.library.getSelected().decRating();
		SongRatedLibraryEvent decRating = new SongRatedLibraryEvent(getSelected(), this);
		emitEvent(decRating);
	}
	
	/**
	 * Returns a iterable structure with the songs of the library
	 * that match with the given regular expression
	 * 
	 * @param reexp the regular expression to be used
	 * @return a iterable structure with the songs of the library that match with the given regular expression
	 */
	public Iterable<ISong> getMatches(String reexp) {

		List<ISong> matches = new ArrayList<>();
		
		for(ISong song : this.library) {
			if(song.matches(reexp))
				matches.add(song);
		}
		
		return matches;
	}
	
	/**
	 * Returns a iterable structure with the songs of the library
	 * in the library's own order
	 * 
	 * @return a iterable structure with the songs of the library in the library's own order
	 */
	public Iterable<ISong> getSongs() {

		List<ISong> songs = new ArrayList<>();
		
		for(ISong song : this.library)
			songs.add(song);
		
		return songs;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		for(ISong song : library) {
			if(i == getIndexSelected())
				sb.append("-> ");
			sb.append(i);
			sb.append(" ");
			sb.append(song.toString());
			sb.append("\n");
			i++;
		}
		
		return sb.toString();
	}

}
