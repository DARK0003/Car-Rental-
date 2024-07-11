import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarRentalSystemUI {
    private CarRentalSystem carRentalSystem;

    private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;

    public CarRentalSystemUI(CarRentalSystem carRentalSystem) {
        this.carRentalSystem = carRentalSystem;

        frame = new JFrame("Car Rental System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        createMainMenu();
        createRentCarMenu();
        createReturnCarMenu();

        frame.add(panel);
        frame.setVisible(true);
    }

    private void createMainMenu() {
        JPanel mainMenu = new JPanel(new GridLayout(3, 1));

        JButton rentCarButton = new JButton("Rent a Car");
        rentCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "RentCarMenu");
            }
        });

        JButton returnCarButton = new JButton("Return a Car");
        returnCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "ReturnCarMenu");
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainMenu.add(rentCarButton);
        mainMenu.add(returnCarButton);
        mainMenu.add(exitButton);

        panel.add(mainMenu, "MainMenu");
    }

    private void createRentCarMenu() {
        JPanel rentCarMenu = new JPanel(new BorderLayout());

        JTextArea availableCarsArea = new JTextArea();
        availableCarsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(availableCarsArea);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "MainMenu");
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Enter your name:"));
        JTextField nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Enter the car ID:"));
        JTextField carIdField = new JTextField();
        inputPanel.add(carIdField);

        inputPanel.add(new JLabel("Enter rental days:"));
        JTextField daysField = new JTextField();
        inputPanel.add(daysField);

        JButton rentButton = new JButton("Rent");
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerName = nameField.getText();
                String carId = carIdField.getText();
                int rentalDays = Integer.parseInt(daysField.getText());

                Customer newCustomer = new Customer("CUS" + (carRentalSystem.getCustomers().size() + 1), customerName);
                carRentalSystem.addCustomer(newCustomer);

                Car selectedCar = carRentalSystem.findCarById(carId);
                if (selectedCar != null && selectedCar.isAvailable()) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    carRentalSystem.rentCar(selectedCar, newCustomer, rentalDays);
                    JOptionPane.showMessageDialog(frame, String.format("Car rented successfully!\nTotal Price: $%.2f", totalPrice));
                } else {
                    JOptionPane.showMessageDialog(frame, "Car is not available for rent or invalid Car ID.");
                }
            }
        });

        inputPanel.add(rentButton);

        rentCarMenu.add(scrollPane, BorderLayout.CENTER);
        rentCarMenu.add(inputPanel, BorderLayout.SOUTH);
        rentCarMenu.add(backButton, BorderLayout.NORTH);

        panel.add(rentCarMenu, "RentCarMenu");

        updateAvailableCars(availableCarsArea);
    }

    private void createReturnCarMenu() {
        JPanel returnCarMenu = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "MainMenu");
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));

        inputPanel.add(new JLabel("Enter the car ID to return:"));
        JTextField carIdField = new JTextField();
        inputPanel.add(carIdField);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String carId = carIdField.getText();
                Car carToReturn = carRentalSystem.findCarById(carId);
                if (carToReturn != null && !carToReturn.isAvailable()) {
                    carRentalSystem.returnCar(carToReturn);
                    JOptionPane.showMessageDialog(frame, "Car returned successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Car was not rented or invalid Car ID.");
                }
            }
        });

        inputPanel.add(returnButton);

        returnCarMenu.add(inputPanel, BorderLayout.CENTER);
        returnCarMenu.add(backButton, BorderLayout.NORTH);

        panel.add(returnCarMenu, "ReturnCarMenu");
    }

    private void updateAvailableCars(JTextArea availableCarsArea) {
        List<Car> availableCars = carRentalSystem.getAvailableCars();
        StringBuilder carsText = new StringBuilder();
        for (Car car : availableCars) {
            carsText.append(car.getCarID()).append(" - ").append(car.getBrand()).append(" - ").append(car.getModel()).append("\n");
        }
        availableCarsArea.setText(carsText.toString());
    }
}
