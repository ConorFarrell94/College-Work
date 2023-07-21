-- create
-- database HumanResources;

DROP VIEW IF EXISTS alex, mark, catherine, david, emily, frank, grace, henry, isabella, jack, public."New York", public."Los Angeles", public."Chicago" CASCADE;
DROP TABLE IF EXISTS payments, performance_reviews, employees, stores CASCADE;
DROP SEQUENCE IF EXISTS employee_id_seq, store_id_seq CASCADE;
DROP FUNCTION IF EXISTS public.add_new_employee(), public.create_store_employees_view(), public.delete_old_rows() CASCADE;

drop index if exists employees_store_id_idx;
drop index if exists payments_employee_id_idx;
drop index if exists performance_reviews_employee_id_idx;

drop user if exists alex;
drop user if exists mark;
drop user if exists catherine;
drop user if exists david;
drop user if exists emily;
drop user if exists frank;
drop user if exists grace;
drop user if exists henry;
drop user if exists isabella;
drop user if exists jack;

-- create 2 sequences for employee_id and store_id
CREATE SEQUENCE employee_id_seq START 1;
CREATE SEQUENCE store_id_seq START 1;

CREATE TABLE stores (
  store_id SERIAL PRIMARY KEY,
  location VARCHAR,
  employee_count INT DEFAULT 0
);

CREATE TABLE employees (
  employee_id SERIAL PRIMARY KEY,
  name VARCHAR,
  date_joined DATE,
  position VARCHAR,
  ssn BIGINT,
  salary INT,
  store_id INT REFERENCES stores(store_id)
);

CREATE TABLE payments (
  employee_id INT PRIMARY KEY REFERENCES employees(employee_id),
  payment_type VARCHAR,
  bank_number BIGINT,
  status VARCHAR
);

CREATE TABLE performance_reviews (
  employee_id INT REFERENCES employees(employee_id),
  text TEXT,
  rating INT,
  bonus INT,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
)
PARTITION BY RANGE(EXTRACT(YEAR FROM created_at));

-- Create the child tables for each year
CREATE TABLE performance_reviews_2020 PARTITION OF performance_reviews
  FOR VALUES FROM (2020) TO (2021);

CREATE TABLE performance_reviews_2021 PARTITION OF performance_reviews
  FOR VALUES FROM (2021) TO (2022);

CREATE TABLE performance_reviews_2022 PARTITION OF performance_reviews
  FOR VALUES FROM (2022) TO (2023);

CREATE TABLE performance_reviews_2023 PARTITION OF performance_reviews
  FOR VALUES FROM (2023) TO (2024);

-- this function adds a new employee to the database and creates a new user for them
-- the new employee is assigned to a random store and a view is created for them to view their
-- information. if the new employee is an HR employee, they are given access to all tables in
-- the database and can view all employee information
CREATE OR REPLACE FUNCTION add_new_employee() RETURNS TRIGGER AS $$
DECLARE
  store_count INTEGER;
  random_store_id INTEGER;
  new_username VARCHAR;
BEGIN
  -- get the number of stores
  SELECT COUNT(*) INTO store_count FROM stores;
  -- generate a random store id
  random_store_id := (floor(random() * store_count) + 1);
  -- update the new employee with the random store id
  UPDATE employees SET store_id = random_store_id WHERE employee_id = NEW.employee_id;
  -- insert the new employee into the payments and performance_reviews tables
  INSERT INTO payments (employee_id)
  VALUES (NEW.employee_id);
  INSERT INTO performance_reviews (employee_id)
  VALUES (NEW.employee_id);
  -- increment the employee_count of the corresponding store
  UPDATE stores SET employee_count = employee_count + 1 WHERE store_id = random_store_id;
  -- create new database user
  new_username := NEW.name;
  EXECUTE 'CREATE USER ' || quote_ident(new_username) || ' WITH PASSWORD ''password''';
  IF NEW.position = 'HR' THEN
    EXECUTE 'GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ' || quote_ident(new_username);
  ELSE
    -- create a view for non-HR employees
    EXECUTE 'CREATE VIEW ' || quote_ident(new_username) || ' AS
             SELECT e.employee_id, e.name, e.date_joined, e.position, e.ssn, e.salary,
                    p.payment_type, p.bank_number, p.status,
                    pr.text, pr.rating, pr.bonus
             FROM employees e
             LEFT JOIN payments p ON e.employee_id = p.employee_id
             LEFT JOIN performance_reviews pr ON e.employee_id = pr.employee_id
             WHERE e.employee_id = ' || NEW.employee_id;
    EXECUTE 'GRANT SELECT ON ' || quote_ident(new_username) || ' TO ' || quote_ident(new_username);
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- create a trigger that calls the add_new_employee function after a new employee is added
CREATE TRIGGER insert_employee_trigger
AFTER INSERT ON employees
FOR EACH ROW
EXECUTE FUNCTION add_new_employee();

-- make a view for each store that shows the employees in that store
CREATE OR REPLACE FUNCTION create_store_employees_view() RETURNS TRIGGER AS $$
DECLARE
  store_id INTEGER;
  store_location VARCHAR;
  view_name VARCHAR;
BEGIN
  store_id := NEW.store_id;
  store_location := NEW.location;
  view_name := NEW.location;
  -- create a view for the new store
  EXECUTE 'CREATE VIEW ' || quote_ident(view_name) || ' AS
           SELECT e.employee_id, e.name
           FROM employees e
           WHERE e.store_id = ' || store_id;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- create a trigger that calls the create_store_employees_view function after a new store is added
CREATE TRIGGER create_store_view_trigger
AFTER INSERT ON stores
FOR EACH ROW
EXECUTE FUNCTION create_store_employees_view();

-- function that deletes rows from the performance_reviews table that are older than 5 years
CREATE OR REPLACE FUNCTION delete_old_rows()
    RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM performance_reviews WHERE created_at < NOW() - INTERVAL '5 years';
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- trigger that calls the delete_old_rows function after a new row is added to the table
CREATE TRIGGER delete_old_rows_trigger
    AFTER INSERT ON performance_reviews
    EXECUTE FUNCTION delete_old_rows();

-- add some stores to the database
INSERT INTO stores (location)
VALUES ('New York');
INSERT INTO stores (location)
VALUES ('Los Angeles');
INSERT INTO stores (location)
VALUES ('Chicago');

CREATE INDEX employees_store_id_idx ON employees (store_id);
CREATE INDEX payments_employee_id_idx ON payments (employee_id);
CREATE INDEX performance_reviews_employee_id_idx ON performance_reviews (employee_id);

-- add some employees to the database
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('alex', '2022-06-01', 'Employee', 987654321, 120000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('mark', '2022-08-15', 'Employee', 234567890, 80000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('catherine', '2021-12-31', 'Employee', 345678901, 130000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('david', '2023-02-01', 'Employee', 456789012, 60000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('emily', '2022-03-15', 'Employee', 567890123, 75000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('frank', '2023-01-01', 'Employee', 678901234, 90000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('grace', '2021-05-15', 'Employee', 789012345, 95000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('henry', '2022-09-01', 'HR', 890123456, 50000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('isabella', '2023-03-01', 'HR', 901234567, 140000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('jack', '2021-10-15', 'HR', 123450987, 110000);

-- insert into performance_reviews where employee_id = x
UPDATE performance_reviews SET text = 'Alex is a great employee. He is very hardworking and always gets the job done. He is a great asset to the company.', rating = 5, bonus = 10000 WHERE employee_id = 1;
UPDATE performance_reviews SET text = 'Mark is a great employee. He is very hardworking and always gets the job done. He is a great asset to the company.', rating = 4, bonus = 5000 WHERE employee_id = 2;
UPDATE performance_reviews SET text = 'Catherine is a great employee. She is very hardworking and always gets the job done. She is a great asset to the company.', rating = 3, bonus = 0 WHERE employee_id = 3;
UPDATE performance_reviews SET text = 'David needs to work on his communication skills. He often misses deadlines and needs to improve his productivity. We hope to see better results from him next year.', rating = 2, bonus = 0 WHERE employee_id = 4;
UPDATE performance_reviews SET text = 'Emily is a great employee. She is very hardworking and always gets the job done. She is a great asset to the company.', rating = 5, bonus = 10000 WHERE employee_id = 5;
UPDATE performance_reviews SET text = 'Frank has shown significant improvement in his work over the past year. However, he still needs to work on his time management skills to become more efficient.', rating = 3, bonus = 0 WHERE employee_id = 6;
UPDATE performance_reviews SET text = 'Grace is a great employee. She is very hardworking and always gets the job done. She is a great asset to the company.', rating = 4, bonus = 5000 WHERE employee_id = 7;
UPDATE performance_reviews SET text = 'Henry has a great attitude and works well with others. However, he needs to take more initiative and show more leadership in his role.', rating = 3, bonus = 0 WHERE employee_id = 8;
UPDATE performance_reviews SET text = 'Isabella is a great employee. She is very hardworking and always gets the job done. She is a great asset to the company.', rating = 5, bonus = 10000 WHERE employee_id = 9;
UPDATE performance_reviews SET text = 'Jack needs to work on his attention to detail. He often makes mistakes that could have been easily avoided. We expect better from him in the coming year.', rating = 2, bonus = 0 WHERE employee_id = 10;

-- insert into payments where employee_id = x
UPDATE payments SET payment_type = 'Direct Deposit', bank_number = 123456789, status = 'Active' WHERE employee_id = 1;
UPDATE payments SET payment_type = 'Paper Check', bank_number = 234567890, status = 'Inactive' WHERE employee_id = 2;
UPDATE payments SET payment_type = 'PayPal', bank_number = 345678901, status = 'Active' WHERE employee_id = 3;
UPDATE payments SET payment_type = 'Direct Deposit', bank_number = 456789012, status = 'Inactive' WHERE employee_id = 4;
UPDATE payments SET payment_type = 'Venmo', bank_number = 567890123, status = 'Active' WHERE employee_id = 5;
UPDATE payments SET payment_type = 'Cash', bank_number = 678901234, status = 'Active' WHERE employee_id = 6;
UPDATE payments SET payment_type = 'Paper Check', bank_number = 789012345, status = 'Active' WHERE employee_id = 7;
UPDATE payments SET payment_type = 'Direct Deposit', bank_number = 890123456, status = 'Inactive' WHERE employee_id = 8;
UPDATE payments SET payment_type = 'Direct Deposit', bank_number = 901234567, status = 'Active' WHERE employee_id = 9;
UPDATE payments SET payment_type = 'Paper Check', bank_number = 123450987, status = 'Inactive' WHERE employee_id = 10;

-- -- show all employees that work in store location x
SELECT e.name, s.location
FROM employees e
JOIN stores s ON e.store_id = s.store_id
WHERE s.location = 'Chicago';



