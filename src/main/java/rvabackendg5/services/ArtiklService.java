package rvabackendg5.services;

import java.util.List;

import rvabackendg5.models.Artikl;

public interface ArtiklService extends CrudService<Artikl> {

	List<Artikl> getArtiklsByNaziv(String naziv);
}
