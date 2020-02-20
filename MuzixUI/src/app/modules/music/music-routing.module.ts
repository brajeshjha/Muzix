import { MusicSearchComponent } from './components/music-search/music-search.component';
import { PlaylistComponent } from './components/playlist/playlist.component';
import { LastfmContainerComponent } from './components/lastfm-container/lastfm-container.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'src/app/authguard.service';


const musicRoutes: Routes = [
    {
        path: 'music',
        children: [
            {
                path: '',
                redirectTo: '/music/gettoptracks',
                pathMatch: 'full',

            },
            {
                path: 'gettoptracks',
                component: LastfmContainerComponent,
                canActivate: [AuthGuardService],
                data: {
                    musicType: 'gettoptracks'
                },
            },
            {
                path: 'playlist',
                component: PlaylistComponent,
                canActivate: [AuthGuardService]
            },
            {
                path: 'search',
                component: MusicSearchComponent,
                canActivate: [AuthGuardService]
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(musicRoutes),
    ],
    exports: [
        RouterModule,
    ]
})
export class MusicRouterModule { }
