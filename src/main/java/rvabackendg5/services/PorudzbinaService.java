package rvabackendg5.services;

import java.util.List;

import rvabackendg5.models.Dobavljac;
import rvabackendg5.models.Porudzbina;

public interface PorudzbinaService extends CrudService<Porudzbina>{
	
	List<Porudzbina> getProudzbinasByPlaceno(boolean placeno);
	
	List<Porudzbina> getByForeignKey(Dobavljac dobavljac);

}
