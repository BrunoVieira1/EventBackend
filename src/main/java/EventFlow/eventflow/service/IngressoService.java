package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.IngressoRequestDTO;
import EventFlow.eventflow.entity.Evento;
import EventFlow.eventflow.entity.Ingresso;
import EventFlow.eventflow.entity.Participante;
import EventFlow.eventflow.mapper.IngressoMapper;
import EventFlow.eventflow.repository.IngressoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository repository;

    @Autowired
    private IngressoMapper mapper;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ParticipanteService participanteService;

    public Ingresso criar(IngressoRequestDTO dto) {
        if (repository.existsByEventoIdAndParticipanteId(dto.getEventoId(), dto.getParticipanteId())) {
            throw new IllegalArgumentException("Este participante já possui um ingresso para este evento.");
        }
        Evento evento = eventoService.findById(dto.getEventoId());
        Participante participante = participanteService.findById(dto.getParticipanteId());

        int qtdVendida = repository.findByEventoId(dto.getEventoId()).size();
        if (qtdVendida >= evento.getLocal().getCapacidadeMaxima()) {
            throw new IllegalArgumentException("Evento esgotado!");
        }

        Ingresso ingresso = mapper.toEntity(dto, evento, participante);
        return repository.save(ingresso);
    }

    public List<Ingresso> findAll() {
        return repository.findAll();
    }

    public Ingresso findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingresso não encontrado! ID: " + id));
    }

    public Ingresso update(Integer id, IngressoRequestDTO dto) {
        Ingresso ingressoAntigo = findById(id);

        Evento evento = eventoService.findById(dto.getEventoId());
        Participante participante = participanteService.findById(dto.getParticipanteId());

        Ingresso ingressoNovo = mapper.toEntity(dto, evento, participante);

        ingressoNovo.setId(id);


        ingressoNovo.setDataCompra(ingressoAntigo.getDataCompra());

        return repository.save(ingressoNovo);
    }

    public void delete(Integer id) {
        Ingresso ingresso = findById(id);
        repository.delete(ingresso);
    }
}