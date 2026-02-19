package in.cimdata.tunifyapi.repository;

import in.cimdata.tunifyapi.document.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {
}
