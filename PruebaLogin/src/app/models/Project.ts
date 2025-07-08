import {FormControl} from '@angular/forms';

export class Project {
    public id: number = 0;
    public users: string[] = [];
    public project_name?: string = '';
    public short_name?: string = '';
    public budget_type?: string  = '';
    public description?: string = '';
    public status?: string[] = [];
    public practice?: string = '';
    public account: FormControl = new FormControl();
    public start_date?: Date | null = null;
    public start_time?: string = '';
    public end_date?: Date | null = null;
    public end_time?: string = '';
    public source_type?: string = '';
    public msa_signer?: string = '';
    public billing_entity?: string = '';
    public industry?: string = '';
    public region?: string = '';
}
