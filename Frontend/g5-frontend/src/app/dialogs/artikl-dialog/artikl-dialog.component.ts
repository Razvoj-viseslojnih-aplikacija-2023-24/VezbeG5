import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Artikl } from 'src/app/models/artikl';
import { ArtiklService } from 'src/app/services/artikl.service';

@Component({
  selector: 'app-artikl-dialog',
  templateUrl: './artikl-dialog.component.html',
  styleUrls: ['./artikl-dialog.component.css']
})
export class ArtiklDialogComponent {

  flag!:number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Artikl>,
    @Inject (MAT_DIALOG_DATA) public data: Artikl,
    public service: ArtiklService
  ){}

  public cancel() {
    this.dialogRef.close();
    this.snackBar.open("Odustali ste od izmena", "Zatvori", {duration: 3500});
  }

  public add() {
    this.service.createArtikl(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Artikl sa nazivom ${data.naziv} je uspešno dodat`, "U redu", {duration:3500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open("Neuspešno dodavanje!", "Zatvori", {duration:3500});
    }
  }

  public update() {
    this.service.updateArtikl(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Artikl sa nazivom ${data.naziv} je uspešno ažuriran`, "U redu", {duration:3500})
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open("Neuspešno ažuriranje!", "Zatvori", {duration:3500});
    }
  }

  public delete() {
    this.service.deleteArtikl(this.data.id).subscribe(
      (data) => {
        this.snackBar.open("Uspešno brisanje", "U redu", {duration:3500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open("Neuspešno brisanje!", "Zatvori", {duration:3500});
    }
  }
}
