import {Component, OnInit, output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import {User} from '../../models/User';
import {Alert} from '../../dialog/alert.component';
import {AuthService} from '../../services/auth.service';
import {Router, RouterLink} from '@angular/router';
import {lastValueFrom} from 'rxjs';

@Component({
  selector: 'app-register',
    imports: [
        FormsModule,
        ReactiveFormsModule,
        RouterLink
    ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
    user: User = new User;
    alert: Alert = new Alert();
    newLogUser = output<User>();

    constructor(
        private _auth: AuthService,
        private _router: Router
    ) {

    }

    ngOnInit() {
        localStorage.removeItem('token');
    }

    async register() {
        try {
            const response: any = await lastValueFrom(this._auth.register(this.user));
            localStorage.setItem('token', response.token);
            this._auth.user = response.user;
            this.alert.openDialog('Success', response.message);
            this.newLogUser.emit(response.user);
            await this._router.navigate(['/people']);
        } catch (e: any) {
            this.alert.openDialog('Error', e.error.message);
        }

    }
}
