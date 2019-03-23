package vn.myclass.core.data.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.myclass.core.common.constanst.CoreConstant;
import vn.myclass.core.common.utils.HibernateUtils;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

//<ID,T> là generic
public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    //khai báo biến persistenceClass
    private Class<T> persistenceClass;

    //contructor của AbstractDao
    //ParameterizedType lấy tham sốID, T vô cục
    //<ID extends Serializable,T > như là 1 cái mảng
    //getActualTypeArguments để lấy vị trí
    //ID là vị trí thứ [0], [1] là vị trí của T
    //cả cục để lấy ra tên entiy User,...
    public AbstractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    //truy vấn có thể gọi trực tiếp tên persistenceClass
    //sql="select * from"+persistenceClass+""
    public String getPersistenceClassName() {
        return this.persistenceClass.getSimpleName();
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        //Transaction transaction=null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //HQL
        try {

            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            // query để excute câu truy vấn HQL
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return list;

    }

    public T update(T entity) {
        T result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }
        return result;
    }

    public void save(T entity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

    }

    /*public T findByID(ID id) {
        T result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            //truyển kiểu class persistenceClass
            result = (T) session.get(persistenceClass, id);
            if (result == null) {
                throw new ObjectNotFoundException("Not Found " + id, null);
            }
            //  transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }*/
    public T findByID(ID id) {
        T result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = (T) session.get(persistenceClass, id);
            transaction.commit();
            if (result == null) {
                throw new ObjectNotFoundException(" NOT FOUND " + id, null);
            }

        } catch (HibernateException e) {
            transaction.rollback();

            throw e;
        }/* finally {
            session.close();
        }*/
        return result;
    }

    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection) {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //size của object tổng số item
        Object totalItem = 0;
        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName());
            if (property != null && value != null) {
                sql1.append(" where ").append(property).append("= :value");
            }
            if (sortExpression != null && sortDirection != null) {
                //sortExpression sx theo ngày tạo
                //sortDirection sx theo tăng or giảm dần
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" " + (sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc"));
            }
            //thực thi chuỗi truy vấn
            Query query1 = session.createQuery(sql1.toString());
            // query1.setParameter("value", value);
            list = query1.list();
            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(getPersistenceClassName());
            if (property != null && value != null) {
                sql2.append(" where ").append(property).append("= :value");

            }
            Query query2 = session.createQuery(sql2.toString());
            if (value != null) {
                query2.setParameter("value", value);
            }

            // trả về kiểu object
            totalItem = query2.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (ID item : ids) {
                T t = (T) session.get(persistenceClass, item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } /*finally {
            session.close();
        }*/
        return count;
    }
}
