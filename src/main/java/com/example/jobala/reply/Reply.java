package com.example.jobala.reply;

import com.example.jobala._user.User;
import com.example.jobala.board.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@NoArgsConstructor
@Entity
@Data
@Table(name = "reply_tb")
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    private String comment;
    private String username;

    @CreationTimestamp
    private Timestamp createdAt;

    @Transient
    private boolean isReplyOwner;

    @Builder
    public Reply(Integer id, User user, Board board, String comment, String username, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.comment = comment;
        this.username = username;
        this.createdAt = createdAt;
    }
}