import { Component, OnInit } from '@angular/core';
import { Music } from '../../music';
import { MusicService } from '../../music.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'music-music-search',
  templateUrl: './music-search.component.html',
  styleUrls: ['./music-search.component.css']
})
export class MusicSearchComponent implements OnInit {

  musics: Array<Music>;
  message: string;

  constructor(private musicService: MusicService, private matSnackBar: MatSnackBar) { }

  ngOnInit() {
  }

  onEnter(searchKey) {
    //console.log(searchKey)
    if (searchKey.length > 0) {
      this.musicService.searchMusicFromApi(searchKey).subscribe((musics) => {
        this.musics = musics;
      });
    }
    else {
      this.musics = [];
    }
  }


}
