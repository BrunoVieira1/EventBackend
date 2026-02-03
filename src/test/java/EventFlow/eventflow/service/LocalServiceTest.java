package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.LocalRequestDTO;
import EventFlow.eventflow.entity.Local;
import EventFlow.eventflow.mapper.LocalMapper;
import EventFlow.eventflow.repository.LocalRepository;
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
class LocalServiceTest {

    @InjectMocks
    private LocalService service;

    @Mock
    private LocalRepository repository;

    @Mock
    private LocalMapper mapper;

    @Test
    void deveCriarLocalComSucesso() {
        LocalRequestDTO dto = new LocalRequestDTO();
        Local localMock = new Local();
        localMock.setId(1);

        when(mapper.toEntity(dto)).thenReturn(localMock);
        when(repository.save(localMock)).thenReturn(localMock);

        Local resultado = service.criar(dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        Integer id = 1;
        Local local = new Local();
        local.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(local));

        Local resultado = service.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void deveLancarErroAoBuscarIdInexistente() {
        Integer id = 99;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
    }
}