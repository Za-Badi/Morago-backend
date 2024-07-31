-- V2__seed_data.sql

-- Insert initial roles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Insert initial categories
INSERT INTO categories (name, is_active) VALUES ('Consultant', true);

-- Insert initial themes
INSERT INTO themes (name, price, night_price, is_popular, is_active, categories_id)
VALUES ('Law', 10.00, 15.00, true, true, 1);

-- Insert initial users
INSERT INTO users (phone, password, first_name, last_name, balance, is_active, is_debtor, on_boarding_status)
VALUES ('1234567890', 'password', 'Admin', 'User', 0.0, true, false, 0);

-- Insert user roles
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);

-- Insert initial consultant_profiles
INSERT INTO consultant_profiles (date_of_birth, email, is_available, is_online)
VALUES ('1980-01-01', 'consultant@example.com', true, true);

-- Insert initial translator_profiles
INSERT INTO translator_profiles (date_of_birth, email, is_available, is_online, level_of_korean)
VALUES ('1990-01-01', 'translator@example.com', true, true, 'Advanced');

-- Insert initial app_versions
INSERT INTO app_versions (platform, min, latest)
VALUES ('Android', '1.0.0', '1.2.0');

-- Insert initial files
INSERT INTO files (path, type, original_title)
VALUES ('/path/to/file1', 'image/png', 'File 1');

-- Insert initial coins
INSERT INTO coins (coin, won)
VALUES (100.0, 200.0);

-- Insert initial debtors
INSERT INTO debtors (account_holder, name_of_bank, is_paid, user_id)
VALUES ('John Doe', 'Bank of Example', false, 1);

-- Insert initial deposits
INSERT INTO deposits (account_holder, name_of_bank, coin, won, status, user_id)
VALUES ('Jane Doe', 'Bank of Example', 50.0, 100.0, 'Pending', 1);

-- Insert initial frequently_asked_questions
INSERT INTO frequently_asked_questions (question, answer, category)
VALUES ('What is this?', 'This is a FAQ entry.', 'General');

-- Insert initial languages
INSERT INTO languages (name)
VALUES ('English');

-- Insert initial notifications
INSERT INTO notification (title, text, date, time, user_id)
VALUES ('Welcome', 'Welcome to the system!', CURDATE(), CURTIME(), 1);

-- Insert initial password_resets
INSERT INTO password_resets (phone, token, reset_code)
VALUES ('1234567890', 'token123', 123456);

-- Insert initial ratings
INSERT INTO ratings (who_user_id, to_whom_user_id, grade, file_id)
VALUES (1, 1, 5.0, 1);

-- Insert initial translator_languages
INSERT INTO translator_languages (translator_id, language_id)
VALUES (1, 1);

-- Insert initial translator_themes
INSERT INTO translator_themes (translator_id, theme_id)
VALUES (1, 1);

-- Insert initial consultant_languages
INSERT INTO consultant_languages (consultant_id, language_id)
VALUES (1, 1);

-- Insert initial consultant_themes
INSERT INTO consultant_themes (consultant_id, theme_id)
VALUES (1, 1);

-- Insert initial user_profiles
INSERT INTO user_profiles (is_free_call_made)
VALUES (false);

-- Insert initial withdrawals
INSERT INTO withdrawals (account_number, account_holder, sum, status, user_id)
VALUES ('123456789', 'John Doe', 100.0, 'Pending', 1);

-- Insert initial calls
INSERT INTO calls (caller_id, recipient_id, consultant_id, duration, status, sum, commission, translator_has_rated, consultant_has_rated, user_has_rated, file_id, theme_id)
VALUES (1, 1, 1, 30, 'Completed', 50.0, 5.0, false, false, false, 1, 1);
