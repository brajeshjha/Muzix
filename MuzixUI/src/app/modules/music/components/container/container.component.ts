import { Component, OnInit, Input } from '@angular/core';
import { Music } from '../../music';
import { MusicService } from '../../music.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'music-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  musics: Array<Music>;

  @Input()
  usePlaylistApi: boolean;

  constructor(private musicService: MusicService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  addToPlaylist(music) {
    const message = `${music.name} added to Favorites`;
    this.musicService.addToPlaylist(music).subscribe(() => {
      this.snackBar.open(message, '', {
        duration: 1000
      });
    });
  }
  deleteFromPlaylist(music) {
    this.musicService.deleteFromPlaylist(music).subscribe((result) => {
      let message = `${music.name} ${result}`;
      this.snackBar.open(message, '', {
        duration: 1000
      });
      const index = this.musics.indexOf(music);
      this.musics.splice(index, 1);
    });

  }
}
