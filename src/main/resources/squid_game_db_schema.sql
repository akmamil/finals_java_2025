--   SQUID GAME PROJECT DATABASE SCRIPT

-- EXPORT: pg_dump -U postgres -d squid_game_db -f squid_game_db_backup.sql
-- IMPORT: psql -U postgres -d squid_game_db -f squid_game_db_backup.sql

SET search_path TO public;

-- =====================================================
--                  ENUM TYPES
-- =====================================================

CREATE TYPE IF NOT EXISTS player_status AS ENUM ('Alive', 'Dead');
CREATE TYPE IF NOT EXISTS round_result_status AS ENUM ('Win', 'Lose');
CREATE TYPE IF NOT EXISTS game_status AS ENUM ('Upcoming', 'Ongoing', 'Finished');
CREATE TYPE IF NOT EXISTS guard_rank AS ENUM ('worker', 'soldier', 'manager');


-- =====================================================
--                  TABLES CREATION
-- =====================================================

-- Таблица Player
CREATE TABLE IF NOT EXISTS Player (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    status player_status NOT NULL,
    balance DECIMAL(10,2) DEFAULT 0.00,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Таблица Games
CREATE TABLE IF NOT EXISTS Games (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    game_date DATE NOT NULL,
    status game_status DEFAULT 'Upcoming'
    );

-- Таблица Round
CREATE TABLE IF NOT EXISTS Round (
    id SERIAL PRIMARY KEY,
    game_id INT NOT NULL,
    round_number INT NOT NULL,
    round_name VARCHAR(100) NOT NULL,
    description TEXT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE
    );

-- Таблица RoundResult
CREATE TABLE IF NOT EXISTS RoundResult (
    id SERIAL PRIMARY KEY,
    player_id INT NOT NULL,
    round_id INT NOT NULL,
    result round_result_status NOT NULL,
    FOREIGN KEY (player_id) REFERENCES Player(id) ON DELETE CASCADE,
    FOREIGN KEY (round_id) REFERENCES Round(id) ON DELETE CASCADE
    );

-- Таблица Guard
CREATE TABLE IF NOT EXISTS Guard (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rank guard_rank NOT NULL,
    assigned_game_id INT,
    FOREIGN KEY (assigned_game_id) REFERENCES Games(id) ON DELETE SET NULL
    );

-- Таблица VIP
CREATE TABLE IF NOT EXISTS VIP (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100),
    wealth DECIMAL(15,2),
    favorite_player_id INT,
    FOREIGN KEY (favorite_player_id) REFERENCES Player(id) ON DELETE SET NULL
    );

-- Таблица PlayerGames (Many-to-Many)
CREATE TABLE IF NOT EXISTS PlayerGames (
    player_id INT NOT NULL,
    game_id INT NOT NULL,
    PRIMARY KEY (player_id, game_id),
    FOREIGN KEY (player_id) REFERENCES Player(id) ON DELETE CASCADE,
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE
    );

-- Таблица VipBets
CREATE TABLE IF NOT EXISTS VipBets (
    id SERIAL PRIMARY KEY,
    vip_id INT NOT NULL,
    game_id INT NOT NULL,
    round_id INT NOT NULL,
    bet_amount DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (vip_id) REFERENCES VIP(id) ON DELETE CASCADE,
    FOREIGN KEY (game_id) REFERENCES Games(id) ON DELETE CASCADE,
    FOREIGN KEY (round_id) REFERENCES Round(id) ON DELETE CASCADE,
    UNIQUE (vip_id, round_id)
    );



-- =====================================================
--                 INDEXES
-- =====================================================
CREATE INDEX idx_player_name ON Player(name);
CREATE INDEX idx_round_game_id ON Round(game_id);
CREATE INDEX idx_result_player_round ON RoundResult(player_id, round_id);


-- =====================================================
--                 VIEW
-- =====================================================
CREATE OR REPLACE VIEW vw_alive_players AS
SELECT id, name, age, balance
FROM Player
WHERE status = 'Alive';

-- =====================================================
--                 Functions
-- =====================================================
-- Возвращает баланс игрока по ID
CREATE OR REPLACE FUNCTION get_player_balance(p_id INT)
RETURNS DECIMAL(10,2) AS $$
BEGIN
RETURN (SELECT balance FROM Player WHERE id = p_id);
END;
$$ LANGUAGE plpgsql;

-- =====================================================
--                 Triggers & Logs
-- =====================================================
-- Автоматическое логирование удалений игроков
CREATE TABLE IF NOT EXISTS PlayerDeleteLog (
                                               id SERIAL PRIMARY KEY,
                                               player_id INT,
                                               deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION log_player_delete()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO PlayerDeleteLog(player_id) VALUES (OLD.id);
RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_player_delete
    BEFORE DELETE ON Player
    FOR EACH ROW EXECUTE FUNCTION log_player_delete();



-- =====================================================
--                 DATA INSERTION
-- =====================================================

-- === Insert Players ===
INSERT INTO Player(name, age, status, balance) VALUES
                                                   ('Gi-hun', 47, 'Alive', 1000.00),
                                                   ('Sang-woo', 45, 'Dead', 0),
                                                   ('Sae-byeok', 28, 'Dead', 200.00);

-- === Insert Games ===
INSERT INTO Games(name, description, game_date, status) VALUES
                                                    ('Red Light, Green Light', 'Deadly children’s game', '2023-01-01'),
                                                    ('Tug of War', 'Team strength test', '2023-01-02'),
                                                    ('Marbles', 'Players play a game of marbles against another.', '2025-01-14', 'Finished');

-- === Insert PlayerGames ===
INSERT INTO PlayerGames(player_id, game_id) VALUES
                                                (1, 1),
                                                (1, 2),
                                                (2, 1),
                                                (2, 2),
                                                (3, 1),
                                                (3, 3);

-- === Insert VipBets ===
INSERT INTO VipBets (vip_id, game_id, round_id, bet_amount) VALUES
                                                                (1, 1, 1, 50000.00),
                                                                (2, 2, 2, 60000.00),
                                                                (3, 3, 3, 70000.00);

-- === Insert Rounds ===
INSERT INTO Round (game_id, round_number, round_name, description, start_time, end_time) VALUES
                                                                                             (1, 1, 'Start Line', 'First deadly challenge', '2025-01-10 10:00', '2025-01-10 10:30'),
                                                                                             (2, 1, 'Pull!', 'Team match', '2025-01-12 14:00', '2025-01-12 14:20'),
                                                                                             (3, 1, 'Odds or Evens', 'Marble game', '2025-01-14 11:00', '2025-01-14 11:45');

-- === Insert Guards ===
INSERT INTO Guard (name, rank, assigned_game_id) VALUES
                                                     ('Guard A', 'worker', 1),
                                                     ('Guard B', 'soldier', 2),
                                                     ('Guard C', 'manager', 3);
-- === Insert VIPs ===
INSERT INTO VIP (name, country, wealth, favorite_player_id) VALUES
                                                                ('VIP One', 'USA', 10000000.00, 1),
                                                                ('VIP Two', 'Russia', 8500000.00, 2),
                                                                ('VIP Three', 'China', 9000000.00, NULL);


-- === Insert RoundResults ===
INSERT INTO RoundResult (player_id, round_id, result) VALUES
                                                          (1, 1, 'Win'),
                                                          (2, 1, 'Win'),
                                                          (3, 1, 'Win'),

                                                          (1, 2, 'Win'),
                                                          (2, 2, 'Lose'),
                                                          (3, 2, 'Lose');
-- =====================================================
--                  SELECT QUERIES
-- =====================================================

-- Игроки и их игры
SELECT p.name AS player, g.name AS game
FROM Player p
         JOIN PlayerGames pg ON p.id = pg.player_id
         JOIN Games g ON pg.game_id = g.id;

-- Все игроки и наличие у них любимого VIP (LEFT JOIN)
SELECT p.name, v.name AS vip
FROM Player p
         LEFT JOIN VIP v ON p.id = v.favorite_player_id;

-- Кол-во игроков по статусу
SELECT status, COUNT(*) AS player_count
FROM Player
GROUP BY status;

-- Игроки "Alive", сгруппированные по балансу
SELECT balance, COUNT(*) AS alive_count
FROM Player
WHERE status = 'Alive'
GROUP BY balance;

-- Количество ставок VIP на каждую игру и раунд
SELECT game_id, round_id, COUNT(*) AS bet_count
FROM VipBets
GROUP BY game_id, round_id;

-- Статусы, где более 1 игрока
SELECT status, COUNT(*) AS cnt
FROM Player
GROUP BY status
HAVING COUNT(*) > 1;

-- =====================================================
--             TRANSACTIONS AND ROLLBACK
-- =====================================================

-- Пример транзакции
BEGIN;
UPDATE Player SET balance = balance - 500 WHERE id = 1;
UPDATE Player SET balance = balance + 500 WHERE id = 2;
COMMIT;

-- С откатом
BEGIN;
UPDATE Player SET balance = balance - 999999 WHERE id = 1;
ROLLBACK;

-- Deadlock (симуляция), нужно выполнять в разных сессиях !
BEGIN;
UPDATE Player SET balance = balance + 100 WHERE id = 1;
-- ждем...

BEGIN;
UPDATE Player SET balance = balance + 100 WHERE id = 2;
-- теперь попробуем:
UPDATE Player SET balance = balance + 100 WHERE id = 1;

UPDATE Player SET balance = balance + 100 WHERE id = 2;


-- =====================================================
--             PERFORMANCE & OPTIMIZATION
-- =====================================================

-- EXPLAIN
EXPLAIN SELECT * FROM Player WHERE balance > 1000;
EXPLAIN ANALYZE SELECT * FROM Player WHERE balance > 1000;

-- Индекс и EXPLAIN
CREATE INDEX idx_player_balance ON Player(balance);
EXPLAIN ANALYZE SELECT * FROM Player WHERE balance > 1000;

-- EXPLAIN с WHERE
EXPLAIN SELECT * FROM Games WHERE status = 'Finished' AND game_date >= '2025-01-12';

-- Первичный ключ оптимизация
EXPLAIN SELECT * FROM Player WHERE id = 1;

-- =====================================================
--            USERS, ROLES, PERMISSIONS
-- =====================================================

CREATE ROLE game_viewer;
GRANT SELECT ON Player TO game_viewer;
CREATE USER student WITH PASSWORD '12345';
GRANT game_viewer TO student;


-- =====================================================
--         SQL FUNCTIONS: COALESCE, CAST, LIMIT
-- =====================================================

-- COALESCE
SELECT name, COALESCE(favorite_player_id::TEXT, 'None') AS favorite FROM VIP;

-- CAST
SELECT name, CAST(balance AS INTEGER) AS balance_rounded FROM Player;

-- LIMIT
SELECT * FROM Player ORDER BY balance DESC LIMIT 2;
