-- create the table PPSNS
CREATE TABLE PPSNS (
    USERNAME name,
    PPSN varchar(255)
);

-- insert the data
INSERT INTO PPSNS (USERNAME, PPSN)
VALUES ('Alice', 'Alice-ppsn'),
       ('Bob', 'Bob-ppsn'),
       ('Connie', 'Connie-ppsn'),
       ('Dave', 'Dave-ppsn');

-- create the two group roles
CREATE ROLE PADMINS;
CREATE ROLE PUSERS;

-- add Alice
GRANT PADMINS TO Alice;

-- add the other three users
GRANT PUSERS TO Bob;
GRANT PUSERS TO Connie;
GRANT PUSERS TO Dave;

-- grant read-only access
GRANT SELECT ON PPSNS TO PUSERS;

-- create a row-level security policy
CREATE POLICY ppsn_users_policy ON PPSNS
FOR SELECT
TO PUSERS
USING (USERNAME = current_user);

-- grant the PADMINS role update and delete
GRANT UPDATE ON PPSNS TO PADMINS;
GRANT DELETE ON PPSNS TO PADMINS;

-- create another policy that only allows PADMINS to modify rows belonging to them
CREATE POLICY ppsn_admins_policy ON PPSNS
FOR UPDATE, DELETE
TO PADMINS
USING (USERNAME = current_user);

-- grant PADMINS select privileges to allow them to see all rows in the table
GRANT SELECT ON PPSNS TO PADMINS;