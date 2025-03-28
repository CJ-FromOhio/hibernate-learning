package hezix.org.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "receiver")
@Builder
public class Payment implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer amount;

    @ManyToOne()
    @JoinColumn(name = "receiver_id")
    private User receiver;
}
