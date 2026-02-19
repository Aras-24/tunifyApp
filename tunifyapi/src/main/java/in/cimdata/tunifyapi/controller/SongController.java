package in.cimdata.tunifyapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.cimdata.tunifyapi.dto.AlbumListResponse;
import in.cimdata.tunifyapi.dto.SongListResponse;
import in.cimdata.tunifyapi.dto.SongRequest;
import in.cimdata.tunifyapi.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @PostMapping
    public ResponseEntity<?> addSong(@RequestPart("request") String requestString,
                                     @RequestPart("audio")MultipartFile audioFile,
                                     @RequestPart("image") MultipartFile imageFile){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SongRequest songRequest = objectMapper.readValue(requestString,SongRequest.class);
            songRequest.setImageFile(imageFile);
            songRequest.setAudioFile(audioFile);
            songService.addSong(songRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(songRequest));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listSongs(){
        try {
            return ResponseEntity.ok(songService.getAllSongs());
        } catch (Exception e) {
            e.printStackTrace(); //  zeigt den Fehler in Terminal
            return ResponseEntity.ok(new SongListResponse(false,null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable String id) {
        try {
            songService.removeSong(id);
            return ResponseEntity.noContent().build(); // 204 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Lied nicht gefunden");
        }
    }
}
