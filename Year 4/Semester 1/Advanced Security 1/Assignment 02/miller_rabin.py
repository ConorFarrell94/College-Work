import random

def miller(n, k):

    temp = n - 1
    r = 0
    while True:
        if temp / 2 == type(int):
            r += 1
        else:
            d = temp
            break
        temp = temp / 2

    for _ in range(k):
        a = random.SystemRandom().randrange(2, n - 2)
        x = a**d % n
        if x == 1 or x == n - 1:
            continue
        for _ in range(r - 1):
            x = x**2 % n
            if x == n - 1:
                continue
        return n , "Not a Prime"
    return n , "Prime"

print(miller(6, 4))
print(miller(13, 4))
