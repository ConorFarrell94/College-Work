print("Enter 2 integers\n")
number1 = int(input())
number2 = int(input())

if number1 == number2:
    print("Both numbers are equal")

elif number1 > number2:
    print("First number is higher than second")

elif number1 < number2:
    print("First number is less than second")

# using to stop cmd from closing immediately
A = input("Press enter to exit")
