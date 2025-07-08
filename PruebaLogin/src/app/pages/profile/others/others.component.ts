import {Component, OnInit, output} from '@angular/core';
import {User} from '../../../models/User';
import {Alert} from '../../../dialog/alert.component';
import {UserService} from '../../../services/user.service';
import {AuthService} from '../../../services/auth.service';
import {Router, RouterLink} from '@angular/router';
import {lastValueFrom} from 'rxjs';
import {ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-others.component',
    imports: [
        ReactiveFormsModule,
        RouterLink
    ],
  templateUrl: './others.component.html',
  styleUrl: './others.component.css'
})
export class OthersComponent implements OnInit {
    public user: User = new User();
    public users: User[] = [];
    public alert: Alert = new Alert();
    newLogUser = output<User>();

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

            localStorage.removeItem('token');
            await this._router.navigate(['/login']);
            return;
        }
    }

    async update(id?: number): Promise<void> {
        await this._router.navigate(['/profile', id]);

    }
    async delete(id?: number): Promise<void> {

        if (id) {
            try {
                const response: any = await lastValueFrom(this._user.delete(await lastValueFrom(this._user.get(id))));
                this.alert.openDialog('Success', response.message);
                // @ts-ignore
                if (this._auth.user['id'] == id)
                    await this._router.navigate(['/login']);
                else
                    await this.ngOnInit();

            } catch (e: any) {
                this.alert.openDialog('Error', e.error.message);
            }

        }
    }

}
