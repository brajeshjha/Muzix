package com.stackroute.muzixmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.muzixmanager.domain.Music;
import com.stackroute.muzixmanager.exception.MusicTrackAlreadyExistsException;
import com.stackroute.muzixmanager.exception.MusicTrackNotFoundException;
import com.stackroute.muzixmanager.repository.MusicRepository;

@Service
public class MusicServiceImpl implements MusicService {

	public MusicServiceImpl() {
		
	}

	private MusicRepository musicRepo;
	
	@Autowired
	public MusicServiceImpl(MusicRepository musicRepo) {
		super();
		this.musicRepo = musicRepo;
	}

	@Override
	public List<Music> getMyMusics(String userId) {
		return this.musicRepo.findByUserId(userId);
	}
	
	@Override
	public boolean saveMusic(Music music) throws MusicTrackAlreadyExistsException {
		
		final Optional<Music> musicObject=this.musicRepo.findById(music.getId());
		if(musicObject.isPresent())
		{
			throw new MusicTrackAlreadyExistsException("Could not save music , music already exits");
		}
		this.musicRepo.save(music);
		return true;

	}

	@Override
	public boolean deleteMusicById(int id) throws MusicTrackNotFoundException {
		final Music music=this.musicRepo.findById(id).orElse(null);
		if (music == null) {
			throw new MusicTrackNotFoundException("Could not delete. Music not found");
		}
		this.musicRepo.delete(music);
		return true;

	}


}
