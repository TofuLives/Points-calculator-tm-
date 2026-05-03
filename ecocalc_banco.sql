-- ============================================================
--  EcoCalc — Script de inicialização do banco de dados MySQL
--  Compatível com XAMPP (MySQL 5.7+ / MariaDB 10.x+)
--  Execute no phpMyAdmin ou via terminal: mysql -u root -p < ecocalc_banco.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS ecocalc
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ecocalc;

-- ------------------------------------------------------------
-- Tabela de usuários
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS usuarios (
    id           INT UNSIGNED    NOT NULL AUTO_INCREMENT,
    nome         VARCHAR(100)    NOT NULL,
    email        VARCHAR(150)    NOT NULL,
    senha_hash   VARCHAR(255)    NOT NULL,          -- bcrypt / password_hash()
    criado_em    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uq_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Tabela de cálculos de pegada de carbono
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS calculos (
    id                  INT UNSIGNED    NOT NULL AUTO_INCREMENT,
    usuario_id          INT UNSIGNED    NOT NULL,
    -- Transporte
    tipo_transporte     VARCHAR(50)     NOT NULL,
    km_dia              DECIMAL(8,2)    NOT NULL,
    dias_semana         TINYINT         NOT NULL,
    combustivel         VARCHAR(30)     NOT NULL,
    emissao_transporte  DECIMAL(10,4)   NOT NULL DEFAULT 0,  -- kg CO₂/mês
    -- Energia
    num_moradores       TINYINT         NOT NULL,
    valor_conta         DECIMAL(10,2)   NOT NULL,
    usa_led             TINYINT(1)      NOT NULL DEFAULT 0,
    usa_renovavel       TINYINT(1)      NOT NULL DEFAULT 0,
    emissao_energia     DECIMAL(10,4)   NOT NULL DEFAULT 0,  -- kg CO₂/mês
    -- Totais
    emissao_total       DECIMAL(10,4)   NOT NULL DEFAULT 0,  -- kg CO₂/mês
    calculado_em        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_usuario (usuario_id),
    CONSTRAINT fk_calculo_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Usuário de exemplo (senha: Teste@123)
-- Gerado com: password_hash('Teste@123', PASSWORD_BCRYPT)
-- Remova ou altere antes de usar em produção!
-- ------------------------------------------------------------
INSERT INTO usuarios (nome, email, senha_hash) VALUES (
    'Usuário Teste',
    'teste@ecocalc.com',
    '$2y$12$u5SkBpfKqTMgVd7B3hkE4.q6nINj.0Wr3RPWbHxZe3lRbC2Gfu8pC'
);
