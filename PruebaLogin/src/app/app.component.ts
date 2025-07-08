import {Component, OnInit} from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import {AuthService} from './services/auth.service';
import {lastValueFrom} from 'rxjs';

@Component({
    selector: 'app-root',
    imports: [
        RouterModule
    ],
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

    constructor(
        private _auth: AuthService,
        private _router: Router,
    ) {

    }

    async ngOnInit() {
        try {
            await lastValueFrom(this._auth.check());
            if (this._router.url.endsWith('/'))
                await this._router.navigate(['/people']);

        }catch(e) {
            if (this._router.url.endsWith('/register')) {
                await this._router.navigate(['/register']);
            } else {
                await this._router.navigate(['/login']);
            }

        }
    }

}
