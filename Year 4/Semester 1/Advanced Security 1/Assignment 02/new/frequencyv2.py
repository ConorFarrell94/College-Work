# Set the ciphertext
ciphertext = "UZQSOVUOHXMOPVGPOZPEVSGZWSZOPFPESXUDBMETSXAIZ\
VUEPHZHMDZSHZOWSFPAPPDTSVPQUZWYMXUZUHSXEPYEPOPDZSZUFPOMBZ\
WPFUPZHMDJUDTMOHMQ"

# Create a frequency analysis table for the ciphertext
freq_table = {}
for c in ciphertext:
    if c not in freq_table:
        freq_table[c] = 1
    else:
        freq_table[c] += 1

# Sort the frequency table in descending order by frequency
sorted_freq_table = sorted(freq_table.items(), key=lambda x: x[1], reverse=True)

# Create a mapping from ciphertext letters to plaintext letters using the frequency analysis table
# (assign the most frequent ciphertext letter to the most frequent plaintext letter, etc.)
mapping = {}
for i in range(len(sorted_freq_table)):
    mapping[sorted_freq_table[i][0]] = "ETAOINSHRDLUCMFYWGPBVKXQJZ"[i]

# Decode the ciphertext using the mapping and print the resulting plaintext
plaintext = ""
for c in ciphertext:
    plaintext += mapping[c]
print(plaintext)