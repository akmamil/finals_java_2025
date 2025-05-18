package project.squid_game_finals.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import project.squid_game_finals.entity.Player;
import project.squid_game_finals.enums.PlayerStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class PlayerDAOImpl implements PlayerDAO {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(Player player) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(player);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Optional<Player> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Player player = session.get(Player.class, id);
            return Optional.ofNullable(player);
        }
    }


    @Override
    public List<Player> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Player", Player.class).list();
        }
    }

    @Override
    public void update(Player player) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(player);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Player player = session.get(Player.class, id);
            if (player != null) {
                session.remove(player);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Player findByIdWithRoundResults(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT p FROM Player p LEFT JOIN FETCH p.roundResults WHERE p.id = :id",
                            Player.class
                    ).setParameter("id", id)
                    .uniqueResult();
        }
    }


    // HQL-запроса
    @Override
    public List<Player> findByStatus(PlayerStatus status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Player where status = :status", Player.class)
                    .setParameter("status", status)
                    .list();
        }
    }

    // Native SQL
    @Override
    public List<Player> findAllWithNativeSQL() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNativeQuery("SELECT * FROM player", Player.class).list();
        }
    }

    // Criteria API
    @Override
    public List<Player> findByBalanceGreaterThan(BigDecimal amount) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Player> cq = cb.createQuery(Player.class);
            Root<Player> root = cq.from(Player.class);
            cq.select(root).where(cb.greaterThanOrEqualTo(root.get("balance"), amount));
            return session.createQuery(cq).getResultList();
        }
    }

    @Override
    public Long countByStatus(PlayerStatus status) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select count(p) from Player p where p.status = :status";
            return session.createQuery(hql, Long.class)
                    .setParameter("status", status)
                    .uniqueResult();
        }
    }
}