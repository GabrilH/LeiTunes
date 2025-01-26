package domain.facade;

import java.io.IOException;
import java.util.Optional;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import domain.core.IMetaInfoExtractor;
import domain.core.MP3agicMetaInfoExtractor;
import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongMetaInfo;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 * 
 * A class whose objects represent a controller of interactions
 * with MusicLibrary type objects
 * 
 */
public class MusicLibraryController {
	
	private MusicLibrary library;
	
	/**
	 * Constructor of the object, receives the library to be controlled
	 * @param library the library to be controlled
	 */
	public MusicLibraryController(MusicLibrary library) {
		this.library = library;
	}
	
	/**
	 * Returns the number of songs on the library
	 * @return the number of songs on the library
	 */
	public int numberOfSongs() {
		return this.library.size();
	}
	
	/**
	 * Adds a song to the controlled library by its given mp3 file name
	 * and obtains its metadata
	 * 
	 * @param filename the mp3 file name of the song to be added
	 * @requires filename to be a valid mp3 file
	 */
	public void addSong(String filename) {
			
		IMetaInfoExtractor extractor = new MP3agicMetaInfoExtractor(filename);
		SongMetaInfo metainfo = new SongMetaInfo(extractor.getTitle(), extractor.getArtists(), extractor.getGenre(), extractor.getAlbum());
		library.add(new Song(metainfo, filename));
		
	}

	/**
	 * If 0≤i<numberOfSongs() selects the song in index i of the library,
	 * otherwise does nothing
	 * 
	 * @param i the given index of the library
	 */
	public void selectSong(int i) {
		if(0 <= i && i < numberOfSongs())
			this.library.select(i);
	}
	
	/**
	 * Returns the selected song of the library, if it exists
	 * @return the selected song of the library
	 */
	public Optional<ISong> getSelectedSong() {

		if(library.someSelected())
			return Optional.of(library.getSelected());
		
		return Optional.empty();
	}
	
	/**
	 * Deletes the selected song of the library, if it exists
	 */
	public void removeSelectedSong() {	
		library.remove();
	}

	/**
	 * If there's a selected song, it stops the currently playing song
	 * (if there's one playing) and starts playing the selected song,
	 * otherwise it does nothing. When the song reaches the end, it
	 * increases the number of times played.
	 */
	public void play() {
		
		if(library.someSelected()) {			
			stop();		
			library.play();
		}
	}
	
	/**
	 * If isPlaying() == True stops the song that was playing
	 */
	public void stop() {
		if(library.isPlaying())
			library.stop();
	}
	
	/**
	 * Increments the selected song's rating, if it exists
	 */
	public void incRateSelected() {
		if(library.someSelected())
			library.incRateSelected();
	}
	
	/**
	 * Decrements the selected song's rating, if it exists
	 */
	public void decRateSelected() {
		if(library.someSelected())
			library.decRateSelected();
	}

	/**
	 * Returns a iterable structure with the songs of the library
	 * that match with the given regular expression
	 * 
	 * @param reexp the regular expression to be used
	 * @return a iterable structure with the songs of the library that match with the given regular expression
	 */
	public Iterable<ISong> getMatches(String reexp) {
		return this.library.getMatches(reexp);
	}
	
	/**
	 * Returns an iterable structure with the songs of the library
	 * @return an iterable structure with the songs of the library
	 */
	public Iterable<ISong> getSongs() {
		return this.library.getSongs();
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("*****MUSIC LIBRARY****\n");
		sb.append(library.toString());
		
		return sb.toString();
	}

}
