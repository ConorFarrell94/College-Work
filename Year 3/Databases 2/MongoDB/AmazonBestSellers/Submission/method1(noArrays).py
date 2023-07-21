import os
import pandas as pd
import pymongo
import json

# This will automatically add a collection to our database, no need to create a mongodbfile first
def import_content(filepath):
    databaseClient = pymongo.MongoClient('localhost', 27017)
    database = databaseClient['AmazonBestSellers']  # mongo db name
    collection_name = 'datav1'  # mongo db collection name
    insertThis = database[collection_name]
    createDir = os.path.dirname(__file__)
    fileToRead = os.path.join(createDir, filepath)
    data = pd.read_csv(fileToRead)  # reading the csv file
    data_json = json.loads(data.to_json(orient='records'))  # converting to json file
    insertThis.insert_many(data_json)  # inserting all the data at once rather than using "insert_one"

if __name__ == "__main__":
  filepath = 'bestsellers.csv'  # pass csv file path
  import_content(filepath)