package project.squid_game_finals.dao;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import project.squid_game_finals.entity.*;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Player.class)
                    .addAnnotatedClass(Games.class)
                    .addAnnotatedClass(Guard.class)
                    .addAnnotatedClass(Round.class)
                    .addAnnotatedClass(RoundResult.class)
                    .addAnnotatedClass(VIP.class)
                    .addAnnotatedClass(VipBets.class)
                    .addAnnotatedClass(PlayerGames.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Ошибка при инициализации SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}

