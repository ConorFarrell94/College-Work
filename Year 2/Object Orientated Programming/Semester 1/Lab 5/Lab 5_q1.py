print("Enter a number between 1 and 100\n")
number = int(input())

if number > 100:
    print("Over 100, Try again")

if number < 1:
    print("Under 1, Try again")

if number >= 40:
    print("Pass mark")

if number < 40:
    print("Fail mark")

# using to stop cmd from closing immediately
A = input("Press enter to exit")
