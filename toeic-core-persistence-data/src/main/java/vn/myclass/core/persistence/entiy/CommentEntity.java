package vn.myclass.core.persistence.entiy;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    //commentId Tự tăng (auto increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @Column(name = "content")
    private String content;
    @Column(name = "createdate")
    private Timestamp createDate;
    @ManyToOne
    //kết nối với db
    @JoinColumn(name = "userid")
    //gọi lại class chứa userid
    private UserEntity userEntity;

    @ManyToOne
    //kết nối với db
    @JoinColumn(name = "listenguidelineid")
    //gọi lại class chứa listenguidelineid
    private ListenguidelineEntity listenguidelineEntity;
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ListenguidelineEntity getListenguidelineEntity() {
        return listenguidelineEntity;
    }

    public void setListenguidelineEntity(ListenguidelineEntity listenguidelineEntity) {
        this.listenguidelineEntity = listenguidelineEntity;
    }


}
