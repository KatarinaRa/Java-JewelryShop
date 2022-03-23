package app.Persistance.Repositories;

import app.Persistance.Entities.Cupons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CuponsRepository extends JpaRepository<Cupons, UUID>{
    public Cupons findByCode(String code);
}