import {Routes} from '@angular/router';
import {LoginComponent} from './pages/login/login.component';
import {RegisterComponent} from './pages/register/register.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {OthersComponent} from './pages/profile/others/others.component';
import {PeopleComponent} from './pages/profile/people/people.component';
import {DashboardComponent} from './pages/profile/dashboard/dashboard.component';
import {CalendarComponent} from './pages/profile/calendar/calendar.component';
import {ProjectsComponent} from './pages/profile/projects/projects.component';

export const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'profile', component: ProfileComponent},
    {path: 'users', component: OthersComponent},
    {path: 'profile/:id', component: ProfileComponent},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'people', component: PeopleComponent},
    {path: 'projects', component: ProjectsComponent},
    {path: 'calendar', component: CalendarComponent},
    {path: 'training', component: PeopleComponent},
    {path: 'timesheet', component: PeopleComponent},
    {path: 'reports', component: PeopleComponent},
    {path: 'administration', component: PeopleComponent},
    {path: 'help', component: PeopleComponent},
];
