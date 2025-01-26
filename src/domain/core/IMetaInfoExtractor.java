package domain.core;

import java.util.List;

/**
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * An interface that offers methods to extract certain metadata information
 * from music files
 */
public interface IMetaInfoExtractor {

	/**
	 * Returns the title of the song
	 * @return the title of the song
	 */
	String getTitle();
	
	/**
	 * Returns the list of artists of the song
	 * @return the list of artists of the song
	 */
	List<String> getArtists();
	
	/**
	 * Returns the genre description of the song
	 * @return the genre description of the song
	 */
	String getGenre();
	
	/**
	 * Returns the album name of the song
	 * @return the album name of the song
	 */
	String getAlbum();
	
}
