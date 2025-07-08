import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {User} from '../models/User';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    constructor(private _http: HttpClient) {

    }

    list(): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);

        return this._http.get(environment.host + '/user', {headers: headers});
    }

    get(id: number): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);

        return this._http.get(environment.host + '/user/' + id, {headers: headers});
    }

    delete(user: User): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);

        return this._http.delete(environment.host + '/user/' + user.id, {headers: headers});
    }

    update(user: User): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);

        return this._http.put(environment.host + '/user/' + user.id, user, {headers: headers});
    }

    add(user: User): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);

        return this._http.post(environment.host + '/user', user, {headers: headers});
    }

}
