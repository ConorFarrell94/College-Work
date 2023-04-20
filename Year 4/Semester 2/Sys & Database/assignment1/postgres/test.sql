create
database HumanResources;

drop table if exists payments;
drop table if exists performance_reviews;
drop table if exists employees;
drop table if exists stores;

drop sequence if exists employee_id_seq;
drop sequence if exists store_id_seq;

drop function if exists public.add_new_employee();

drop role if exists employee;
drop role if exists hrsraff;

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
drop user if exists jessica;
drop user if exists kevin;
drop user if exists linda;
drop user if exists michael;
drop user if exists nancy;
drop user if exists oliver;
drop user if exists pamela;
drop user if exists quentin;
drop user if exists rachel;
drop user if exists steven;

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
  employee_id INT PRIMARY KEY REFERENCES employees(employee_id),
  text TEXT,
  rating INT,
  bonus INT
);

CREATE OR REPLACE FUNCTION add_new_employee() RETURNS TRIGGER AS $$
DECLARE
  store_count INTEGER;
  random_store_id INTEGER;
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
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insert_employee_trigger
AFTER INSERT ON employees
FOR EACH ROW
EXECUTE FUNCTION add_new_employee();

INSERT INTO stores (location)
VALUES ('New York');
INSERT INTO stores (location)
VALUES ('Los Angeles');
INSERT INTO stores (location)
VALUES ('Chicago');

CREATE ROLE employee;
CREATE USER alex WITH PASSWORD 'password123' ROLE employee;
CREATE USER mark WITH PASSWORD 'password123' ROLE employee;
CREATE USER catherine WITH PASSWORD 'password123' ROLE employee;
CREATE USER david WITH PASSWORD 'password123' ROLE employee;
CREATE USER emily WITH PASSWORD 'password123' ROLE employee;
CREATE USER frank WITH PASSWORD 'password123' ROLE employee;
CREATE USER grace WITH PASSWORD 'password123' ROLE employee;
CREATE USER henry WITH PASSWORD 'password123' ROLE employee;
CREATE USER isabella WITH PASSWORD 'password123' ROLE employee;
CREATE USER jack WITH PASSWORD 'password123' ROLE employee;

INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Alex', '2022-06-01', 'Marketing Manager', 987654321, 120000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Mark', '2022-08-15', 'Sales Representative', 234567890, 80000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Catherine', '2021-12-31', 'Sales Manager', 345678901, 130000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('David', '2023-02-01', 'IT Support Specialist', 456789012, 60000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Emily', '2022-03-15', 'Graphic Designer', 567890123, 75000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Frank', '2023-01-01', 'Web Developer', 678901234, 90000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Grace', '2021-05-15', 'Accountant', 789012345, 95000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Henry', '2022-09-01', 'Customer Service Representative', 890123456, 50000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Isabella', '2023-03-01', 'Product Manager', 901234567, 140000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Jack', '2021-10-15', 'Operations Manager', 123450987, 110000);

CREATE ROLE hrsraff;
CREATE USER jessica WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER kevin WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER linda WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER michael WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER nancy WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER oliver WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER pamela WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER quentin WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER rachel WITH PASSWORD 'password123' ROLE hrsraff;
CREATE USER steven WITH PASSWORD 'password123' ROLE hrsraff;

INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Jessica', '2023-01-15', 'Employment Manager', 234567890, 65000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Kevin', '2022-07-01', 'Recruiter', 345678901, 120000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Linda', '2021-11-30', 'Recruiter', 456789012, 130000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Michael', '2023-02-15', 'HR Analyst', 567890123, 55000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Nancy', '2022-04-01', 'Staff Coordinator', 678901234, 80000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Oliver', '2023-05-15', 'Employee Relation Manager', 789012345, 100000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Pamela', '2021-06-30', 'Recruitment manager', 890123456, 140000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Quentin', '2022-08-01', 'Associate Executive HR', 901234567, 75000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Rachel', '2023-03-01', 'HR Analyst', 123450987, 90000);
INSERT INTO employees (name, date_joined, position, ssn, salary)
VALUES ('Steven', '2021-09-15', 'HR Representative', 234509876, 60000);

