import { MusicService } from './../../music.service';
import { Component, OnInit } from '@angular/core';
import { Music } from '../../music';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'music-lastfm-container',
  templateUrl: './lastfm-container.component.html',
  styleUrls: ['./lastfm-container.component.css']
})
export class LastfmContainerComponent implements OnInit {
  musics: Array<Music>;
  musicType: string;


  constructor(private musicService: MusicService, private route: ActivatedRoute) {
    this.musics = [];
    this.route.data.subscribe((data) => {
      this.musicType = data.musicType
      console.log(this.musicType)
    })
  }

  ngOnInit() {
    this.musicService.getMusic(this.musicType).subscribe(
      (musics) => {
        this.musics.push(...musics)
      }
    )
  }


}
