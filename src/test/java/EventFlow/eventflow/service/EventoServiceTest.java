package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.EventoRequestDTO;
import EventFlow.eventflow.entity.Evento;
import EventFlow.eventflow.entity.Local;
import EventFlow.eventflow.entity.Organizador;
import EventFlow.eventflow.enums.StatusEvento;
import EventFlow.eventflow.mapper.EventoMapper;
import EventFlow.eventflow.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @InjectMocks
    private EventoService service;

    @Mock
    private EventoRepository repository;

    @Mock
    private EventoMapper mapper;

    @Mock
    private OrganizadorService organizadorService;

    @Mock
    private LocalService localService;

    @Test
    @DisplayName("Deve criar um evento com sucesso quando dados forem válidos")
    void deveCriarEventoComSucesso() {

        EventoRequestDTO dto = new EventoRequestDTO();
        dto.setTitulo("Java Day");
        dto.setOrganizadorId(1);
        dto.setLocalId(1);

        Organizador organizadorMock = new Organizador();
        Local localMock = new Local();
        Evento eventoMock = new Evento();
        eventoMock.setId(1);
        eventoMock.setTitulo("Java Day");

        // Ensinando os Mocks a responderem
        when(organizadorService.findById(1)).thenReturn(organizadorMock);
        when(localService.findById(1)).thenReturn(localMock);
        when(mapper.toEntity(dto, organizadorMock, localMock)).thenReturn(eventoMock);
        when(repository.save(eventoMock)).thenReturn(eventoMock);


        Evento resultado = service.criar(dto);


        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any()); // Garante que o save foi chamado
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar deletar evento com status ABERTO")
    void deveLancarErroAoDeletarEventoAberto() {

        Integer id = 1;
        Evento eventoAberto = new Evento();
        eventoAberto.setId(id);
        eventoAberto.setStatus(StatusEvento.ABERTO); // Status proibido para delete

        when(repository.findById(id)).thenReturn(Optional.of(eventoAberto));


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.delete(id);
        });


        assertTrue(exception.getMessage().contains("não pode excluir um evento ativo"));


        verify(repository, never()).delete(any());
    }

    @Test
    @DisplayName("Deve deletar com sucesso quando evento estiver CANCELADO")
    void deveDeletarEventoCancelado() {

        Integer id = 1;
        Evento eventoCancelado = new Evento();
        eventoCancelado.setId(id);
        eventoCancelado.setStatus(StatusEvento.CANCELADO);

        when(repository.findById(id)).thenReturn(Optional.of(eventoCancelado));


        service.delete(id);


        // Verifica se o método delete do repository foi chamado 1 vez
        verify(repository, times(1)).delete(eventoCancelado);
    }

    @Test
    @DisplayName("Deve lançar erro EntityNotFoundException ao buscar ID inexistente")
    void deveLancarErroIdInexistente() {

        Integer idInexistente = 99;
        when(repository.findById(idInexistente)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> service.findById(idInexistente));
    }
}