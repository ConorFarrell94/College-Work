# Import the necessary modules
import random

# Define the rules for determining if a number is prime
def is_prime(n, k=128):
  # Handle small numbers
  if n in [2, 3]:
    return True
  if n == 1 or n % 2 == 0:
    return False

  # Find the largest power of 2 that divides n - 1
  s = 0
  d = n - 1
  while d % 2 == 0:
    d //= 2
    s += 1

  # Test the number k times
  for _ in range(k):
    a = random.randrange(2, n - 1)
    x = pow(a, d, n)
    if x != 1 and x != n - 1:
      for _ in range(s - 1):
        x = pow(x, 2, n)
        if x == 1:
          return False
        if x == n - 1:
          break
      else:
        return False
  return True

# Test the is_prime() function
print(is_prime(11))
print(is_prime(15))
print(is_prime(101))
print(is_prime(3))


