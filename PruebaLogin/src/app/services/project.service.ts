import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {Project} from '../models/Project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

    constructor(private _http: HttpClient) {

    }
    create(project: Project): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);
        return this._http.post(environment.host+'/project', project, {headers: headers});
    }

    list(id: number): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);

        return this._http.get(environment.host + '/projects/' + id, {headers: headers});
    }

    get(id: number): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);

        return this._http.get(environment.host + '/projects/project/' + id, {headers: headers});
    }
}
