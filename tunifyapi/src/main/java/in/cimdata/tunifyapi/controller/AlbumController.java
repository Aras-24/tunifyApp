package in.cimdata.tunifyapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.cimdata.tunifyapi.dto.AlbumListResponse;
import in.cimdata.tunifyapi.dto.AlbumRequest;
import in.cimdata.tunifyapi.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<?> addAlbum(@RequestPart("request") String request, @RequestPart("file") MultipartFile file){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AlbumRequest albumRequest = objectMapper.readValue(request, AlbumRequest.class);
            albumRequest.setImageFile(file);
           return ResponseEntity.status(HttpStatus.CREATED).body(albumService.addAlbum(albumRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping
    public ResponseEntity<?> listAlbums(){
        try {
            return ResponseEntity.ok(albumService.getAllAlbums());
        } catch (Exception e) {
            e.printStackTrace(); //  zeigt den Fehler in Terminal
            return ResponseEntity.ok(new AlbumListResponse(false,null));
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable String id) {
        try {
            albumService.removeAlbum(id);
            return ResponseEntity.noContent().build(); // 204 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Album nicht gefunden");
        }
    }


}