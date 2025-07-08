import {Component, OnInit, output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {User} from '../../models/User';
import {Alert} from '../../dialog/alert.component';
import {AuthService} from '../../services/auth.service';
import {lastValueFrom} from 'rxjs';
import {Router, RouterLink} from '@angular/router';

@Component({
    selector: 'app-login',
    imports: [
        FormsModule,
        ReactiveFormsModule,
        RouterLink
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
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

    async login() {
        try {
            const response: any = await lastValueFrom(this._auth.login(this.user));
            localStorage.setItem('token', response.token);
            this._auth.user = response.user;
            this.newLogUser.emit(response.user);
            await this._router.navigate(['/people']);
        } catch (e: any) {
            if (e.error)
                this.alert.openDialog('Error', e.error.message);
        }

    }
}
