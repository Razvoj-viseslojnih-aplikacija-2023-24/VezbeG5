package rvabackendg5.services;

import java.util.List;

import rvabackendg5.models.Dobavljac;

public interface DobavljacService extends CrudService<Dobavljac>{
	List<Dobavljac> getDobavljacsByAdresa(String adresa);

}
