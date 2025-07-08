import {Component, OnInit} from '@angular/core';
import {User} from '../../models/User';
import {Alert} from '../../dialog/alert.component';
import {UserService} from '../../services/user.service';
import {FormsModule} from '@angular/forms';
import {lastValueFrom} from 'rxjs';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Params, Router, RouterLink} from '@angular/router';
@Component({
    selector: 'app-profile.component',
    imports: [
        FormsModule,
        RouterLink,
    ],
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
    public user: User = new User();
    public alert: Alert = new Alert();
    public id: Number | null = null;

    constructor(
        private _user: UserService,
        private _auth: AuthService,
        private _router: Router,
        private _route: ActivatedRoute,

    ) {

    }

    async ngOnInit(): Promise<void> {
        try {
            this._route.params.subscribe({
                next: async (params: Params) => {
                    const id = params['id'] ?? null;
                    if(!id) {
                        this.user = await lastValueFrom(this._auth.check());
                        this._auth.user = Object.assign(new User, this.user);
                    } else {
                        let other = await lastValueFrom(this._auth.check());
                        if(other.admin || id == other.id) {
                            this.id = id;
                            this.user = await lastValueFrom(this._user.get(Number(id)));
                        }
                    }

                }
            })

        } catch (e: any) {
            if (e.error)
                this.alert.openDialog('Error', e.error.message);
            await this._router.navigate(['/login']);
            return;
        }
    }

    async update() {
        try {
            await lastValueFrom(this._user.update(this.user));
        }catch (e: any) {
            if(this.id)
                await this.ngOnInit();
            else
                this.user = Object.assign(new User, this._auth.user);
            this.alert.openDialog('Error', e.error.message);
        }
    }

}
