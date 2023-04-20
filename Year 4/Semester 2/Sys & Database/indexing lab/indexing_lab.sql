-- Credit to Markus Winand of https://use-the-index-luke.com
DROP TABLE employees;
DROP FUNCTION random_string;

-- Create an employee table, we're going to fill this with random data
-- Because the junk column is a CHAR, rather than a VARCHAR type it will
-- always take up all of the space allocated to it (1000 bytes), regardless
-- of how full the table is. This helps make the table large so that the
-- impact of indexes is more apparent
CREATE TABLE employees (
   employee_id   NUMERIC       NOT NULL,
   first_name    VARCHAR(1000) NOT NULL,
   last_name     VARCHAR(1000) NOT NULL,
   date_of_birth DATE                  ,
   phone_number  VARCHAR(1000) NOT NULL,
   junk          CHAR(1000)            ,
   CONSTRAINT employees_pk PRIMARY KEY (employee_id)
);

create index ix_employees_dob_btree on employees using btree (date_of_birth asc);
create index ix_employees_dob_hash on employees using hash (date_of_birth);
create index ix_employees_last_name on employees using btree (last_name);

--This function allows us to generate a random sring with a length between
--minlen and maxlen. This function is going to help us populate our table
--with lots of random data
CREATE FUNCTION random_string(minlen NUMERIC, maxlen NUMERIC)
RETURNS VARCHAR(1000)
AS
$$
DECLARE
  rv VARCHAR(1000) := '';
  i  INTEGER := 0;
  len INTEGER := 0;
BEGIN
  IF maxlen < 1 OR minlen < 1 OR maxlen < minlen THEN
    RETURN rv;
  END IF;

  -- Choose a random length for this string between minlen and maxlen
  len := floor(random()*(maxlen-minlen)) + minlen;

  -- Generate a random character for each character in this string
  FOR i IN 1..floor(len) LOOP
    -- chr(97) gives us 'A', we add a random number between
    -- 0 and 25 to give us an equal chance of selecting any
    -- random character.
    rv := rv || chr(97+CAST(random() * 25 AS INTEGER));
  END LOOP;
  RETURN rv;
END;
$$ LANGUAGE plpgsql;


-- Insert our data. The GENERATE_SERIES function will return all the numbers
-- between 1 and 10000, we use this number as the employee id. We're populating
-- the other columns randomly
INSERT INTO employees (employee_id,  first_name,
                       last_name,    date_of_birth, 
                       phone_number, junk)
SELECT GENERATE_SERIES
     , initcap(lower(random_string(2, 8)))
     , initcap(lower(random_string(2, 8)))
     , CURRENT_DATE - CAST(floor(random() * 365 * 10 + 40 * 365) AS NUMERIC) * INTERVAL '1 DAY'
     , CAST(floor(random() * 9000 + 1000) AS NUMERIC)
     , 'junk'
  FROM GENERATE_SERIES(1, 50000);


-- Check that our data looks how we expect it to
select * from employees limit 10;


-- We're going to update a random (but known) employee so we can query for
-- it later.
UPDATE employees 
SET first_name='JACK', 
    last_name='ONEILL'
WHERE employee_id=789;




-- The VACUUM ANALYZE tells the optimiser to gather statistics about the table
-- so it can do a better job of optimising queries
VACUUM ANALYZE employees;



explain select * from employees where employee_id = 789;

explain analyze select * from employees where employee_id = 789;


create index ix_employees_last_name on employees using btree (last_name);


set pgaudit.log = 'none';

SELECT pg_sleep(10);