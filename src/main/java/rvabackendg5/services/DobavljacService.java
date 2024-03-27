package rvabackendg5.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rvabackendg5.models.Dobavljac;

@Service
public interface DobavljacService extends CrudService<Dobavljac>{
	List<Dobavljac> getDobavljacsByAdresa(String adresa);

}
