import {Component, OnInit} from '@angular/core';
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AuthService} from '../../../services/auth.service';
import {lastValueFrom} from 'rxjs';
import {User} from '../../../models/User';
import {Project} from '../../../models/Project';
import {Alert} from '../../../dialog/alert.component';
import {ProjectService} from '../../../services/project.service';

@Component({
  selector: 'app-projects.component',
    imports: [
        RouterLink,
        RouterLinkActive
    ],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css'
})
export class ProjectsComponent implements OnInit {
    public user: User = new User();
    public project: Project | undefined;
    public projects: Project[] = [];
    public alert: Alert = new Alert();
    public showNotification = false;

    constructor(
        private _project: ProjectService,
        private _auth: AuthService,
        private _router: Router,

    ) {

    }

    async ngOnInit(): Promise<void> {
        try {
            this.user = await lastValueFrom(this._auth.check());
            this.project = undefined;
            if(this.user.id)
                this.projects = await lastValueFrom(this._project.list(this.user.id));

            this._auth.user = Object.assign(new User, this.user);
        } catch (e: any) {

            if (e.error)
                this.alert.openDialog('Error',e.error.message);
            else
                this.alert.openDialog('Error','You\'re not logged');

            localStorage.removeItem('token');
            await this._router.navigate(['/login']);
            return;
        }
    }

    toggleNotification() {
        this.showNotification = !this.showNotification;
    }

    async seeProject(id: number) {
        this.project = await lastValueFrom(this._project.get(Number(id)));
    }

    seeProjects() {
        this.project = undefined;
    }
}
