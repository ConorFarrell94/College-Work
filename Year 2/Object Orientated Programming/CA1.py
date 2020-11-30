def menu(): # function to call main menu
    print("-"*25, "\n1. Enter search terms", "\n2. Read document", "\n3. Quit document")

def load_file(file_path): # load file into memory as read only, preserves system memory this way
    with open(file_path, 'r') as loaded_file:
        return loaded_file.read()

def read_document(doc_number): # print document from doc_number
    if doc_number in DOCUMENTS:
        document = DOCUMENTS[doc_number]
        print(document)
    else:
        return print("-"*25, "\nNot in range")

"""
loading the file into a list, then removing and splitting at each <NEW DOCUMENT>
starting at 1 so as when the user wants to print a document they dont need to put 0 in order to print the first document
this happens because arrays start at 0
"""
def process_file_content(file_content):
    DOCUMENTS = {}

    # replace is used to get rid of all the `` and ,, that were in the documents, this makes it look easier to read when printed out
    for i, doc in enumerate(file_content.strip("<NEW DOCUMENT>").replace("'", "").replace("`", "").split("<NEW DOCUMENT>"), start = 1):
        DOCUMENTS[i] = doc
    return DOCUMENTS

""" 
this function takes the file, runs through it and adds each individual word to a set
it then counts how many times each one appears and adds it to a dictionary 
each word will be a key and the documents they appear in will be the values
"""
def build_word_dictionary(docs):
    word_occurance_dict = {}

    for doc_number, doc_content in docs.items():
        for word in doc_content.split():
            word = word.lower()
            if word not in word_occurance_dict:
                word_occurance_dict[word] = set()
            word_occurance_dict[word].add(doc_number)
    return word_occurance_dict
    
"""
for every word in the search term, check where they come up
this will treat "stock prices" as two separate searches because its two words
"""
def search(word_occurance_dict, search_term):
    search_term = search_term.strip() # gets rid of the whitespace in the search terms
    search_term = search_term.lower() # converts input to lower case in order to compare to word_occurance_dict
    search_term = search_term.split() # splits each entered word into separate values

    results = []

    for term in search_term:
        if term in word_occurance_dict:
            results.append(word_occurance_dict[term])

    if len(results) == 0:
        print("-"*25, "\nNot found in any documents")
        return
   
    else:
        print("-"*25, "\nFound in document:")
        print(set.intersection(*results))
        return
    """
    * and intersection enables us to print out only the common documents between all search terms
    hence only giving us documents that every single search term entered appear in
    """

FILE_PATH = "ap_docs.txt" # loading the file path for reading

# loading files and building search databases
file_content = load_file(FILE_PATH) 
processed_documents = process_file_content(file_content) # process DOCUMENTS dictionary in order to use it to build the word occurance dictionary
word_occurance_dict = build_word_dictionary(processed_documents) # building dictionary of words and relevant documents they appear in
DOCUMENTS = process_file_content(file_content)

"""
while loop to always show menu after every action choice
until user chooses option 3 which will break the while loop
and display "goodbye" then close the program
also using and else statement at the end to handle any
invalid input options
"""
stay_in_menu = 0
while stay_in_menu == 0:

    menu()
    option = input("\nWhat would you like to do?\n")

    if option == '1': # search for term 
            print("-"*25, "\nSearch terms: ")
            search_term = input()
            search(word_occurance_dict, search_term)

    elif option == '2': # read document
            #print("-"*25) 
            # try and except will handle all errors/"exceptions" here so as whenever a document number is entered that isnt valid it will let the user know
            try:
                print("-"*25)
                doc_number = int(input("Document number to display?\n"))
                print("-"*25, "\nDocument number :", doc_number, "\n")
                read_document(doc_number)
            except: 
                print("-"*25) 
                print("Invalid document number")

    elif option == '3': # exit program 
            print("-"*25, "\nGoodbye :)")
            stay_in_menu = 1 # break the while loop and end the program

    else: # error handling for invalid inputs
            print("-"*25, "\nInvalid option")