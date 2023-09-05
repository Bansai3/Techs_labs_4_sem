package utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {
    private Session session;
    private Transaction transaction;

    public Session OpenTransactionSection() {
        session = OpenSection();
        transaction = session.beginTransaction();
        return session;
    }

    public void CloseTransactionSection() {
        transaction.commit();
        CloseSession();
    }

    private Session OpenSection() {
        return HibernateUtil.GetSessionFactory().openSession();
    }

    private void CloseSession() {
        session.close();
    }

    public Session getSession() {
        return this.session;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }
}
