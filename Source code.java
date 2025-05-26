import javax.swing.*;
import java.util.*;

/*
 * Hellooooo, this is the fun part of the program! Please debug or tweak with it how much you'd like!
 * If you want, you can also fork the repository, so you can share your version of my code (don't forget to credit >:|)
 */

public class PointCalculator {

    // Map to store layouts and their corresponding actions with point values
    private static final Map<String, Map<String, Integer>> layouts = new LinkedHashMap<>();

    public static void main(String[] args) {
        // Initialize layouts with their actions and point values
        initializeLayouts();

        // Display a welcome message to the user
        JOptionPane.showMessageDialog(null, "Welcome to the tofulives Point Calculator™! Press Ok to continue.", "Welcome", JOptionPane.INFORMATION_MESSAGE);

        // Main loop to keep the program running until the user decides to quit
        while (true) {
            // Prompt the user to select a layout
            String selectedLayout = selectLayout();
            if (selectedLayout == null) {
                // User canceled the selection; exit the program
                exitProgram();
            }

            // Retrieve the actions for the selected layout
            Map<String, Integer> actions = layouts.get(selectedLayout);
            if (actions == null) {
                // No actions found for the selected layout; show an error and continue
                JOptionPane.showMessageDialog(null, "No actions defined for the selected layout.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            // Prompt the user to select actions and calculate the total points
            int totalPoints = selectActionsAndCalculatePoints(actions);

            // Display the total points to the user
            JOptionPane.showMessageDialog(null, "Total Points: " + totalPoints, "Result", JOptionPane.INFORMATION_MESSAGE);

            // Prompt the user for the next action: perform another calculation, select another layout, or quit
            int nextAction = JOptionPane.showOptionDialog(
                    null,
                    "What would you like to do next?",
                    "Next Step",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Perform Another Calculation", "Select Another Layout", "Quit"},
                    "Perform Another Calculation"
            );

            if (nextAction == 2 || nextAction == JOptionPane.CLOSED_OPTION) {
                // User chose to quit or closed the dialog; exit the program
                exitProgram();
            } else if (nextAction == 1) {
                // User chose to select another layout; continue to the next iteration
                continue;
            }
            // If the user chose to perform another calculation, the loop will continue with the same layout
        }
    }

    /*
     * LAYOUT SELECTION
     */
    private static void initializeLayouts() {
        // Layout 1: Duck Safe House
        Map<String, Integer> DSF = new LinkedHashMap<>();
        DSF.put("Raider Kill", 4);
        DSF.put("Rule Breaker Kill", 2);
        DSF.put("Corpse Thrown Out", 1);
        DSF.put("People Healed/Defibrillated", 1);
        DSF.put("Person Experimented On", 2);
        DSF.put("Hour of Playtime (10 pts each)", 10);
        DSF.put("Hour managing Airlock (15 pts each)", 15);
        DSF.put("Blacklisted Users Sniped", 6);
        DSF.put("Guard Training Complete", 20);
        layouts.put("Duck Safe House", DSF);

        // Layout 2: Goober Safe House
        Map<String, Integer> Goober = new LinkedHashMap<>();
        Goober.put("Raider Elimination", 5);
        Goober.put("Employee Bonus (choose how many points to add)", 1);
        Goober.put("Nuisances (choose how many points to add)", 1);
        Goober.put("Lever Reset", 3);
        Goober.put("CR work (1pts per person let in)", 1);
        Goober.put("Hour of Playtime (10 pts each)", 10);
        Goober.put("Reactor sabotage reset", 15);
        layouts.put("Goober Safe House", Goober);

        // Additional layouts can be added here in the future
    }

    /*
     * Prompts the user to select a layout from the available options.
     * return The name of the selected layout, or null if the user cancels.
     */
    private static String selectLayout() {
        Object[] layoutOptions = layouts.keySet().toArray();
        return (String) JOptionPane.showInputDialog(
                null,
                "Select a point layout:",
                "Layout Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                layoutOptions,
                layoutOptions[0]
        );
    }

    /*
     * Prompts the user to select actions and calculates the total points.
     *
     * @param actions A map of actions and their corresponding point values.
     * @return The total points accumulated from the selected actions.
     */
    private static int selectActionsAndCalculatePoints(Map<String, Integer> actions) {
        int totalPoints = 0;
        List<String> actionList = new ArrayList<>(actions.keySet());

        // Loop to allow the user to select multiple actions
        while (true) {
            Object[] actionOptions = actionList.toArray();
            String selectedAction = (String) JOptionPane.showInputDialog(
                    null,
                    "Select an action (Cancel to finish):",
                    "Action Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    actionOptions,
                    actionOptions[0]
            );

            if (selectedAction == null) {
                // User canceled the selection; break the loop
                break;
            }

            // Prompt the user to enter the quantity for the selected action
            int quantity = promptForQuantity(selectedAction);
            if (quantity <= 0) {
                // Invalid quantity entered; skip this action
                continue;
            }

            // Add the points for the selected action multiplied by the quantity to the total
            totalPoints += actions.get(selectedAction) * quantity;
        }

        return totalPoints;
    }

    /**
     * Prompts the user to enter the quantity for a selected action.
     * *@param action The action for which the quantity is to be entered.
     * *@return The quantity entered by the user, or -1 if invalid.
     */
    private static int promptForQuantity(String action) {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    null,
                    "How many times did you perform \"" + action + "\"?",
                    "Enter Quantity",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (input == null) {
                // User canceled the input; return -1 to indicate invalid input
                return -1;
            }

            try {
                int quantity = Integer.parseInt(input);
                if (quantity > 0) {
                    return quantity;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
     * Exits the program gracefully.
     */
    private static void exitProgram() {
        JOptionPane.showMessageDialog(null, "Thank you for using the Point Calculator!", "Byeee :)", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

/*
    ©tofulives - All rights reserved (/j)
 */