package EventFlow.eventflow.config;

import EventFlow.eventflow.entity.*;
import EventFlow.eventflow.enums.StatusEvento;
import EventFlow.eventflow.enums.TipoIngresso;
import EventFlow.eventflow.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class MockDataConfig {

    @Bean
    public CommandLineRunner initDatabase(
            OrganizadorRepository organizadorRepository,
            LocalRepository localRepository,
            EventoRepository eventoRepository,
            ParticipanteRepository participanteRepository,
            IngressoRepository ingressoRepository
    ) {
        return args -> {

            if (organizadorRepository.count() > 0) {
                System.out.println("Banco de dados já populado. Mock ignorado.");
                return;
            }

            System.out.println("Iniciando carga de dados Mock...");


            Organizador org1 = new Organizador(null, "Tech Events Ltda", "contato@techevents.com", "11999990000");
            Organizador org2 = new Organizador(null, "Comunidade Java Brasil", "admin@javabrasil.com", "11988887777");
            organizadorRepository.saveAll(Arrays.asList(org1, org2));


            Local loc1 = new Local(null, "Centro de Convenções", "Av. Paulista, 1000", 500);
            Local loc2 = new Local(null, "Auditório Open Space", "Rua Funchal, 200", 50);
            localRepository.saveAll(Arrays.asList(loc1, loc2));


            Evento ev1 = new Evento(null, "Spring Boot Summit", "O maior evento de Spring do ano", LocalDateTime.now().plusDays(20), StatusEvento.ABERTO, org1, loc1);
            Evento ev2 = new Evento(null, "Workshop Microservices", "Aprenda na prática", LocalDateTime.now().plusDays(5), StatusEvento.ESGOTADO, org2, loc2);
            Evento ev3 = new Evento(null, "Java Legacy Conference", "Mantendo sistemas antigos", LocalDateTime.now().minusDays(10), StatusEvento.ENCERRADO, org1, loc1);
            eventoRepository.saveAll(Arrays.asList(ev1, ev2, ev3));


            Participante p1 = new Participante(null, "Carlos Silva", "carlos.silva@gmail.com", "11122233344", null);
            Participante p2 = new Participante(null, "Ana Souza", "ana.souza@hotmail.com", "55566677788", null);
            Participante p3 = new Participante(null, "Roberto Dev", "beto@outlook.com", "99988877700", null);
            participanteRepository.saveAll(Arrays.asList(p1, p2, p3));


            Ingresso ing1 = new Ingresso(null, LocalDateTime.now().minusDays(2), new BigDecimal("150.00"), TipoIngresso.VIP, ev1, p1);


            Ingresso ing2 = new Ingresso(null, LocalDateTime.now().minusDays(1), new BigDecimal("80.00"), TipoIngresso.PISTA, ev1, p2);


            Ingresso ing3 = new Ingresso(null, LocalDateTime.now().minusDays(5), new BigDecimal("50.00"), TipoIngresso.MEIA_ENTRADA, ev2, p3);

            ingressoRepository.saveAll(Arrays.asList(ing1, ing2, ing3));

            System.out.println("Carga de dados Mock finalizada com sucesso!");
        };
    }
}