
import pandas as pd
import json

df = pd.read_csv('bookorderrows.csv', sep = ',', delimiter = None,encoding='latin-1')

bkorder = df[['orderno']].drop_duplicates()

def writeOrderfile(doclist, outfile):
    file = open(outfile,'w')
    rec = 'use ("BookShop");\n'
    file.write(rec)
    for r in doclist[['orderno']].itertuples(index=False):
        theserows = (df[(df['orderno']==r)])
        agginfo = theserows[['custname','email','edate','deladdr','Nett','postage','ordertotal']].drop_duplicates()
        tc = theserows[[ 'Title', 'Author', 'Publisher', 'ISBN', 'cover', 'quantity', 
                        'unitprice', 'subtotal', 'image']]
        entries = json.dumps({"orderno": r,
                              "email": agginfo['email'].values[0],
                               "edate": agginfo['edate'].values[0],
                               "custname": agginfo['custname'].values[0],
                               "deladdr": agginfo['deladdr'].values[0],
                               "books": tc.to_dict('records'),
                               "Nett": agginfo['Nett'].values[0],
                               "postage": agginfo['postage'].values[0],
                               "ordertotal": agginfo['ordertotal'].values[0]
                             })
        rec = 'db.bookorder.insertOne(' + entries + ');\n'
        file.write(rec)
    file.close()
    return()

writeOrderfile(bkorder, 'bookorders.mongodb')
