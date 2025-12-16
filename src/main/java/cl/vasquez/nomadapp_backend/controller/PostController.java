package cl.vasquez.nomadapp_backend.controller;

import cl.vasquez.nomadapp_backend.model.Post;
import cl.vasquez.nomadapp_backend.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Crear post (sin imágenes)
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    // Subir imágenes a un post
    @PostMapping(
            value = "/{id}/images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<List<String>> uploadImages(
            @PathVariable Long id,
            @RequestPart("files") List<MultipartFile> files
    ) throws IOException {

        Post post = postService.findById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            String ext = Objects.requireNonNull(file.getOriginalFilename())
                    .substring(file.getOriginalFilename().lastIndexOf("."));

            String filename = UUID.randomUUID() + ext;

            Path target = Paths.get("uploads").resolve(filename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            String url = "http://10.138.135.65:8082/uploads/" + filename;
            urls.add(url);
        }

        post.getImageUrls().addAll(urls);
        postService.savePost(post);

        return ResponseEntity.ok(urls);
    }

    // Listar posts
    @GetMapping
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }

    // Eliminar post
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    // Actualizar post
    @PutMapping("/{id}")
    public Post updatePost(
            @PathVariable Long id,
            @RequestBody Post post
    ) {
        return postService.updatePost(id, post);
    }
}
