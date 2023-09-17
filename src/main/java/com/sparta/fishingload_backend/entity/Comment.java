package com.sparta.fishingload_backend.entity;

import com.sparta.fishingload_backend.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false, length = 500)
    private String comment;

    @Column(name="account_id",nullable = false)
    private String accountId;

    @Column(name = "commentlike", nullable = false)
    private int commentLike = 0;

    @Column(name = "comment_use", nullable = false)
    private boolean commentUse = true;

    public Comment(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

}
