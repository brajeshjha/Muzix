package com.stackroute.muzixmanager.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.muzixmanager.domain.Music;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MusicRepositoryTest {
	

	@Autowired
	private  MusicRepository musicRepository;

	public void setMusicRepository(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}

	private Music music;
	
	
	@Test
	public void testSaveMusic() throws Exception {
		this.music=new Music(1,"First Class","www.abcd.com","Arijit","www.abcd.com","jhabrajesh");
		this.musicRepository.save(music);
		final Music fetchedMusic = this.musicRepository.findByName(music.getName());
		assertThat(fetchedMusic.getName()).isEqualTo("First Class");
	}
	
	@Test
	public void testGetMusic() throws Exception {
		this.music=new Music(1,"First Class","www.abcd.com","Arijit","www.abcd.com","jhabrajesh");
		this.musicRepository.save(music);
		final Music fetchedMusic = this.musicRepository.findByName(music.getName());
		assertEquals("Arijit",fetchedMusic.getArtistName());
	}
	
	@Test
	public void testGetMyMusic() throws Exception {
		this.musicRepository.save(new Music(1,"First Class","www.abcd.com","Arijit","www.abcd.com","jhabrajesh"));
		final List<Music> musics = this.musicRepository.findByUserId("jhabrajesh");
		assertEquals(musics.size(),1);
	}

	@Test
	public void testDeleteMusic() throws Exception {
		this.music=new Music(1,"First Class","www.abcd.com","Arijit","www.abcd.com","jhabrajesh");
		this.musicRepository.save(music);
		final Music fetchedmusic = this.musicRepository.findByName(music.getName());
		assertEquals("Arijit", fetchedmusic.getArtistName());
		this.musicRepository.delete(fetchedmusic);
		assertEquals(Optional.empty(), musicRepository.findById(1));
	}


}

