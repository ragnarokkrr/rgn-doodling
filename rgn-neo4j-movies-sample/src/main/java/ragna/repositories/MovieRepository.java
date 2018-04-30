package ragna.repositories;

import java.util.Collection;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import ragna.domain.Movie;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    Movie findByTitle(@Param("title") String title);
    Collection<Movie> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:MMovie)<-[r:ACTED_IN]-(a:MPerson) RETURN m,r,a LIMIT {limit}")
    Collection<Movie> graph(@Param("limit") int limit);
}
