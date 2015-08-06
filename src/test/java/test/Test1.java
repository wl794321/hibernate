package test;

import entry.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import util.HibernateUtils;


/**
 * Created by Mr.Wang on 2015/8/4.
 */
public class Test1 {
    @Test
    public void createTable(){
        Configuration config = new Configuration().configure();
        SchemaExport schema = new SchemaExport(config);
        schema.setFormat(true).create(true,true);

    }
    @Test
    public void add(){
        Session session =null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getSession();
            tx = session.beginTransaction();
            Student student = new Student();
            student.setName("王龙");
            student.setAge(20);

            session.save(student);

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
        }finally {
            HibernateUtils.closeSession(session);
        }

    }

}
