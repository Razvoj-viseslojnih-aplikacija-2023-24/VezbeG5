package rvabackendg5.services;

import java.util.List;

import rvabackendg5.models.Artikl;
import rvabackendg5.models.Porudzbina;
import rvabackendg5.models.StavkaPorudzbine;

public interface StavkaPorudzbineService extends CrudService<StavkaPorudzbine>{
	List<StavkaPorudzbine> getPorudzbinasByPlaceno(boolean placeno);
	List<StavkaPorudzbine> getByForeignKey(Artikl artikl);
	List<StavkaPorudzbine> getByForeignKey(Porudzbina porudzbina);

}
