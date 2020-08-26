package association.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import association.model.Flat;
import association.repositories.FlatRepository;
import association.services.FlatService;

@Service
public class JpaFlatService implements FlatService {

	@Autowired
	FlatRepository flatRepository;

	@Override
	public List<Flat> findAll() {
		return flatRepository.findAll();
	}

	@Override
	public Flat save(Flat flat) {
		return flatRepository.save(flat);
	}

	@Override
	public Flat findOne(Long id) {
		return flatRepository.findById(id).get();
	}

}
