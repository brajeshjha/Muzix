import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map, retry } from 'rxjs/operators';

import { Music } from './music';


@Injectable({
  providedIn: 'root'
})
export class MusicService {

  apiKey: string;
  lastfmEndpoint: string;
  serverEndpoint: string;

  constructor(private http: HttpClient) {
    this.apiKey = 'api_key=6b6c443edc7cbf72e5dba1ac17688fa3';
    this.lastfmEndpoint = 'http://ws.audioscrobbler.com/2.0/';
    this.serverEndpoint = 'http://localhost:8080/api/music/';
  }

  getMusic(musicType: string): Observable<Array<Music>> {
    const endpoint = `${this.lastfmEndpoint}?method=chart.${musicType}&${this.apiKey}&format=json`;
    return this.http.get(endpoint).pipe(
      retry(3),
      map(this.pickMusicResults),
      map(this.transformPath.bind(this))
    );
  }
  pickMusicResults(response) {
    return response['tracks']['track'];
  }

  transformPath(musics): Array<Music> {
    console.log(musics);
    let arr = [];
    for (let object of musics) {
      let music: Music = new Music();
      music.name = object['name'];
      music.artistName = object.artist.name;
      music.imageUrl = object.image[3]['#text'];
      music.url = object.url;
      music.playcount = object['playcount'];
      arr.push(music);
    }
    return arr;
  }

  getPlaylist(): Observable<any> {
    return this.http.get<Array<Music>>(this.serverEndpoint);
  }

  addToPlaylist(music) {
    return this.http.post(this.serverEndpoint, music);
  }

  deleteFromPlaylist(music: Music) {
    const endpoint = this.serverEndpoint + music.id;
    return this.http.delete(endpoint, { responseType: 'text' });
  }

  searchMusicFromApi(track: string): Observable<Array<Music>> {
    if (track.length > 0) {
      const searchEndpoint = `${this.lastfmEndpoint}?method=track.search&track=${track}&${this.apiKey}&format=json`;
      return this.http.get(searchEndpoint).pipe(
        map(object => {
          return object['results']['trackmatches']['track'];
        }),
        map(this.transformPath.bind(this)));
    }
  }

}
