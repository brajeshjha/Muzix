import { Component, OnInit } from '@angular/core';
import { Music } from '../../music';
import { MusicService } from '../../music.service';
import { MatSnackBar } from '@angular/material';


@Component({
  selector: 'music-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {
  musics: Array<Music>;
  usePlaylistApi: boolean;


  constructor(private musicService: MusicService, private snackBar: MatSnackBar) {
    this.musics = [];
    this.usePlaylistApi = true;
  }

  ngOnInit() {
    this.musicService.getPlaylist().subscribe((musics) => {
      if (musics.length == 0) {
        this.snackBar.open('Favorites is empty', '', {
          duration: 1000
        });
      }
      this.musics.push(...musics);
    })
  }

}
