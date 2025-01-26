package domain.core;
import domain.facade.ISong;
/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A concrete class of SongLibraryEvent, which represents
 * a concrete event about a song being added in a library
 * 
 */
public class SongAddedLibraryEvent extends SongLibraryEvent {
	
	 public SongAddedLibraryEvent(ISong song, MusicLibrary library) {
	        super(song, library);
	    }
}
