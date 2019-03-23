package vn.myclass.core.persistence.entiy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
//map với table listenguideline
@Table(name="listenguideline")
public class ListenguidelineEntity {

        //userId khóa chính
        @Id
        //userId Tự tăng (auto increment)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer listenguidelineId;
        @Column(name = "title")
        private String title;
        @Column(name = "image")
        private String image;
        @Column(name = "content")
        private String content;
        @Column(name = "createdated")
        private Timestamp createDated;
        @Column(name = "modifieddate")
        private Timestamp modifiedDate;



    @OneToMany(mappedBy = "listenguidelineEntity",fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;
    public Integer getListenguidelineId() {
        return listenguidelineId;
    }

    public void setListenguidelineId(Integer listenguidelineId) {
        this.listenguidelineId = listenguidelineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateDated() {
        return createDated;
    }

    public void setCreateDated(Timestamp createDated) {
        this.createDated = createDated;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }
}
