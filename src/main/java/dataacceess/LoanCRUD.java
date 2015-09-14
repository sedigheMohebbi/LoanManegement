package dataacceess;


import exception.SqlException;
import model.Loan;
import model.RealCustomer;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LoanCRUD {
    private final static Logger logger = Logger.getLogger(Loan.class);
    public static Loan saveLoan(Loan loan) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(loan);
            logger.info("save loan in db");
            transaction.commit();
        } finally {
            session.close();
        }
        return loan;
    }

}
