package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.ParticipanteRequestDTO;
import EventFlow.eventflow.entity.Participante;
import EventFlow.eventflow.mapper.ParticipanteMapper;
import EventFlow.eventflow.repository.ParticipanteRepository;
import jakarta.persistence.EntityNotFoundException;
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
class ParticipanteServiceTest {

    @InjectMocks
    private ParticipanteService service;

    @Mock
    private ParticipanteRepository repository;

    @Mock
    private ParticipanteMapper mapper;

    @Test
    void deveCriarParticipanteComSucesso() {
        ParticipanteRequestDTO dto = new ParticipanteRequestDTO();
        dto.setEmail("teste@email.com");
        dto.setDocumento("12345678900");

        Participante participanteMock = new Participante();
        participanteMock.setId(1);

        when(repository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(repository.existsByDocumento(dto.getDocumento())).thenReturn(false);
        when(mapper.toEntity(dto)).thenReturn(participanteMock);
        when(repository.save(participanteMock)).thenReturn(participanteMock);

        Participante resultado = service.criar(dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveLancarErroSeEmailDuplicado() {
        ParticipanteRequestDTO dto = new ParticipanteRequestDTO();
        dto.setEmail("existente@email.com");

        when(repository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.criar(dto));
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarErroSeDocumentoDuplicado() {
        ParticipanteRequestDTO dto = new ParticipanteRequestDTO();
        dto.setEmail("novo@email.com");
        dto.setDocumento("12345678900");

        when(repository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(repository.existsByDocumento(dto.getDocumento())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.criar(dto));
        verify(repository, never()).save(any());
    }
}