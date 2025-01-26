package domain.core;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class SongTest {
	
	private final SongMetaInfo metainfo = new SongMetaInfo("Acordar", new ArrayList<String>(Arrays.asList("Vicentuna","TunaSabes")), "Rock", "Tunagem");
	
	@Test public void timesPlayed() {
		
		Song song = new Song(metainfo, "acordar.mp3");
		
		int times = 3;
		for(int i = 0; i < times; i++)
			song.incTimesPlayed();
		
		assertTrue(song.getTimesPlayed() == times);
	}
	
	@Test public void incRating() {
		
		Song song = new Song(metainfo, "acordar.mp3");
		
		int toRate = 11;
		for(int i = 0; i < toRate; i++)
			song.incRating();
		
		assertTrue(toRate >= Rate.MAX_RATING ? song.getRating().equals(new Rate(10)) : song.getRating().equals(new Rate(toRate)));
	}
	
	@Test public void decRating() {
		
		Song song = new Song(metainfo, "acordar.mp3");
		
		//increment first to then be able to decrement
		int incToRate = 10;
		for(int i = 0; i < incToRate; i++)
			song.incRating();
		
		int decTimes = 11;
		for(int i = 0; i < decTimes; i++)
			song.decRating();
		
		assertTrue(song.getRating().equals(new Rate(0)));
	}
	
	@Test public void rating() {
		
		Song song = new Song(metainfo, "acordar.mp3");
		
		int incTimes1 = 5;
		for(int i = 0; i < incTimes1; i++)
			song.incRating();
		
		int decTimes1 = 3;
		for(int i = 0; i < decTimes1; i++)
			song.decRating();
		
		int incTimes2 = 9;
		for(int i = 0; i < incTimes2; i++)
			song.incRating();
		
		int decTimes2 = 1;
		for(int i = 0; i < decTimes2; i++)
			song.decRating();
		
		assertTrue(song.getRating().equals(new Rate(9)));
	}
	
	 @Test public void testGetTitle() {
		 
		 String title = "Acordar";

		 SongMetaInfo metainfo = new SongMetaInfo(title, new ArrayList<>(Arrays.asList("Vicentuna","TunaSabes")), "Rock", "Tunagem");
		 Song song = new Song(metainfo, "acordar.mp3");
		 
		 assertTrue(song.getSongTitle().equals(title));
	 }
	 
	 @Test public void testGetArtists() {
		 
		 ArrayList<String> artists = new ArrayList<>(Arrays.asList("Vicentuna","TunaSabes"));

		 SongMetaInfo metainfo = new SongMetaInfo("Acordar", artists, "Rock", "Tunagem");
		 Song song = new Song(metainfo, "acordar.mp3");
		 
		 assertTrue(song.getArtists().equals(artists));
	 }
	 
	 @Test public void testGetGenre() {
		 
		 String genre = "Rock";

		 SongMetaInfo metainfo = new SongMetaInfo("Acordar", new ArrayList<>(Arrays.asList("Vicentuna","TunaSabes")), genre, "Tunagem");
		 Song song = new Song(metainfo, "acordar.mp3");
		 
		 assertTrue(song.getGenre().equals(genre));
	 }
	 
	 @Test public void testGetAlbum() {
		 
		 String album = "Tunagem";

		 SongMetaInfo metainfo = new SongMetaInfo("Acordar", new ArrayList<>(Arrays.asList("Vicentuna","TunaSabes")), "Rock", album);
		 Song song = new Song(metainfo, "acordar.mp3");
		 
		 assertTrue(song.getAlbum().equals(album));
	 }
	 
	 @Test public void testGetFilename() {
		 
		 String filename = "acordar.mp3";
		 
		 SongMetaInfo metainfo = new SongMetaInfo("Acordar", new ArrayList<>(Arrays.asList("Vicentuna","TunaSabes")), "Rock", "Tunagem");
		 Song song = new Song(metainfo, filename);
		 
		 assertTrue(song.getFilename().equals(filename));
	 }
	 
	 @Test public void testMatches() {
		 
		 Song song = new Song(metainfo, "acordar.mp3");
		 
		 boolean matchesTitle = song.matches(".*Ac.*");
		 boolean matchesArtists = song.matches(".*Vicen.*") && song.matches(".*Sabes.*");
		 boolean matchesGenre = song.matches(".*Roc.*");
		 boolean matchesAlbum = song.matches(".*agem.*");
		 boolean matchesRandom = song.matches(".*,.*") || song.matches(".*\\[.*") || song.matches(".*-.*");
		
		 assertTrue(matchesTitle && matchesArtists && matchesGenre && matchesAlbum && !matchesRandom);
	 }
}
