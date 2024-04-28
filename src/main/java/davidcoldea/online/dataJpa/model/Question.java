package davidcoldea.online.dataJpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String questionText;

    @NotBlank
    private String subject;

    @NotBlank
    private String questionType;

    @ElementCollection
    private List<String> choices;

    @ElementCollection
    private List<String> correctAnswers;
}
