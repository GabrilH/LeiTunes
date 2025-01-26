package domain.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 * 
 * A concrete class of IMetaInfoExtractor that implements it using the MP3agic class
 *
 */
public class MP3agicMetaInfoExtractor implements IMetaInfoExtractor {

	private ID3v2 id3v2Tag = null;
	private ID3v1 id3v1Tag = null;
	
	/**
	 * The constructor of the object, it receives the mp3 file of the song
	 * @param mp3file the mp3 file of the song
	 */
	public MP3agicMetaInfoExtractor(String fileName) {
		
		Mp3File mp3file;
		try {
			mp3file = new Mp3File(fileName);
			
			if(mp3file.hasId3v2Tag())
				this.id3v2Tag = mp3file.getId3v2Tag();

			else if(mp3file.hasId3v1Tag())
				this.id3v1Tag = mp3file.getId3v1Tag();
			
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			System.err.println("Invalid mp3 filename");
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the title of the song
	 * @return the title of the song
	 */
	@Override
	public String getTitle() {
		return nullToUnknown(id3v2Tag != null ? id3v2Tag.getTitle() : id3v1Tag.getTitle());
	}

	/**
	 * Returns the list of artists of the song
	 * @return the list of artists of the song
	 */
	@Override
	public List<String> getArtists() {
		return Arrays.asList(nullToUnknown(id3v2Tag != null ? id3v2Tag.getArtist() : id3v1Tag.getArtist()).split("; "));
	}

	/**
	 * Returns the genre description of the song
	 * @return the genre description of the song
	 */
	@Override
	public String getGenre() {
		return nullToUnknown(id3v2Tag != null ? id3v2Tag.getGenreDescription() : id3v1Tag.getGenreDescription());
	}

	/**
	 * Returns the album name of the song
	 * @return the album name of the song
	 */
	@Override
	public String getAlbum() {
		return nullToUnknown(id3v2Tag != null ? id3v2Tag.getAlbum() : id3v1Tag.getAlbum());
	}
	
	/**
	 * Auxiliary method used to replace "null" Strings with "unknown"
	 * 
	 * @param str the string to be checked
	 * @return If str is "null" it returns "unknown", otherwise returns str
	 */
	private String nullToUnknown(String str) {
		return str == null ? "unknown" : str;
	}

}
