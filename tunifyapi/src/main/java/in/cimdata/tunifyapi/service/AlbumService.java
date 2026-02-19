package in.cimdata.tunifyapi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import in.cimdata.tunifyapi.document.Album;
import in.cimdata.tunifyapi.dto.AlbumListResponse;
import in.cimdata.tunifyapi.dto.AlbumRequest;
import in.cimdata.tunifyapi.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final Cloudinary cloudinary;

    public Album addAlbum(AlbumRequest request) throws IOException {
        Map<String, Object> imageUploadResult = cloudinary.uploader().upload(request.getImageFile().getBytes(), ObjectUtils.asMap("resource_type", "image"));

        Album newAlbum = Album.builder()
                .name(request.getName())
                .desc(request.getDesc())
                .bgColour(request.getBgColor())
                .imageUrl(imageUploadResult.get("secure_url").toString())
                .build();
        return albumRepository.save(newAlbum);
    }

    public AlbumListResponse getAllAlbums(){
        return new AlbumListResponse(true,albumRepository.findAll());
    }

    public boolean removeAlbum(String id) {
        if (!albumRepository.existsById(id)) {
            throw new RuntimeException("Album nicht gefunden");
        }
        albumRepository.deleteById(id);
        return true;
    }

}
