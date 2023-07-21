import pandas as pd
import json

df = pd.read_csv('bestsellers.csv', sep=',', delimiter=None, encoding='latin-1')
bkorder = df[['Author']].drop_duplicates()

def writeOrderfile(doclist, outfile):
    file = open(outfile, 'w')
    rec = 'use ("AmazonBestSellers");\n'  # Database name
    file.write(rec)
    for r in doclist[['Author']].itertuples(index=False):
        theserows = (df[(df['Author'] == r[0])])  # Sort by Author
        agginfo = theserows[['Author']].drop_duplicates()
        tc = theserows[['Name', 'Price', 'Year', 'Genre', 'Reviews', 'UserRating']]  # Selecting which data to add to the dict
        entries = json.dumps({"Author": agginfo['Author'].values[0],  # We will only create a new document for each unique Author
                            "Books": tc.to_dict('records')  # Dictionary containing all books/details by Author
                            })
        rec = 'db.datav2.insertOne(' + entries + ');\n'  # Insert into this collection
        file.write(rec)
    file.close()
    return()

writeOrderfile(bkorder, 'datav2.mongodb')
