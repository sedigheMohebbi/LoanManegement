package dataacceess;

import model.LoanType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class LoanTypeCRUD {
    public static LoanType saveLoanType(LoanType loanType){
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(loanType);
            transaction.commit();
        }
        finally {
            session.close();
        }
        return loanType;
    }
}
