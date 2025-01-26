package domain.core;

import java.util.List;

import util.adts.RegExpMatchable;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 * 
 * A record whose objects represent information about a music, more specifically
 * its name, the list of artists, genre and album name
 * 
 */
public record SongMetaInfo(String name, List<String> artists, String genre, String album) implements RegExpMatchable {

	/**
	 * Returns the name of the song
	 * @return the name of the song
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the list of artists of the song
	 * @return the list of artists of the song
	 */
	public List<String> getArtists() {
		return this.artists;
	}
	
	/**
	 * Returns the genre of the song
	 * @return the genre of the song
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * Returns the album name of the song
	 * @return the album name of the song
	 */
	public String getAlbum() {
		return this.album;
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
		
		if(name.matches(regexp) || genre.matches(regexp) || album.matches(regexp))
			return true;
		
		for(String artist : artists)
			if(artist.matches(regexp))
				return true;
		
		return false;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((artists == null) ? 0 : artists.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SongMetaInfo other = (SongMetaInfo) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (artists == null) {
			if (other.artists != null)
				return false;
		} else if (!artists.equals(other.artists))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		sb.append(getName());
		sb.append(", ");
		sb.append(getAlbum());
		sb.append(", ");
		sb.append(getGenre());
		sb.append(", ");
		sb.append(getArtists());
		sb.append("]");
		
		return sb.toString();
	}

}
