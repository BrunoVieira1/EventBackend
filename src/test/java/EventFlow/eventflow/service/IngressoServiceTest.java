package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.IngressoRequestDTO;
import EventFlow.eventflow.entity.Evento;
import EventFlow.eventflow.entity.Ingresso;
import EventFlow.eventflow.entity.Local;
import EventFlow.eventflow.entity.Participante;
import EventFlow.eventflow.mapper.IngressoMapper;
import EventFlow.eventflow.repository.IngressoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngressoServiceTest {

    @InjectMocks
    private IngressoService service;

    @Mock
    private IngressoRepository repository;

    @Mock
    private IngressoMapper mapper;

    @Mock
    private EventoService eventoService;

    @Mock
    private ParticipanteService participanteService;

    @Test
    void deveCriarIngressoComSucesso() {
        IngressoRequestDTO dto = new IngressoRequestDTO();
        dto.setEventoId(1);
        dto.setParticipanteId(1);
        Local localMock = new Local();
        localMock.setId(1);
        localMock.setCapacidadeMaxima(100);

        Evento eventoMock = new Evento();
        Participante participanteMock = new Participante();
        Ingresso ingressoMock = new Ingresso();
        ingressoMock.setId(1);
        eventoMock.setLocal(localMock);

        when(repository.existsByEventoIdAndParticipanteId(1, 1)).thenReturn(false);
        when(eventoService.findById(1)).thenReturn(eventoMock);
        when(participanteService.findById(1)).thenReturn(participanteMock);
        when(mapper.toEntity(dto, eventoMock, participanteMock)).thenReturn(ingressoMock);
        when(repository.save(ingressoMock)).thenReturn(ingressoMock);

        Ingresso resultado = service.criar(dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveLancarErroSeParticipanteJaTemIngressoNoEvento() {
        IngressoRequestDTO dto = new IngressoRequestDTO();
        dto.setEventoId(1);
        dto.setParticipanteId(1);

        when(repository.existsByEventoIdAndParticipanteId(1, 1)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.criar(dto));
        verify(repository, never()).save(any());
    }
}