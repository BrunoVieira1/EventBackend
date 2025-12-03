package EventFlow.eventflow.repository;

import EventFlow.eventflow.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    Optional<Participante> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByDocumento(String documento);
}