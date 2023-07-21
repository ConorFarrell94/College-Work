# This program reads data from the https://data.smartdublin.ie/dataset website and stores them in a SQLite database.
# It runs 2 simple queries.

# https://data.smartdublin.ie/dataset/33ec9fe2-4957-4e9a-ab55-c5e917c7a9ab/resource/67ea095f-67ad-47f5-b8f7-044743043848/download/dublinbikes_20181001_20190101.csv

# https://data.smartdublin.ie/dataset/33ec9fe2-4957-4e9a-ab55-c5e917c7a9ab/resource/2dec86ed-76ed-47a3-ae28-646db5c5b965/download/dublin.csv

# Import sqlite 3 and pandas.  read the station data into dfstation. Print the top 5 rows.

import sqlite3 as sql
import pandas as pd
stationfile = "https://data.smartdublin.ie/dataset/33ec9fe2-4957-4e9a-ab55-c5e917c7a9ab/resource/2dec86ed-76ed-47a3-ae28-646db5c5b965/download/dublin.csv"
dfstation = pd.read_csv(stationfile, index_col=None)
print(dfstation.head())

# Read the first 10,000 rows of the usage data into dfusage, giving it headers.  drop columns we don't want and print 5 rows.

usagefile = "https://data.smartdublin.ie/dataset/33ec9fe2-4957-4e9a-ab55-c5e917c7a9ab/resource/67ea095f-67ad-47f5-b8f7-044743043848/download/dublinbikes_20181001_20190101.csv"
header = [ 'STATION_ID', 'TIME', 'LAST_UPDATED', 'NAME', 'BIKE_STANDS',
       'AVAILABLE_BIKE_STANDS', 'AVAILABLE_BIKES', 'STATUS', 'ADDRESS',
       'LATITUDE', 'LONGITUDE']
dfusage = pd.read_csv(usagefile, index_col=None, names = header, skiprows=1, nrows=10000)
dfusage.drop(columns=[ 'STATION_ID',  'LAST_UPDATED', 'LATITUDE', 'LONGITUDE'],inplace=True)
print(dfusage.head())

# Add or replace the tables station and usage to the database dubbikes.db


conn = sql.connect('dubbikes.db')
cursor = conn.cursor()
cursor.execute("Drop table if exists station")
dfstation.to_sql('station', conn, index=False)
cursor.execute("Drop table if exists usage")
dfusage.to_sql('usage', conn, index=False)

# Print the top two rows of the station table

print(pd.read_sql_query("select * from station limit 2",conn))

# Print the top two rows of the usage table

print(pd.read_sql_query("select * from usage limit 2",conn))


# When you're finished querying, close the connection.


conn.close()