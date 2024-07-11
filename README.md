Here’s a detailed breakdown of the Java code for the CarRentalSystem class, its associated classes, and the main method. Each point explains a different aspect of the code:
1. Class Definitions
   
•	Car Class: Represents a car with properties and methods related to its rental.
•	Customer Class: Represents a customer with an ID and a name.
•	Rental Class: Represents a rental transaction involving a car, a customer, and rental duration.
•	CarRentalSystem Class: Manages the collection of cars, customers, and rentals, and provides a menu for user interaction.

2. 'Car' Class

•	Attributes:

 o	carID: Unique identifier for the car.
 o	brand: Brand of the car.
 o	model: Model of the car.
 o	basePricePerDay: Daily rental rate for the car.
 o	isAvailable: Availability status of the car.

•	Constructor:

 o	Initializes a new Car object with carID, brand, model, basePricePerDay, and sets 
    isAvailable to true.
•	Methods:
 o	getCarID(), getBrand(), getModel(): Getters for car details.
 o	calculatePrice(int rentalDays): Computes the total rental price based on the number of days.
 o	isAvailable(): Checks if the car is available for rent.
 o	rent(): Marks the car as rented (sets isAvailable to false).
 o	returnCar(): Marks the car as available (sets isAvailable to true).

3. 'Customer' Class
   
•	Attributes:

o	customerId: Unique identifier for the customer.
 o	name: Name of the customer.

•	Constructor:
 o	Initializes a new Customer object with customerId and name.

•	Methods:
 o	getCustomerId(), getName(): Getters for customer details.

4. 'Rental' Class
   
•	Attributes:
 o	car: The Car object being rented.
 o	customer: The Customer object renting the car.
 o	days: Number of days for which the car is rented.

•	Constructor:
 o	Initializes a new Rental object with car, customer, and days.

•	Methods:
 o	getCar(), getCustomer(), getDays(): Getters for rental details.

5. CarRentalSystem Class

•	Attributes:
 o	cars: List of available cars.
 o	customers: List of registered customers.
 o	rentals: List of current rentals.

•	Constructor:
 o	Initializes empty lists for cars, customers, and rentals.

•	Methods:
 o	addCar(Car car): Adds a car to the list of available cars.
 o	addCustomer(Customer customer): Adds a customer to the list of registered customers.
 o	rentCar(Car car, Customer customer, int days): Processes a rental transaction if the car is 
    available.
 o	returnCar(Car car): Handles the return of a rented car and removes the rental record.
 o	menu(): Displays the main menu for the user to interact with the system:
    	Rent a Car: Allows a customer to rent a car.
    	Return a Car: Allows a customer to return a rented car.
    	Exit: Exits the application.

•	Main Method:
 o	Creates a CarRentalSystem instance.
 o	Adds some sample cars to the system.
 o	Calls the menu() method to start the user interface loop.

6. User Interaction in 'menu()' Method
 
•	Displays Options:
 o	Presents options to rent a car, return a car, or exit the application.
•	Rent a Car:
 o	Prompts the user for their name, displays available cars, and allows them to select a car 
    and specify rental days.
 o	Confirms the rental transaction and calculates the total price.
 
•	Return a Car:
 o	Prompts the user for the car ID to return.
 o	Checks if the car was rented and processes the return if valid.
 
•	Exit:
 o	Terminates the application.
 
7. Example Usage

•	Adding Cars: 
 o	Three cars are created with specific IDs, brands, models, and prices.
 o	These cars are added to the CarRentalSystem.
•	Starting the Application:
 o	The menu() method is called to provide a console-based interface for the user to interact 
    with the system.
