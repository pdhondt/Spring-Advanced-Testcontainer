package be.vdab.testcontainers;

import be.vdab.testcontainers.domain.Persoon;
import be.vdab.testcontainers.repositories.PersoonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
@Testcontainers
@DataJpaTest
@Sql("/insertPersonen.sql")
public class PersoonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final PersoonRepository repository;
    @Container
    private static final MySQLContainer mySQL =
            new MySQLContainer("mysql:latest")
                    .withDatabaseName("testcontainers")
                    .withUsername("cursist")
                    .withPassword("cursist");

    public PersoonRepositoryTest(PersoonRepository repository) {
        this.repository = repository;
    }
    @DynamicPropertySource
    private static void source(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQL::getJdbcUrl);
    }
    @Test
    void erIs1PersoonEnDatIsJoe() {
        assertThat(repository.findAll())
                .singleElement()
                .extracting(Persoon::getVoornaam)
                .isEqualTo("Joe");
    }
}
