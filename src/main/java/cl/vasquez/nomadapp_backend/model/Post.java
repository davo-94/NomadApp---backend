package cl.vasquez.nomadapp_backend.model;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String date;

    @ElementCollection
    @CollectionTable(
            name = "post_images",
            joinColumns = @JoinColumn(name = "post_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    //Constructores

    public Post() {}

    public Post(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    //Get&Setters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
