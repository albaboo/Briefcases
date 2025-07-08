import {ChangeDetectionStrategy, Component, inject} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {
    MAT_DIALOG_DATA,
    MatDialog,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
} from '@angular/material/dialog';

export interface DialogData {
    title: string;
    info: string;
}

export class Alert {
    readonly dialog = inject(MatDialog);

    openDialog(status: string, message: string): void {
        this.dialog.open(AlertComponent, {
            width: '250px',
            data: {title: status, info: message},
        });
    }
}

@Component({
    selector: 'alert.component',
    template: `
    <h2 mat-dialog-title>{{data.title}}</h2>
    <mat-dialog-content>
      {{data.info}}
    </mat-dialog-content>
    <mat-dialog-actions>
      <button matButton mat-dialog-close>Ok</button>
    </mat-dialog-actions>
  `,
    imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AlertComponent {

    readonly data = inject<DialogData>(MAT_DIALOG_DATA);

}
