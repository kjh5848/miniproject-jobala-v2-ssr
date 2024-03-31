package com.example.jobala.reply;

import com.example.jobala._user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ReplyResponse {

    @AllArgsConstructor
    @Data
    public static class ReplyDTO {
        private Integer id;

        //댓글의 주인여부(주인만 삭제가능)
        private Integer userId;
        private String comment;
        private String username;
        private Boolean replyOwner; //게시글 주인 여부

        public ReplyDTO(Object[] object, User sessionUser) {
            this.id = (Integer) object[0];
            this.userId = (Integer) object[1];
            this.comment = (String) object[2];
            this.username = (String) object[3];

            if (sessionUser == null) replyOwner = false;
            else replyOwner = sessionUser.getId() == userId;
        }
    }

    @Data
    public static class ReplySaveDTO {
        private Integer id;
        private Integer boardId;
        private String comment;

        public ReplySaveDTO(Reply reply) {
            this.id = reply.getId();
            this.boardId = reply.getBoard().getId();
            this.comment = reply.getComment();
        }
    }
}