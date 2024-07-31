import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);
        List<Integer> appleWeights = new ArrayList<>();
        System.out.println("Enter apple weight in grams (-1 to stop):");

        // Collecting apple weights
        while (true) {
            int weight = scanner.nextInt();
            if (weight == -1) {
                break;
            }
            appleWeights.add(weight);
        }


        // Input payment amounts
        System.out.println("Enter the amount Ram paid:");
        double ramPaid = scanner.nextDouble();

        System.out.println("Enter the amount Sham paid:");
        double shamPaid = scanner.nextDouble();

        System.out.println("Enter the amount Rahim paid:");
        double rahimPaid = scanner.nextDouble();

        double totalAmountPaid = ramPaid + shamPaid + rahimPaid;

        // Sort apple weights in descending order
        appleWeights.sort(Collections.reverseOrder());

//        Collections.sort(appleWeights);


        // Calculate the target weights for each person based on the amount they paid
        double ramTarget = (ramPaid / totalAmountPaid) * totalWeight(appleWeights);
        double shamTarget = (shamPaid / totalAmountPaid) * totalWeight(appleWeights);
        double rahimTarget = (rahimPaid / totalAmountPaid) * totalWeight(appleWeights);

        List<Integer> ramApples = new ArrayList<>();
        List<Integer> shamApples = new ArrayList<>();
        List<Integer> rahimApples = new ArrayList<>();

        // Distribute apples
        for (int i = 0;i < appleWeights.size() ;i++) {

            int weight = appleWeights.get(i);

            if (totalWeight(ramApples) + weight <= ramTarget) {
                ramApples.add(weight);
                appleWeights.remove(i);
                i = i - 1;

            } else if (totalWeight(shamApples) + weight <= shamTarget) {
                shamApples.add(weight);
                appleWeights.remove(i);
                i = i - 1;
            } else if(totalWeight(rahimApples) + weight <= rahimTarget){
                rahimApples.add(weight);
                appleWeights.remove(i);
                i = i - 1;
            }
        }

        // If is there any apples left till now then i can say these should not be distribute directly

        // If the person remaining percentage is more than 50% of this fruit he is the guy eligible for this fruit

        // since this time first i am taking care about the smaller weights then i distribute this to the guy which have
        // contributed lesser (rahim basing on the question) if the contribution is dynamic then the order will be changed

        System.out.println(appleWeights); // print all the apples which can not directly distributed

        for(int i = appleWeights.size() - 1;i >= 0; i--) {

            double remWeight = rahimTarget - totalWeight(rahimApples);
            double ramWeight = ramTarget - totalWeight(ramApples);
            double shamWeight = shamTarget - totalWeight(shamApples);

            if(remWeight > (appleWeights.get(i)/2)) { // this have more than 50% of contribution
                rahimApples.add(appleWeights.get(i));
            }
            else if(shamWeight > (appleWeights.get(i)/2)) { // this guy have more than 50% of contribution

                shamApples.add(appleWeights.get(i));
            }

            else{
                // if the both of the above are not sufficient to get this apple may be i am the one

                // even after i am not one why should we left the apple empty

                // since he has the mor money than others give this apple to him is the best one

                ramApples.add(appleWeights.get(i));
            }
        }




        // Printing the distribution
        System.out.println("Distribution Result:");
        System.out.println("Ram: " + ramApples);
        System.out.println("Sham: " + shamApples);
        System.out.println("Rahim: " + rahimApples);
    }

    // Utility function to calculate the total weight of apples
    private static double totalWeight(List<Integer> weights) {
        return weights.stream().mapToDouble(Integer::doubleValue).sum();
    }
}
