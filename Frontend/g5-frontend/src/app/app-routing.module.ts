import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArtiklComponent } from './main/artikl/artikl.component';
import { DobavljacComponent } from './main/dobavljac/dobavljac.component';
import { PorudzbinaComponent } from './main/porudzbina/porudzbina.component';
import { StavkaPorudzbineComponent } from './main/stavka-porudzbine/stavka-porudzbine.component';
import { HomeComponent } from './utility/home/home.component';
import { AboutComponent } from './utility/about/about.component';
import { AuthorComponent } from './utility/author/author.component';

const routes: Routes = [
  {path:'artikl', component:ArtiklComponent},
  {path:'dobavljac', component:DobavljacComponent},
  {path:'porudzbina', component:PorudzbinaComponent},
  {path:'stavka-porudzbine', component:StavkaPorudzbineComponent},
  {path:'home', component:HomeComponent},
  {path:'about', component:AboutComponent},
  {path:'author', component:AuthorComponent},
  {path:'', component:HomeComponent, pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
