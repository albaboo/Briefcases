import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../models/User';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    public user: User|null = null;
    constructor(private _http: HttpClient) {

    }

    login(user: User): Observable<any> {
        return this._http.post(environment.host+'/login', user);
    }

    register(user: User): Observable<any> {
        return this._http.post(environment.host+'/user', user);
    }

    check(): Observable<any> {
        const headers = new HttpHeaders()
            .append('Authorization', `Bearer ${localStorage.getItem('token')}`);
        return this._http.get(environment.host+'/check', {headers: headers});
    }
}
