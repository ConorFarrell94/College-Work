#!/usr/bin/env python
# coding: utf-8

# Run the program CreateBikeStationDB to create the database dubbikes.db.  This program opens the SQlite database dubbikes.db that was created by CreateBikeStationDB for querying.  When you have finished writing and running your queries, close the database.
# 
#  ### Things to note:
#  
#   - The SQL query should be enclosed in pd.read_sql_query("    ",conn)
#   - If you are not using Jupyter Notebook, you will need it to be in a PRINT statement
#       - print(pd.read_sql_query("    ",conn)) where conn is the connection.
#   - Differences from Oracle SQL:
#       - use the word except instead of minus
#       - limit 3 limits the number of rows returned to 3.
# 
#  ### Remember:
#  
#   - Although this data is in a database, it is not normalized or validated and there are no keys.

# #  To do:
# 
# Write queries to solve the following:
# 
#  - 	Display the first two rows of the usage table.
#  -	Determine keys:
#    -	Station table:
#       - Display the number of rows in the station table.
#       - Display the number of distinct values for a column or set of columns you think could be act as a primary key to the station table.
#    -	Usage table:
#       - Display the number of rows in the usage table.
#       -	Display the number of distinct values for a column or set of columns you think could be act as a primary key to the usage table
#  - 	Join columns:
#    - Which column(s) would you pick  to join the tables?
#    - Are all of the values in the usage table for every join column value in the station table?
#    - 	Are all of the values in the station table for every join column value in the usage table?
#  -	 Missing / unmatched values:
#    - Write a query to list the join column(s) values in the station table that are not in the usage table, if any.
#    - Write a query to list the join column(s) values in the usage table that are not in the station table, if any.
#    
#  - Queries:
#      - return the station name, latitude, longitude and count of times recorded for every station beginning with the letter S in the usage table.
#      - return the names of stations that are in the usage table, but not in the station table
#      - return the names of stations that are in the station table, but not in the usage table
#      - return the count of station names that appear in both the station table and the usage table.
# 

# In[1]:


import sqlite3 as sql
import pandas as pd

conn = sql.connect('dubbikes.db')
cursor = conn.cursor()

# The following statement selects the top two rows from the station table.

print(pd.read_sql_query("select * from station limit 2",conn))

# The following statement selects the top two rows from the usage table.

print(pd.read_sql_query("select * from usage limit 2",conn))


# Put your code in here

#  Don't run the next line until you're finished.
conn.close()

