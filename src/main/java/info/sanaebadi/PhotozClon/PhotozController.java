package info.sanaebadi.PhotozClon;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotozController {

    private Map<String, Photo> photoList = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));
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

    @PostMapping("/photoz")
    public Photo create(@RequestBody Photo photo) {
        photo.setId(UUID.randomUUID().toString());
        photoList.put(photo.getId(), photo);
        return photo;
    }
}
