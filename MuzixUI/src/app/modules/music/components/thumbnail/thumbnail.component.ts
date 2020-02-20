import { Music } from './../../music';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'music-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  music: Music;

  @Input()
  usePlaylistApi: boolean;

  @Output()
  addMusic = new EventEmitter();

  @Output()
  deleteMusic = new EventEmitter()

  constructor() { }

  ngOnInit() {
  }

  addToPlaylist() {
    console.log("inside add");

    this.addMusic.emit(this.music);
  }

  deleteFromPlaylist() {
    this.deleteMusic.emit(this.music);
  }

}
