package dataacceess;


import model.LoanType;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class LoanTypeCRUD {
    private final static Logger logger = Logger.getLogger(LoanType.class);

    public static LoanType saveLoanType(LoanType loanType) {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(loanType);
            logger.info("loan type save in db");
            transaction.commit();
        } finally {
            session.close();
        }
        return loanType;
    }

    public static List<LoanType> loadLoanTypeName() {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT loanType FROM LoanType loanType");
        List<LoanType> loanTypes = query.list();
        logger.info("load loan type from db");
        return loanTypes;
    }

    public static LoanType loadLoanType(int id) {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            LoanType loanType = session.get(LoanType.class, id);
            return loanType;
        } finally {
            session.close();
        }
    }
}
