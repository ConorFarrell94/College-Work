# Import the necessary modules
from collections import Counter

# Define the ciphertext
ciphertext = "UZQSOVUOHXMOPVGPOZPEVSGZWSZOPFPESXUDBMETSXAIZVUEPHZHMDZSHZOWSFPAPPDTSVPQUZWYMXUZUHSXEPYEPOPDZSZUFPOMBZWPFUPZHMDJUDTMOHMQ"

# Count the frequency of each letter in the ciphertext
counter = Counter(ciphertext)

# Calculate the total number of letters in the ciphertext
total_letters = sum(counter.values())

# Create a list of tuples with the letter and its frequency percent
letters_list = [(letter, (count / total_letters) * 100) for letter, count in counter.items()]

# Sort the list by the frequency percent in descending order
sorted_letters = sorted(letters_list, key=lambda x: x[1], reverse=True)

# Print the letter frequency percent for each letter in the ciphertext
for letter, percent in sorted_letters:
    rounded_percent = round(percent, 1)
    print(f"{letter}: {rounded_percent}%")