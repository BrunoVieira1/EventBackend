package EventFlow.eventflow.repository;

import EventFlow.eventflow.entity.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Integer> {
    boolean existsByEmail(String email);
}