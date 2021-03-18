# this function adds two numbers
def add(x, y):
    return x + y


# this function subtracts two numbers
def subtract(x, y):
    return x - y


# this function multiplies two numbers
def multiply(x, y):
    return x * y


# this function divides two numbers
def divide(x, y):
    return x / y


print("Select an operator\n")
print("1 - Addition\n")
print("2 - Subtraction\n")
print("3 - Multiplication\n")
print("4 - Division\n")


choice = int(input())

if choice == 1:
    num1 = int(input("Enter first number:"))
    num2 = int(input("Enter second number:"))
    print(num1, "+", num2, "=", add(num1, num2))
    # using to stop cmd from closing immediately
    A = input("Press enter to exit")

elif choice == 2:
    num1 = int(input("Enter first number:"))
    num2 = int(input("Enter second number:"))
    print(num1, "-", num2, "=", subtract(num1, num2))
    # using to stop cmd from closing immediately
    A = input("Press enter to exit")

elif choice == 3:
    num1 = int(input("Enter first number:"))
    num2 = int(input("Enter second number:"))
    print(num1, "*", num2, "=", multiply(num1, num2))
    # using to stop cmd from closing immediately
    A = input("Press enter to exit")

elif choice == 4:
    num1 = int(input("Enter first number:"))
    num2 = int(input("Enter second number:"))
    print(num1, "/", num2, "=", divide(num1, num2))
    # using to stop cmd from closing immediately
    A = input("Press enter to exit")
