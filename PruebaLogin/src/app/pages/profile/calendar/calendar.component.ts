import {Component, OnInit} from '@angular/core';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import {lastValueFrom} from 'rxjs';
import {User} from '../../../models/User';
import {Alert} from '../../../dialog/alert.component';
import {AuthService} from '../../../services/auth.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-calendar.component',
    imports: [
        RouterLink,
        RouterLinkActive,
        ReactiveFormsModule,
        FormsModule
    ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent implements OnInit {
    public user: User = new User();
    public alert: Alert = new Alert();
    public currentDate = new Date();
    public currentMonth!: number;
    public currentYear!: number;
    public weeks: any[][] = [];
    public showNotification = false;

    constructor(
        private _auth: AuthService,
        private _router: Router,

    ) {

    }
    async ngOnInit(): Promise<void> {
        this.currentMonth = this.currentDate.getMonth();
        this.currentYear = this.currentDate.getFullYear();
        this.generateCalendar(this.currentYear, this.currentMonth);
        try {
            this.user = await lastValueFrom(this._auth.check());
            this._auth.user = Object.assign(new User, this.user);
        } catch (e: any) {

            if (e.error)
                this.alert.openDialog('Error','You\'re not logged');

            localStorage.removeItem('token');
            await this._router.navigate(['/login']);
            return;
        }
    }
    searchMonth(event: Event) {
        const input = event.target as HTMLInputElement;
        const value = input.value;

        if (value) {
            this.currentYear = Number(value.split('-')[0]);
            this.currentMonth = Number(value.split('-')[1])-1;
            this.generateCalendar(this.currentYear, this.currentMonth);
        }
    }
    generateCalendar(year: number, month: number) {
        this.currentDate = new Date(this.currentYear, this.currentMonth)
        const firstDayUs = new Date(year, month, 1).getDay();
        const firstDay = (firstDayUs === 0) ? 6 : firstDayUs - 1;
        const daysInMonth = new Date(year, month + 1, 0).getDate();

        const calendarDays: (number | null)[] = Array(firstDay).fill(null).concat(
            Array.from({ length: daysInMonth }, (_, i) => i + 1)
        );

        while (calendarDays.length % 7 !== 0) {
            calendarDays.push(null);
        }

        this.weeks = [];
        for (let i = 0; i < calendarDays.length; i += 7) {
            this.weeks.push(calendarDays.slice(i, i + 7));
        }
    }
    nextMonth() {
        if (this.currentMonth === 11) {
            this.currentMonth = 0;
            this.currentYear++;
        } else {
            this.currentMonth++;
        }
        this.generateCalendar(this.currentYear, this.currentMonth);
    }

    prevMonth() {
        if (this.currentMonth === 0) {
            this.currentMonth = 11;
            this.currentYear--;
        } else {
            this.currentMonth--;
        }
        this.generateCalendar(this.currentYear, this.currentMonth);
    }

    toggleNotification() {
        this.showNotification = !this.showNotification;
    }

}
