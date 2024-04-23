import { Artikl } from "./artikl";
import { Porudzbina } from "./porudzbina";

export class StavkaPorudzbine {
  id!:number;
  redniBroj!:number;
  cena!:number;
  kolicina!:number;
  jedinicaMere!:number;
  artikl!:Artikl;
  porudzbina!:Porudzbina;
}
