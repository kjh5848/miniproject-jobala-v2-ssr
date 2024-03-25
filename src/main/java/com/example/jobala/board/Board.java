package com.example.jobala.board;

import com.example.jobala._user.User;
import com.example.jobala.reply.Reply;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "board_tb")

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private Integer role; // 0 -> guest, 1 -> comp
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @OrderBy("id desc")
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();


    @Transient
    private boolean isBoardOwner;


    @Builder
    public Board(Integer id, String title, String content, Integer role, User user, Timestamp createdAt, boolean isBoardOwner) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.role = role;
        this.user = user;
        this.createdAt = createdAt;
        this.isBoardOwner = isBoardOwner;
    }
}
