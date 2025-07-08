import {Component, OnInit} from '@angular/core';
import {User} from '../../../models/User';
import {Alert} from '../../../dialog/alert.component';
import {FormsModule} from '@angular/forms';
import {lastValueFrom} from 'rxjs';
import {AuthService} from '../../../services/auth.service';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import {UserService} from '../../../services/user.service';
import {Confirm} from '../../../dialog/confirm.component';

@Component({
    selector: 'app-people.component',
    imports: [
        FormsModule,
        RouterLink,
        RouterLinkActive,
    ],
    templateUrl: './people.component.html',
    styleUrl: './people.component.css'
})
export class PeopleComponent implements OnInit {
    public user: User = new User();
    public users: User[] = [];
    public alert: Alert = new Alert();
    public confirm: Confirm = new Confirm();
    public showNotification = false;

    constructor(
        private _user: UserService,
        private _auth: AuthService,
        private _router: Router,

    ) {

    }

    async ngOnInit(): Promise<void> {
        try {
            this.user = await lastValueFrom(this._auth.check());

            this.users = await lastValueFrom(this._user.list());
            this._auth.user = Object.assign(new User, this.user);

        } catch (e: any) {

            if (e.error)
                this.alert.openDialog('Error','You\'re not logged');

            await this._router.navigate(['/login']);
            return;
        }
    }

    toggleNotification() {
        this.showNotification = !this.showNotification;
    }

    async update(id?: number): Promise<void> {
        await this._router.navigate(['/profile', id]);

    }

    async delete(id?: number): Promise<void> {

        if (id) {
            try {

                let result = await this.confirm.openDialog("Are you sure you want to delete this user?");

                if (result) {
                    const response: any = await lastValueFrom(this._user.delete(await lastValueFrom(this._user.get(id))));
                    this.alert.openDialog('Success', response.message);
                    // @ts-ignore
                    if (this._auth.user['id'] == id)
                        await this._router.navigate(['/login']);
                    else
                        await this.ngOnInit();
                }

            } catch (e: any) {
                this.alert.openDialog('Error', e.error.message);
            }

        }
    }
}
