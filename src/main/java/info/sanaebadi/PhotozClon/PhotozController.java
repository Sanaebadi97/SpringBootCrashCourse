package info.sanaebadi.PhotozClon;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotozController {

    private Map<String, Photo> photoList = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg", null));
    }};

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return photoList.values();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = photoList.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = photoList.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //    @PostMapping("/photoz")
//    public Photo create(@RequestBody @Valid Photo photo) {
//        photo.setId(UUID.randomUUID().toString());
//        photoList.put(photo.getId(), photo);
//        return photo;
//    }
//
    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
        photoList.put(photo.getId(), photo);
        return photo;
    }
}
