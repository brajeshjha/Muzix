import { SharedModule } from '../shared/shared.module';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { ContainerComponent } from './components/container/container.component';
import { LastfmContainerComponent } from './components/lastfm-container/lastfm-container.component';
import { PlaylistComponent } from './components/playlist/playlist.component';
import { MusicSearchComponent } from './components/music-search/music-search.component';
import { MusicService } from './music.service';
import { MusicRouterModule } from './music-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './token-interceptor.service';

@NgModule({
  declarations: [ThumbnailComponent, ContainerComponent, LastfmContainerComponent, PlaylistComponent, MusicSearchComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    SharedModule,
    MusicRouterModule
  ],
  exports: [
    MusicRouterModule
  ],
  providers: [
    MusicService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ]
})
export class MusicModule { }
