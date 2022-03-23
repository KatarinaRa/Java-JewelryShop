package app.Services;

import app.Persistance.Entities.Category;
import app.Persistance.Entities.Cupons;
import app.Persistance.Repositories.CategoryRepository;
import app.Persistance.Repositories.CuponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CuponsService {

    private final CuponsRepository repository;

    public CuponsService(CuponsRepository repository) {
        this.repository = repository;
    }

    public List<Cupons> getAllCupons(){
        return repository.findAll();
    }

    public void addCupons(Cupons cupons){
        repository.save(cupons);
    }

    public void deleteCupons(UUID id){
        repository.deleteById(id);
    }
    public Optional<Cupons> updateCupons(UUID id){
        return repository.findById(id);
    }
    public Cupons findByCode(String code){ return repository.findByCode(code);}
}
