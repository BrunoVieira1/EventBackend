package EventFlow.eventflow.service;

import EventFlow.eventflow.dto.request.OrganizadorRequestDTO;
import EventFlow.eventflow.entity.Organizador;
import EventFlow.eventflow.mapper.OrganizadorMapper;
import EventFlow.eventflow.repository.OrganizadorRepository;
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
class OrganizadorServiceTest {

    @InjectMocks
    private OrganizadorService service;

    @Mock
    private OrganizadorRepository repository;

    @Mock
    private OrganizadorMapper mapper;

    @Test
    void deveCriarOrganizadorComSucesso() {
        OrganizadorRequestDTO dto = new OrganizadorRequestDTO();
        dto.setEmail("teste@email.com");

        Organizador organizadorMock = new Organizador();
        organizadorMock.setId(1);

        when(repository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(mapper.toEntity(dto)).thenReturn(organizadorMock);
        when(repository.save(organizadorMock)).thenReturn(organizadorMock);

        Organizador resultado = service.criar(dto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveLancarErroAoCriarOrganizadorComEmailDuplicado() {
        OrganizadorRequestDTO dto = new OrganizadorRequestDTO();
        dto.setEmail("duplicado@email.com");

        when(repository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.criar(dto));
        verify(repository, never()).save(any());
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        Integer id = 1;
        Organizador organizador = new Organizador();
        organizador.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(organizador));

        Organizador resultado = service.findById(id);

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