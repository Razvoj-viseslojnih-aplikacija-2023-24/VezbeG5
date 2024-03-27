package rvabackendg5.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rvabackendg5.models.Dobavljac;
import rvabackendg5.models.Porudzbina;

@Service
public interface PorudzbinaService extends CrudService<Porudzbina>{
	
	List<Porudzbina> getProudzbinasByPlaceno(boolean placeno);
	
	List<Porudzbina> getByForeignKey(Dobavljac dobavljac);

}
