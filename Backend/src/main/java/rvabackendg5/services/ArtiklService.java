package rvabackendg5.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rvabackendg5.models.Artikl;

@Service
public interface ArtiklService extends CrudService<Artikl> {

	List<Artikl> getArtiklsByNaziv(String naziv);
}
