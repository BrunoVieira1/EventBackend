package EventFlow.eventflow.repository;

import EventFlow.eventflow.entity.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {
    List<Ingresso> findByEventoId(Integer eventoId);
    boolean existsByEventoIdAndParticipanteId(Integer eventoId, Integer participanteId);
}