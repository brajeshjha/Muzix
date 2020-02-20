package com.stackroute.muzixmanager.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixmanager.domain.Music;
import com.stackroute.muzixmanager.service.MusicService;


@RunWith(SpringRunner.class)
@WebMvcTest(MusicController.class)
public class MusicControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private MusicService service;

	private Music music;

	@InjectMocks
	private MusicController controller;

	static List<Music> musics;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mvc = MockMvcBuilders.standaloneSetup(controller).build();
		musics = new ArrayList<>();
		music = new Music(1,"First Class","www.abcd.com","Arijit","www.abcd.com","jhabrajesh");
		musics.add(music);
		music = new Music(2,"High Rated","www.xyz.com","Guru","www.xyz.com","jhabrajesh");
		musics.add(music);
	}

	@Test
	public void testSaveNewMusicSuccess() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaGFicmFqZXNoIiwiaWF0IjoxNTU2Mjc1MTc0fQ.leoJ1Wz759v_JtPGoRN3wEmFudaDN4MmZzi4TD1Yd2A";
		when(service.saveMusic(music)).thenReturn(true);
		mvc.perform(post("/api/music").header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(music)))
				.andExpect(status().isCreated());
		verify(service, times(1)).saveMusic(Mockito.any(Music.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testDeleteMusicById() throws Exception {
		when(service.deleteMusicById(1)).thenReturn(true);
		mvc.perform(delete("/api/music/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).deleteMusicById(1);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testGetMyMovies() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaGFicmFqZXNoIiwiaWF0IjoxNTU2Mjc1MTc0fQ.leoJ1Wz759v_JtPGoRN3wEmFudaDN4MmZzi4TD1Yd2A";
		when(service.getMyMusics("jhabrajesh")).thenReturn(musics);
		mvc.perform(get("/api/music/").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(service, times(1)).getMyMusics(Mockito.anyString());
		verifyNoMoreInteractions(service);
	}

	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}
	
	
}
