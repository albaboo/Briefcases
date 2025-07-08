import {ChangeDetectionStrategy, Component, inject} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {
    MAT_DIALOG_DATA,
    MatDialog,
    MatDialogActions,
    MatDialogRef,
    MatDialogTitle,
} from '@angular/material/dialog';
import {FormsModule} from '@angular/forms';
import {lastValueFrom} from 'rxjs';

export interface DialogData {
    title: string;
}

export class Confirm {
    readonly dialog = inject(MatDialog);

    openDialog(title: string): Promise<boolean | undefined>  {
        return lastValueFrom(this.dialog.open(ConfirmComponent, {
            width: '250px',
            data: title,
            disableClose: true,
        }).afterClosed());
    }
}

@Component({
    selector: 'confirm.component',
    template: `
    <h2 mat-dialog-title>{{data}}</h2>
    <mat-dialog-actions align="end">
        <button mat-button (click)="submit(false)" tabindex="-1">Cancel</button>
        <button mat-button (click)="submit(true)" tabindex="-1">OK</button>
    </mat-dialog-actions>

  `,
    imports: [MatButtonModule, MatDialogActions, MatDialogTitle, FormsModule],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ConfirmComponent {
    readonly dialogRef = inject(MatDialogRef<ConfirmComponent>);
    readonly data = inject<DialogData>(MAT_DIALOG_DATA);

    submit(result: boolean) {
        this.dialogRef.close(result);
    }

}
