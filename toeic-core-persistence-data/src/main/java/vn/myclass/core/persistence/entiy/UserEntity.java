package vn.myclass.core.persistence.entiy;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

// @enity jpa annotion giúp class user thành 1 entity
@Entity
//map với table user
@Table(name="user")
public class UserEntity {
    //userId khóa chính

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    @Id
    //userId Tự tăng (auto increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "createdated")
    private Timestamp createDated;
    //quan hệ nhiều-1
    //khóa ngoại
    @ManyToOne
    //kết nối với db
    @JoinColumn(name = "roleid")
    //gọi lại class chứa roleid
    private RoleEntity roleEntity;

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;
    private List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Timestamp getCreateDated() {
        return createDated;
    }

    public void setCreateDated(Timestamp createDated) {
        this.createDated = createDated;
    }
}
