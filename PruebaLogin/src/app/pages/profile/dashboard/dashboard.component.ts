import {Component, OnInit} from '@angular/core';
import {User} from '../../../models/User';
import {Project} from '../../../models/Project';
import {Alert} from '../../../dialog/alert.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {lastValueFrom} from 'rxjs';
import {AuthService} from '../../../services/auth.service';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import {ProjectService} from '../../../services/project.service';
import {UserService} from '../../../services/user.service';
import {MatFormField, MatLabel} from '@angular/material/input';
import {MatOption} from '@angular/material/core';
import {MatSelect} from '@angular/material/select';

@Component({
    selector: 'app-dashboard.component',
    imports: [
        FormsModule,
        RouterLink,
        RouterLinkActive,
        MatLabel,
        MatOption,
        MatSelect,
        MatFormField,
        ReactiveFormsModule,
    ],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
    public user: User = new User();
    public users: User[] = [];
    public alert: Alert = new Alert();
    public project: Project = new Project();
    public showNotification = false;

    constructor(
        private _user: UserService,
        private _auth: AuthService,
        private _project: ProjectService,
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

    clear(): void {
        this.project = new Project();
    }

    async search(): Promise<void> {
        try {

            if(this.user.username)
                this.project.users[this.project.users.length] = this.user.username;

            this.project.account = this.project.account.value;

            const response: any = await lastValueFrom(this._project.create(this.project));
            this.alert.openDialog('Success', response.message);
            this.clear();

        } catch (e: any) {

            if (e.error)
                this.alert.openDialog('Error', e.error.message);

            this.clear();

        }
    }

    toggleNotification() {
        this.showNotification = !this.showNotification;
    }
}
