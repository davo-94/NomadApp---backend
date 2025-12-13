package cl.vasquez.nomadapp_backend.service;

import cl.vasquez.nomadapp_backend.model.Post;
import cl.vasquez.nomadapp_backend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElse(null);
    }
    public Post updatePost(Long id, Post updated) {
        return postRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setDescription(updated.getDescription());
                    existing.setDate(updated.getDate());
                    return postRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
