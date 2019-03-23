//dùng những tính chất của generic
package vn.myclass.core.data.dao;

import java.io.Serializable;
import java.util.List;

//ID truyền kiểu vô vd Interger String cho các userid,commentid,...
//T type của các class(entiy) User,Role,...
//T input dũ liệu của các entity khi query lên(truy vấn)
//Serializable liên quan đến đọc ghi file
//interface phương thức ko có thân hàm
//tất cả phương thức khai báo trong interface đều là public
//interface hiển thị thuộc tính riêng mà thằng cha ko có
public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();

    T update(T entity);

    void save(T entity);

    T findByID(ID id);
    //findByProperty("roleid ","1 ","createdated","desc";
    Object [] findByProperty (String property,Object value,String sortExpression,String sortDirection);

    Integer delete(List<ID> ids);
}
