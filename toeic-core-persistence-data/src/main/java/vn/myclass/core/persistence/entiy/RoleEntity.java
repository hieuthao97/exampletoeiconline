package vn.myclass.core.persistence.entiy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "roleid")
    private Integer roleId;
    @Column(name = "name")
    private String name;

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    //trùng với cái khai báo bên user(private RoleEntity role)
    //cần khai báo 1 cái list user
    //mappedBy định nghĩa 1 cái role sẽ có 1 ds user tương ứng
    //eager khi truy vẫn 1 role:admin nó sẽ lấy cả list:user 1000rows tương ứng
    //lazy truy vấn role:admin thì lấy mỗi role:admin
    //và khi gọi ds role.userEntityList thì mới lấy ra ds
    @OneToMany(mappedBy = "roleEntity",fetch = FetchType.LAZY)
    private List<UserEntity> userEntityList;
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
