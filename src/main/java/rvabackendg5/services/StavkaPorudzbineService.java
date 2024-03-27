package rvabackendg5.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rvabackendg5.models.Artikl;
import rvabackendg5.models.Porudzbina;
import rvabackendg5.models.StavkaPorudzbine;

@Service
public interface StavkaPorudzbineService extends CrudService<StavkaPorudzbine>{
	
	List<StavkaPorudzbine> getStavkasByCenaLessThan(double cena);
	List<StavkaPorudzbine> getByForeignKey(Artikl artikl);
	List<StavkaPorudzbine> getByForeignKey(Porudzbina porudzbina);

}
