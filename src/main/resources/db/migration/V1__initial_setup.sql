-- V1__initial_setup.sql
-- Create table files
CREATE TABLE files (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       path VARCHAR(255),
                       type VARCHAR(255),
                       original_title VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table roles
CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table categories
CREATE TABLE categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            is_active BOOLEAN,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table languages
CREATE TABLE languages (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           name VARCHAR(200),
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table themes
CREATE TABLE themes (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255),
                        korean_title VARCHAR(255),
                        price DECIMAL(10, 2),
                        night_price DECIMAL(10, 2),
                        description TEXT,
                        is_popular BOOLEAN,
                        is_active BOOLEAN,
                        icon_id BIGINT,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        categories_id BIGINT,
                        FOREIGN KEY (icon_id) REFERENCES files(id),
                        FOREIGN KEY (categories_id) REFERENCES categories(id)
);

-- Create table translator_profiles
CREATE TABLE translator_profiles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     date_of_birth VARCHAR(50),
                                     email VARCHAR(255),
                                     is_available BOOLEAN,
                                     is_online BOOLEAN,
                                     level_of_korean VARCHAR(200),
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table consultant_profiles
CREATE TABLE consultant_profiles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     date_of_birth VARCHAR(50),
                                     email VARCHAR(255),
                                     is_available BOOLEAN,
                                     is_online BOOLEAN,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table user_profiles
CREATE TABLE user_profiles (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               is_free_call_made BOOLEAN,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table users
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       phone VARCHAR(100) NOT NULL,
                       password VARCHAR(255),
                       first_name VARCHAR(200),
                       last_name VARCHAR(200),
                       balance DOUBLE,
                       fcm_token VARCHAR(255),
                       apn_token VARCHAR(255),
                       ratings DOUBLE,
                       total_ratings INT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       status VARCHAR(50),
                       is_active BOOLEAN,
                       is_debtor BOOLEAN,
                       on_boarding_status INT,
                       image_id BIGINT,
                       translator_profile_id BIGINT,
                       consultant_profile_id BIGINT,
                       user_profile_id BIGINT,
                       FOREIGN KEY (image_id) REFERENCES files(id),
                       FOREIGN KEY (translator_profile_id) REFERENCES translator_profiles(id),
                       FOREIGN KEY (consultant_profile_id) REFERENCES consultant_profiles(id),
                       FOREIGN KEY (user_profile_id) REFERENCES user_profiles(id)
);

-- Create table coins
CREATE TABLE coins (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       coin DOUBLE NOT NULL,
                       won DOUBLE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table app_versions
CREATE TABLE app_versions (
                              platform VARCHAR(50) PRIMARY KEY,
                              min VARCHAR(255) NOT NULL,
                              latest VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table consultant_languages
CREATE TABLE consultant_languages (
                                      consultant_id BIGINT NOT NULL,
                                      language_id BIGINT NOT NULL,
                                      PRIMARY KEY (consultant_id, language_id),
                                      FOREIGN KEY (consultant_id) REFERENCES consultant_profiles(id),
                                      FOREIGN KEY (language_id) REFERENCES languages(id)
);

-- Create table consultant_themes
CREATE TABLE consultant_themes (
                                   consultant_id BIGINT NOT NULL,
                                   theme_id BIGINT NOT NULL,
                                   PRIMARY KEY (consultant_id, theme_id),
                                   FOREIGN KEY (consultant_id) REFERENCES consultant_profiles(id),
                                   FOREIGN KEY (theme_id) REFERENCES themes(id)
);

-- Create table debtors
CREATE TABLE debtors (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         account_holder VARCHAR(200),
                         name_of_bank VARCHAR(200),
                         is_paid BOOLEAN,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         user_id BIGINT NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create table deposits
CREATE TABLE deposits (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          account_holder VARCHAR(50),
                          name_of_bank VARCHAR(50),
                          coin DOUBLE,
                          won DOUBLE,
                          status VARCHAR(50),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          user_id BIGINT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create table frequently_asked_questions
CREATE TABLE frequently_asked_questions (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            question VARCHAR(255),
                                            answer VARCHAR(255),
                                            category VARCHAR(50),
                                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table notification
CREATE TABLE notification (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              title VARCHAR(200),
                              text VARCHAR(100),
                              date DATE,
                              time TIME,
                              user_id BIGINT NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create table password_resets
CREATE TABLE password_resets (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 phone VARCHAR(255),
                                 token VARCHAR(255),
                                 reset_code INT,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table ratings
CREATE TABLE ratings (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         who_user_id BIGINT NOT NULL,
                         to_whom_user_id BIGINT NOT NULL,
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP,
                         grade DOUBLE,
                         file_id BIGINT,
                         FOREIGN KEY (who_user_id) REFERENCES users(id),
                         FOREIGN KEY (to_whom_user_id) REFERENCES users(id),
                         FOREIGN KEY (file_id) REFERENCES files(id)
);

-- Create table translator_languages
CREATE TABLE translator_languages (
                                      translator_id BIGINT NOT NULL,
                                      language_id BIGINT NOT NULL,
                                      PRIMARY KEY (translator_id, language_id),
                                      FOREIGN KEY (translator_id) REFERENCES translator_profiles(id),
                                      FOREIGN KEY (language_id) REFERENCES languages(id)
);

-- Create table translator_themes
CREATE TABLE translator_themes (
                                   translator_id BIGINT NOT NULL,
                                   theme_id BIGINT NOT NULL,
                                   PRIMARY KEY (translator_id, theme_id),
                                   FOREIGN KEY (translator_id) REFERENCES translator_profiles(id),
                                   FOREIGN KEY (theme_id) REFERENCES themes(id)
);

-- Create table user_roles
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Create table withdrawals
CREATE TABLE withdrawals (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             account_number VARCHAR(200),
                             account_holder VARCHAR(200),
                             sum FLOAT,
                             status VARCHAR(50),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             user_id BIGINT NOT NULL,
                             FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create table calls
CREATE TABLE calls (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       caller_id BIGINT NOT NULL,
                       recipient_id BIGINT NOT NULL,
                       consultant_id BIGINT,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP,
                       duration INT,
                       status VARCHAR(50),
                       sum DOUBLE,
                       commission DOUBLE,
                       translator_has_rated BOOLEAN,
                       consultant_has_rated BOOLEAN,
                       user_has_rated BOOLEAN,
                       file_id BIGINT,
                       theme_id BIGINT,
                       FOREIGN KEY (caller_id) REFERENCES users(id),
                       FOREIGN KEY (recipient_id) REFERENCES users(id),
                       FOREIGN KEY (consultant_id) REFERENCES users(id),
                       FOREIGN KEY (file_id) REFERENCES files(id),
                       FOREIGN KEY (theme_id) REFERENCES themes(id)
);
