import collections # look into this to get total cart value eg. sum(cart.values())
import locale  # can be used for currency 


# can use dictionaries for products / prices aswell


"""

            Composition:
            A Body class 'has a' Head class. A head class cant exist without a Body

            Aggregation
            A Car has an Owner, but a Car can exist with an Owner and an Owner can exist without a Car


"""


# Data holder - good code design wont couple data layer classes with input mechanisms
class Customer(): # class to create customer
    def __init__(self, firstname, surname):
        #age = input("Age : ")
        #location = input("Country of residence : ")

        self.firstname = firstname
        self.surname = surname
        #self.age = age
        #self.location = location

    #def print_shop_items(self):
        
        #raise NotImplementedError("Class has not implemented print_shop_items")

    def __str__(self):
        return 'Hi {self.firstname}!'.format(self=self)


class LoyalCustomers(Customer): # subclass for Customer
    def __init__(self, firstname, surname):
        super().__init__(firstname, surname)
        self.status = "Loyal Customer"
        self.listing = 1

    def __str__(self):
        return 'Hi {self.firstname}!'.format(self=self)


class BargainHunters(Customer): # subclass of Customer
    def __init__(self, firstname, surname):
        super().__init__(firstname, surname)
        self.status = "Bargain Hunter"
        self.listing = 0

    def __str__(self):
        return 'Hi {self.firstname}!'.format(self=self)


class ShopItems():
    ItemsList = [
    {
        'name' : 'Juice',
        'price' : 1.5,
        'ID' : 1,
        'Loyal' : 0
    },
    {
        'name' : 'Chocolate',
        'price' : 2.5,
        'ID' : 2,
        'Loyal' : 0
    },
    {
        'name' : 'Face Mask',
        'price' : 5,
        'ID' : 3,
        'Loyal' : 0
    },
    {
        'name' : 'Alcohol',
        'price' : 15,
        'ID' : 4,
        'Loyal' : 1
    },
    {
        'name' : 'Lottery Ticket',
        'price' : 10,
        'ID' : 5,
        'Loyal' : 1
    },
    ]


class ShoppingCart():
    def __init__(self):
        self.cart = {}
        for x in range(len(ShopItems.ItemsList)):
            self.cart[x + 1] = 0

    def cart_add(self, pid):
        self.cart[pid] += 1

    def cart_rem(self, pid):
        self.cart[pid] -= 1

    def cart_get(self):
        return self.cart

    def cart_key(self, key):
        return self.cart[key]


def main_menu():
    print("-"*25)
    print("[ 1 ] Create a Customer\n[ 2 ] List Products\n"
          "[ 3 ] Add/Remove A Product In Shopping Cart\n"
          "[ 4 ] See Current Shopping Cart\n[ 5 ] Checkout\n[ 6 ] Exit")


def print_items():
    for x in range(len(ShopItems.ItemsList)):
     if customer.listing == 1:
         print(ShopItems.ItemsList[x] ['name'], "for €", ShopItems.ItemsList[x]['price'])
     elif customer.listing == 0 and ShopItems.ItemsList[x]['Loyal'] == 0 :
         print(ShopItems.ItemsList[x]['name'], "for €", ShopItems.ItemsList[x]['price'])


customer = None
myCart = dict()

while True:
    main_menu()
    option = input() # users choice from menu

    if option == '1':  # create customer
        if not customer: # If there's no customer, try to create one
            print("-" * 25)
            firstname = input("First Name : ")
            surname = input("Surname : ")
            print("-" * 25)
            ShopperType = input("Which shopper are you?\n[ 1 ] Loyal Shopper\n[ 2 ] Bargain Hunters\n")
            if ShopperType == '1':
                customer = LoyalCustomers(firstname, surname)
                print("-" * 25)
                print("Welcome", customer.firstname, customer.surname, "you're classed as a", customer.status)
            elif ShopperType == '2':
                customer = BargainHunters(firstname, surname)
                print("Welcome", customer.firstname, customer.surname, "you're classed as a", customer.status)
            else:
                print("Invalid option")
        
        while customer:
            main_menu()
            option = input() # users choice from menu
            if option == '1':
                print("-" * 25)
                print ("Customer already created")

            if option == '2':  # list products
                print("-" * 25)
                print_items()

            if option == '3': # add / remove products
                print("-" * 25)
                #print(customer.get_list())
                choice = input()
                    
            if option == '4':# see current shopping cart
                if myCart != None:
                    for key in myCart.keys():
                        for value in myCart.values():
                            print (key, value)
                    pass
                else:
                    print("Shopping cart empty")

            if option == '5': # checkout
                if myCart != None:
                    pass
                else:
                    print("Shopping cart empty")

            elif option == '6': # exit code
                exit(print("Goodbye"))

    if option == '6': # exit code
        exit(print("Goodbye"))

    elif not customer:
        print("Please create a customer first")
   
    else: # error checking 
        print("Invalid option")