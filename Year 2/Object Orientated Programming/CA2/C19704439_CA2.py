import random
import re

"""
data holder - good code design wont couple data layer classes with input mechanisms
this stores only first and surname of the customer
"""
class Customer(): # class to create customer
    def __init__(self, firstname, surname):
        self.firstname = firstname
        self.surname = surname

    def __str__(self):
        return (self.firstname, self.surname)


"""
subclass of customer which has its own custom status along with a "listing" value which determines which items are availiable to it
"""
class LoyalCustomers(Customer): # subclass for Customer
    def __init__(self, firstname, surname):
        super().__init__(firstname, surname)
        self.status = "Loyal Customer"
        self.listing = 1

    def __str__(self):
        return (self.status)


"""
subclass of customer which has its own custom status along with a "listing" value which determines which items are availiable to it
"""
class BargainHunters(Customer): # subclass of Customer
    def __init__(self, firstname, surname):
        super().__init__(firstname, surname)
        self.status = "Bargain Hunter"
        self.listing = 0
    
    def __str__(self):
        return (self.status)


"""
stores all shop items
more can be added as long as they fit the template of 
name :
price :
ID : 
loyal : 
"""
class ShopItems():
    items_list = [
    {
        'name' : 'Juice',
        'price' : round(random.uniform(1,5), 2), # picks a random value float between 1 and 5 and limits it to 2 decimal points
        'ID' : 1,
        'loyal' : 0
    },
    {
        'name' : 'Chocolate',
        'price' : round(random.uniform(1,5), 2),
        'ID' : 2,
        'loyal' : 0
    },
    {
        'name' : 'Face Mask',
        'price' : round(random.uniform(5,10), 2),
        'ID' : 3,
        'loyal' : 0
    },
    {
        'name' : 'Alcohol',
        'price' : round(random.uniform(10,25), 2),
        'ID' : 4,
        'loyal' : 1
    },
    {
        'name' : 'Lottery Ticket',
        'price' : round(random.uniform(1,10), 2),
        'ID' : 5,
        'loyal' : 1
    },
    ]


"""
provides the abilities to add/remove and view the current shopping cart
"""
class ShoppingCart():
    def __init__(self):
        self.my_cart = {} # set my_cart to be a dict
        for x in range(len(ShopItems.items_list)):
            self.my_cart[x + 1] = 0

    def cart_listing(self, listing):
        return self.my_cart[listing] 

    def cart_get(self): # returns whats in my_cart
        return self.my_cart

    def cart_add(self, ID): # adds item to cart based on the ID value
        self.my_cart[ID] += 1

    def cart_remove(self, ID): # removes item from cart based on the ID value
        self.my_cart[ID] -= 1

    def __str__(self):
        return (self.my_cart)


"""
displays main menu options to user
"""
def main_menu():
    print("-"*25)
    print("[ 1 ] Create a Customer\n"
          "[ 2 ] List Products\n"
          "[ 3 ] Add/Remove A Product In Shopping Cart\n"
          "[ 4 ] See Current Shopping Cart\n"
          "[ 5 ] Checkout\n"
          "[ 6 ] Exit")


"""
takes in users first and last name and stores it to customer.
then asks which shopper type they are and sets the customer type to the relevant subclass (bargain or loyal)
"""
def get_customer():
    print("-" * 25)
    firstname = input("First Name : ")
    surname = input("Surname : ")
    print("-" * 25)
    ShopperType = input("Which shopper are you?\n[ 1 ] Loyal Shopper\n[ 2 ] Bargain Hunter\n")
    if ShopperType == '1':
        customer = LoyalCustomers(firstname, surname)
        print("-" * 25)
        print("Welcome", customer.firstname, customer.surname, "you're status is", customer.status)
        return(customer)
    elif ShopperType == '2':
        customer = BargainHunters(firstname, surname)
        print("-" * 25)
        print("Welcome", customer.firstname, customer.surname, "you're status is", customer.status)
        return(customer)
    else:
        return


"""
will print out shop ID, name and price by taking the range of the shop items and printing out each value
can also differentiate between the type of customer trying to view the items because of the customer.listing value
"""
def print_items():
    print("-" * 25)
    for x in range(len(ShopItems.items_list)):
     if customer.listing == 1:
         print("Product ID [", ShopItems.items_list[x] ['ID'], "]", ShopItems.items_list[x] ['name'], "for €", ShopItems.items_list[x] ['price'])
     elif customer.listing == 0 and ShopItems.items_list[x]['loyal'] == 0 :
         print("Product ID [", ShopItems.items_list[x] ['ID'], "]", ShopItems.items_list[x] ['name'], "for €", ShopItems.items_list[x] ['price'])


"""
adds or removes items from the my_cart dict based on the entered ID value from the user
"""
def add_remove():
    print("-" * 25)
    choice = input("[ 1 ] Add\n[ 2 ] Remove\n")
    if choice == '1':
        print_items()
        ID = int(input("\nSelect your item to add by Product ID : "))
        if customer.listing == 0 and ShopItems.items_list[ID - 1]['loyal'] == 1: # error checking to prevent bargain hunter buying loyal items
            print("-" * 25)
            print("Invalid option")
        else:
            my_cart.cart_add(ID)
    elif choice == '2':
        print("-" * 25)
        ID = int(input("Select your item to remove by Product ID : "))
        if my_cart.cart_listing(ID) > 0:
            my_cart.cart_remove(ID)
        else:
            print("Item not currently in cart or cart may be empty")
    else:
        print("Invalid option")


"""
takes the my_cart dict and prints through it based on the length of it
also totals up the prices and prints them back out for the user
"""
def calculate_cart(total):
    print("-" * 25)
    for x in range(len(my_cart.cart_get())):
        if my_cart.cart_listing(x + 1) != 0:
            print("Product ID [", ShopItems.items_list[x] ['ID'], "]", ShopItems.items_list[x] ['name'], "X", my_cart.cart_listing(x + 1))
            total = ShopItems.items_list[x] ['price'] * my_cart.cart_listing(x + 1) # adds value of the shop item to the my_cart dict in order to pass back the total
    print("Total price of cart : €", "{:.2f}".format(total)) # this formatting ensures that if multiple items are added the total will still only display in two decimal places
    return total


"""
prints total value then asks for confirmation of checkout
"""
def checkout():
    print("-" * 25)
    choice = input("Confirm purchase?\n[ 1 ] Yes\n[ 2 ] No\n")
    if choice == '1':
        print("-" * 25)
        card_number = input("Please enter your 16 digit long card number : \n")
        if not re.match("[0-9]", card_number): # checks input to ensure only values between 0 and 9 are entered
            print("Invalid card number")
        elif len(card_number) > 16 or len(card_number) >= 17: # checks input to ensure there are 16 digits entered
            print("Invalid card number")
        elif len(card_number) == 16 and re.match("[0-9]", card_number):
            print("-" * 25)
            print("Confirmation recieved!\nThanks", customer.firstname, "for shopping with us")
            exit(print("Goodbye!"))
    else:
        return 


"""
test function to see quick execution of code
"""
def test():
    ans = input("[ 1 ] Loyal\n[ 2 ] Bargain\n")
    if ans == '1':
        customer = LoyalCustomers('Master', 'Chief')
    elif ans == '2':
        customer = BargainHunters('John', 'Wick')
    print(customer)
    print_items()
    my_cart.cart_add(1)
    print("added [ 1 ] to cart")
    my_cart.cart_add(2)
    print("added [ 2 ] to cart")
    my_cart.cart_add(3)
    print("added [ 3 ] to cart")
    my_cart.cart_remove(1)
    print("removed [ 1 ] from cart")
    calculate_cart(total)
    checkout()


customer = None # setting as None initially so as user will be forced to make a customer
my_cart = ShoppingCart() # setting my_cart as the shoppingcart class
total = 0

while True: # loop forever
    main_menu()
    option = input() # users choice from menu
    if option == '1':  # create customer
        if not customer: # if there's no customer, try to create one
            customer = get_customer()

    while customer:
        main_menu()
        option = input() # users choice from menu
        if option == '1':
            print("-" * 25)
            print ("Customer already created")
        if option == '2': # list products
            print_items()
        if option == '3': # add / remove products
            add_remove()
        if option == '4': # see current shopping cart
            calculate_cart(total)
        if option == '5': # checkout
            calculate_cart(total) # putting this here enables us to go straight to checkout without selection option 4 first
            checkout()
        elif option == '6': # exit code
            print("-" * 25)
            exit(print("Goodbye", customer.firstname)) # goodbye by name because a customer has been made at this point
            
    if option == '6': # exit code
        print("-" * 25)
        exit(print("Goodbye"))
    elif not customer:
        print("-" * 25)
        print("Please create a customer first")

