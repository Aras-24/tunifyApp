package in.cimdata.tunifyapi.repository;

import in.cimdata.tunifyapi.document.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
