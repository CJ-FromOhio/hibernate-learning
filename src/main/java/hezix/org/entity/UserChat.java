package hezix.org.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "users_chat")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserChat extends AuditableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private User user;

    @ManyToOne()
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public void setUser(User user) {
        this.user = user;
        this.user.getUserChats().add(this);
    }

    public void setChat(Chat chat) {
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }
}
