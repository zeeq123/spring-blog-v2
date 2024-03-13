package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@ToString
@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

//    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user; // user_id

    @CreationTimestamp // pc -> db (날짜 주입)
    private Timestamp createdAt;
}
