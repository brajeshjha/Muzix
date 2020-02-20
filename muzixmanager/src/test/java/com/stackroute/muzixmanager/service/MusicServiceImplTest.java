package com.stackroute.muzixmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.muzixmanager.domain.Music;
import com.stackroute.muzixmanager.exception.MusicTrackAlreadyExistsException;
import com.stackroute.muzixmanager.exception.MusicTrackNotFoundException;
import com.stackroute.muzixmanager.repository.MusicRepository;

public class MusicServiceImplTest {

	@Mock
	private MusicRepository musicRepository;

	@InjectMocks
	private MusicServiceImpl musicServiceImpl;

	Optional<Music> options;
	
	private Music music;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		music = new Music(1,"First Class","www.abcd.com","Arijit","www.abcd.com","jhabrajesh");
		options = Optional.of(music);
	}

	@Test
	public void testMockCreation() {
		assertNotNull("JpaRepository creation failed: use @InjectMocks on muzixServiceImpl", music);
	}

	@Test
	public void testSaveMusicSuccess() throws MusicTrackAlreadyExistsException {
		when(musicRepository.save(music)).thenReturn(music);
		final boolean flag = musicServiceImpl.saveMusic(music);
		assertTrue("saving music passed", flag);
		verify(musicRepository, times(1)).save(music);
	}

	@Test(expected = MusicTrackAlreadyExistsException.class)
	public void testSaveMusicFailure() throws MusicTrackAlreadyExistsException {
		when(musicRepository.findById(1)).thenReturn(options);
		when(musicRepository.save(music)).thenReturn(music);
		final boolean flag = musicServiceImpl.saveMusic(music);
	}

	@Test
	public void testDeleteMusicById() throws MusicTrackNotFoundException {
		when(musicRepository.findById(1)).thenReturn(options);
		doNothing().when(musicRepository).delete(music);
		final boolean flag = musicServiceImpl.deleteMusicById(1);
		assertTrue("deleting movie passed", flag);
		verify(musicRepository, times(1)).delete(music);
		verify(musicRepository, times(1)).findById(music.getId());
	}

	@Test(expected = MusicTrackNotFoundException.class)
	public void testDeleteMusicByIdFailure() throws MusicTrackNotFoundException {
		doNothing().when(musicRepository).delete(music);
		final boolean flag = musicServiceImpl.deleteMusicById(1);
	}

	@Test
	public void testGetAllMusics() {
		final List<Music> musics = new ArrayList<>();
		musics.add(music);
		when(musicRepository.findByUserId("100")).thenReturn(musics);
		final List<Music> musics1 = musicServiceImpl.getMyMusics("100");
		assertEquals(musics, musics1);
		verify(musicRepository, times(1)).findByUserId(Mockito.anyString());
	}

}
