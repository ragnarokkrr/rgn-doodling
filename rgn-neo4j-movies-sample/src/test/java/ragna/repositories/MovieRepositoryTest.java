package ragna.repositories;


import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ragna.domain.Movie;
import ragna.domain.Person;
import ragna.domain.Role;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        Movie matrix = new Movie("The Matrix", 1999, "Welcome to the Real Workd");

        movieRepository.save(matrix);

        Person keanu = new Person("Keanu Reeves",1964);

        personRepository.save(keanu);

        Role neo = new Role(matrix, keanu);
        neo.addRoleName("Neo");

        matrix.addRole(neo);

        movieRepository.save(matrix);

    }

    @Test
    public void findByTitle() {
        String title = "The Matrix";
        Movie result = movieRepository.findByTitle(title);
        assertNotNull(result);
        assertEquals(1999, result.getReleased());
    }


    @Test
    public void testFindByTitleContaining(){
        String title = "*Matrix*";
        Collection<Movie> result = movieRepository.findByTitleLike(title);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGraph() {
        Collection<Movie> graph = movieRepository.graph(5);
        assertEquals(1, graph.size());

        Movie movie = graph.iterator().next();

        assertEquals(1, movie.getRoles().size());
        assertEquals("The Matrix", movie.getTitle());
        assertEquals("Keanu Reeves", movie.getRoles().iterator().next().getPerson().getName());
    }
}
