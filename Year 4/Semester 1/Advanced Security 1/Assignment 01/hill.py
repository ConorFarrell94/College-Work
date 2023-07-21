alphabet = {
    "a": 0,
    "b": 1,
    "c": 2,
    "d": 3,
    "e": 4,
    "f": 5,
    "g": 6,
    "h": 7,
    "i": 8,
    "j": 9,
    "k": 10,
    "l": 11,
    "m": 12,
    "n": 13,
    "o": 14,
    "p": 15,
    "q": 16,
    "r": 17,
    "s": 18,
    "t": 19,
    "u": 20,
    "v": 21,
    "w": 22,
    "x": 23,
    "y": 24,
    "z": 25,
}

key_matrix = [1, 0,
              10, 4]  # bake
plaintext = [2, 0,
             10, 4]  # cake

a = ((key_matrix[0] * plaintext[0]) + (key_matrix[1] * plaintext[2])) % 26
b = ((key_matrix[0] * plaintext[1]) + (key_matrix[1] * plaintext[3])) % 26
c = ((key_matrix[2] * plaintext[0]) + (key_matrix[3] * plaintext[2])) % 26
d = ((key_matrix[2] * plaintext[1]) + (key_matrix[3] * plaintext[3])) % 26


encrypted = [a, b, c, d]
cipher_text = ""
for item in encrypted:
    for key, val in alphabet.items():
        if val == item:
            cipher_text += key

print("Plaintext: ", plaintext)
print("Encrypted text: ", encrypted)
print("Cipher text: " + cipher_text)
