package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Getter;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;

    public String getTime(){
        return MyDateUtil.timestampFormat(createdAt);
    }
}
