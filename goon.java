import java.util.Scanner;
import java.util.Random;

public class GooningSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Game States
        int focusMeter = 20;   // Ranges 0 to 100
        int brainRot = 5;      // Cumulative multiplier/penalty
        int streakHours = 0;   // The score
        boolean playing = true;

        System.out.println("=============================================");
        System.out.println("⚡ WELCOME TO THE ULTIMATE GOONING SIMULATOR ⚡");
        System.out.println("=============================================");
        System.out.println("Rules: Keep your Focus Meter high, build your Streak,");
        System.out.println("but do NOT let Focus hit 100%, or you Max Out and lose!");
        System.out.println("---------------------------------------------\n");

        while (playing) {
            // Display Current Stats
            System.out.println("--- Hour " + streakHours + " of the Marathon ---");
            System.out.println("🧠 Brain Rot Level: " + brainRot + "%");
            System.out.print("📊 Focus Meter:    [");
            int visualBars = focusMeter / 5;
            for (int i = 0; i < 20; i++) {
                if (i < visualBars) System.out.print("█");
                else System.out.print("░");
            }
            System.out.println("] " + focusMeter + "%");
            System.out.println("---------------------------------------------");
            System.out.println("Choose your next action:");
            System.out.println("1) 🌀 Edge (Increase focus riskily)");
            System.out.println("2) 🧴 Jelq (Stabilize your current state)");
            System.out.println("3) 🚿 Cold Shower (Drastically lower focus & rot)");
            System.out.println("4) 🚪 Quit (Lock in your final score)");
            System.out.print("> ");

            String input = scanner.nextLine();
            System.out.println();

            switch (input) {
                case "1": // Edge
                    int gain = random.nextInt(15) + 10 + (brainRot / 5);
                    focusMeter += gain;
                    brainRot += random.nextInt(4) + 2;
                    streakHours++;
                    System.out.println("🔥 You lock in! Focus increased by " + gain + "%.");
                    break;

                case "2": // Jelq
                    if (focusMeter > 10) {
                        int reduction = random.nextInt(10) + 5;
                        focusMeter -= reduction;
                        System.out.println("🧴 You balance your cadence. Focus reduced by " + reduction + "%.");
                    } else {
                        System.out.println("❌ Focus is too low to optimize right now.");
                    }
                    streakHours++;
                    break;

                case "3": // Cold Shower
                    focusMeter = Math.max(0, focusMeter - 40);
                    brainRot = Math.max(0, brainRot - 10);
                    streakHours++;
                    System.out.println("🥶 CRITICAL RESET: The cold water snaps you back to reality.");
                    System.out.println("Focus dropped heavily. Brain Rot cleansed slightly.");
                    break;

                case "4": // Quit
                    playing = false;
                    System.out.println("🏁 You decided to stop and lock in your legacy.");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. The brain rot is making you mistype. Try again.");
                    continue; // Skip the cycle checks if input was invalid
            }

            // Passive stat scaling per turn
            if (playing) {
                focusMeter += (brainRot / 8); // Brain rot makes focus naturally drift upward
            }

            // Lose Condition: Maxing Out
            if (focusMeter >= 100) {
                System.out.println("\n💥 NOOOOO! YOU MAXED OUT! 💥");
                System.out.println("The raw energy overfilled the meter. You lost control.");
                System.out.println("Your marathon breaks down entirely.");
                playing = false;
            } 
            // Lose Condition: Total Brain Melting
            else if (brainRot >= 100) {
                System.out.println("\n🫠 BRAIN ROT CRITICAL FAILURE 🫠");
                System.out.println("Your brain has completely melted into pure internet sludge.");
                System.out.println("You can no longer parse reality.");
                playing = false;
            }
            
            System.out.println();
        }

        // Game Over Summary
        System.out.println("=============================================");
        System.out.println("🎮 GAME OVER");
        System.out.println("Final Score (Streak Hours): " + streakHours);
        System.out.println("=============================================");
        scanner.close();
    }
}
