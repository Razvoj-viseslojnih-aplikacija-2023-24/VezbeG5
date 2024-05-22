import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { ArtiklDialogComponent } from 'src/app/dialogs/artikl-dialog/artikl-dialog.component';
import { Artikl } from 'src/app/models/artikl';
import { ArtiklService } from 'src/app/services/artikl.service';

@Component({
  selector: 'app-artikl',
  templateUrl: './artikl.component.html',
  styleUrls: ['./artikl.component.css']
})
export class ArtiklComponent implements OnInit, OnDestroy{
  displayedColumns = ['id', 'naziv', 'proizvodjac', 'actions'];
  dataSource!:MatTableDataSource<Artikl>;
  subscription!:Subscription;

  @ViewChild(MatSort, {static:false}) sort!:MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!:MatPaginator;
  constructor(private service:ArtiklService, public dialog:MatDialog){}

  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public loadData() {
    this.subscription = this.service.getAllArtikls().subscribe(
      (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
    }
  }

  public openDialog(flag:number, id?:number, naziv?:string, proizvodjac?: string) {
    const dialogRef = this.dialog.open(ArtiklDialogComponent, {data : {id, naziv, proizvodjac}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      (result) => {
        if(result == 1) {
          this.loadData();
        }
      }
    )
  }

  public applyFilter(filter:any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }

}
