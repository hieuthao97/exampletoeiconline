//core common tiện ích dùng chung
package vn.myclass.core.common.utils;
//import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
             //final cố định giá trị sessionFactory kể cả class khác trỏ vô
    private static final SessionFactory sessionFactory = buildSessionFactory();

           //tạo sessionfactory từ hibernate.cfg.xml
    private static SessionFactory buildSessionFactory() {
        try {
            //trả về một sessionfactory được load từ hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Initial session Factory fail"+e);
            throw new ExceptionInInitializerError(e);
        }
        }
        //lớp nào muốn gọi sessionfactory để tạo 1 đối tượng session thì trỏ vô method này
    public static SessionFactory getSessionFactory () {
        return sessionFactory;
    }


}

