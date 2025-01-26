package domain.core;

import java.util.List;

import domain.facade.ISong;
import util.adts.RegExpMatchable;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 * 
 * A class whose objects represent a song, which has its file's name,
 * metadata information, numbers of times played and classification
 * 
 */
public class Song implements ISong, RegExpMatchable{
	
	private String fileName;
	private SongMetaInfo metaInfo;
	private int playedCounter;
	private Rate rate;
	
	/**
	 * Constructor of the object, receives its metadata
	 * and file name, and sets the played times and 
	 * classification to 0
	 * 
	 * @param info the metadata of the song
	 * @param filename the song's file name
	 */
	public Song(SongMetaInfo info, String filename) {
		this.metaInfo = info;
		this.fileName = filename;
		this.playedCounter = 0;
		this.rate = new Rate(0);
	}
	
	/**
	 * Increments the number of times the song was played
	 */
	@Override
	public void incTimesPlayed() {
		this.playedCounter++;
	}
	
	/**
	 * Returns the number of times the song was played
	 * 
	 * @return number of times the song was played
	 */
	@Override
	public int getTimesPlayed() {
		return this.playedCounter;
	}
	
	/**
	 * Returns the rating of the song
	 * 
	 * @return the song's rating
	 * @ensures \result != null
	 */
	@Override
	public Rate getRating() {
		return this.rate;
	}
	
	/**
	 * Increments the song's rating
	 * @ensures getRating().equals(\old(getRating().inc())
	 */
	@Override
	public void incRating() {
		this.rate = rate.incRating();
	}
	
	/**
	 * Decrements the song's rating
	 * @ensures getRating().equals(\old(getRating().dec())
	 */
	@Override
	public void decRating() {
		this.rate = rate.decRating();
	}
	
	/**
	 * Returns the title of the song
	 * 
	 * @return the song's title
	 * @ensures \result != null
	 */
	@Override
	public String getSongTitle() {
		return this.metaInfo.getName();
	}
	
	/**
	 * Returns the genre of the song
	 * 
	 * @return the song's genre
	 * @ensures \result != null
	 */
	@Override
	public String getGenre() {
		return this.metaInfo.getGenre();
	}
	
	/**
	 * Returns the artist list of the song
	 * 
	 * @return the song's artists list
	 * @ensures \result != null
	 */
	@Override
	public List<String> getArtists() {
		return this.metaInfo.getArtists();
	}
	
	/**
	 * Returns the album name of the song
	 * 
	 * @return the song's album name
	 * @ensures \result != null
	 */
	@Override
	public String getAlbum() {
		return this.metaInfo.getAlbum();
	}
	
	/**
	 * Return the filename  of the song
	 * 
	 * @return the song's filename
	 * @ensures \result != null 
	 */
	@Override
	public String getFilename() {
		return this.fileName;
	}
	
	/**
	 * Checks if any song data matches the given regular expression
	 *  
	 * @param regexp the regular expression to be used
	 * @requires regexp != null
	 * @return whether some data of the song matches with the given regexp
	 */
	@Override
	public boolean matches(String regexp) {
		return metaInfo.matches(regexp);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((metaInfo == null) ? 0 : metaInfo.hashCode());
		result = prime * result + playedCounter;
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (metaInfo == null) {
			if (other.metaInfo != null)
				return false;
		} else if (!metaInfo.equals(other.metaInfo))
			return false;
		if (playedCounter != other.playedCounter)
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}

	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(metaInfo.toString());
		sb.append(" --- ");
		sb.append(getRating());
		sb.append(" -- ");
		sb.append(getTimesPlayed());
		
		return sb.toString();
	}
}
