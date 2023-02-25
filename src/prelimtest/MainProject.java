package prelimtest;

import java.util.Scanner;

public class MainProject {
    // Variables for FCFS
    static int e, no_p, burstTimeFCFS[], turnAroundTimeFCFS[], AWTFCFS[];
    static float avg_waitFCFS=0,avg_TTFCFS=0;

    // Variables for SJF
    static int burstTimeSJF[], processSJF[], waitingTimeSJF[], tatSJF[], a, b, c, totalSJF = 0, posSJF, tempSJF;
    static float waitAvgSJF, tatAvgSJF;


    // Variables for RR
    static int n, i, quantumTime_RR, temp_RR, count_RR = 0, sq=0, burstTime_RR[], waitingTime_RR[],completionTime_RR[], tat_RR[], remainingTime_RR[],partialTime_RR[];
    static float awt=0,atat=0, awtf=0, attf=0;

    // Variables for Final
    static float awtFinal1=0, awtFinal2=0, attFinal1=0, attFinal2=0;

    public static void group_Info () {

        System.out.println("Group #6: Batch Queue Algorithm: ");
        System.out.println("Amegleo, John Paul");
        System.out.println("Espinosa, Ronwaldo C. ");
        System.out.println("Fernandez, Kyle P.");
        System.out.println("Lahoz, Krisia Jovan C.");
        System.out.println("Tabiquero, Khristopher Reeve.");
        System.out.println("Torriana, Kesha N. ");
        System.out.println("CCS - 204 - CS22S2 - Operating Systems");
        System.out.println("\n");
    }

    // LOGIC OF FCFS
    public static void FCFS_Logic () {

        Scanner scan = new Scanner(System.in);

        burstTimeFCFS = new int[50];
        turnAroundTimeFCFS = new int[50];
        AWTFCFS = new int[50];
        AWTFCFS[0] = 0;

        System.out.println("--- First Come First Serve Algorithm: ---");

        // Input number of process
        System.out.print("Enter the number of process: ");
        no_p = scan.nextInt();

        System.out.println("\nEnter the burst time for each process:");

        for(e=0; e<no_p; e++) {
            System.out.print("\tP" + (e+1) + ":  ");
            burstTimeFCFS[e]=scan.nextInt();
        }

        for(e=1; e<no_p; e++) {
            AWTFCFS[e] = AWTFCFS[e-1] + burstTimeFCFS[e-1];
            avg_waitFCFS += AWTFCFS[e];
        }

        avg_waitFCFS /= no_p;

        for(e=0; e<no_p; e++) {
            turnAroundTimeFCFS[e] = AWTFCFS[e] + burstTimeFCFS[e];
            avg_TTFCFS += turnAroundTimeFCFS[e];
        }
        avg_TTFCFS /= no_p;
    }


    // DISPLAY OF FCFS
    public static void FCFS_Display ( ) {

        System.out.println("\n\n--- Results of Batch Queue Algorithm Below ---");
        System.out.print("\n-----------------------------------------------------------------------------------------------");
        System.out.println("\nFirst Come First Serve Algorithm Results:");
        System.out.print("-----------------------------------------------------------------------------------------------");
        System.out.print("\nProcess\t       Burst Time\t        Waiting Time\t        Turnaround Time\n");
        System.out.print("-----------------------------------------------------------------------------------------------");
        for(e=0;e<no_p;e++)	{
            System.out.println("\n" + "P" + (e+1) +"\t\t\t   "
                    + burstTimeFCFS[e] + "\t\t\t\t\t    "
                    + AWTFCFS[e] + "\t\t\t \t\t     "
                    + turnAroundTimeFCFS[e]);
        }

        System.out.println("\nAverage Waiting Time = "+avg_waitFCFS + " seconds");
        System.out.println("\nAverage Turnaround Time = "+avg_TTFCFS + " seconds");
        System.out.println("-----------------------------------------------------------------------------------------------\n\n");
    }

    // LOGIC OF SJF
    public static void SJF_Logic (){
        Scanner s = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("--- Shortest Job First Algorithm: ---");

        System.out.print("Enter the number of processes: ");
        c = s.nextInt();

        processSJF = new int[c];
        burstTimeSJF = new int[c];
        waitingTimeSJF = new int[c];
        tatSJF = new int[c];

        System.out.println("\nEnter the burst time for each process:");
        for (a = 0; a < c; a++) {
            System.out.print("\tP"+ (a+1) + ": ");
            burstTimeSJF[a] = s.nextInt();
            processSJF[a] = a+1; //Process Number
        }

        //Sorting
        for (a=0; a<c; a++) {
            posSJF = a;
            for (b=a+1; b<c; b++) {
                if (burstTimeSJF[b] < burstTimeSJF[posSJF])
                    posSJF = b;
            }

            tempSJF = burstTimeSJF[a];
            burstTimeSJF[a] = burstTimeSJF[posSJF];
            burstTimeSJF[posSJF] = tempSJF;

            tempSJF = processSJF[a];
            processSJF[a] = processSJF[posSJF];
            processSJF[posSJF] = tempSJF;
        }

        //First process has 0 waiting time
        waitingTimeSJF[0] = 0;

        //Calculating waiting time
        for (a=1; a<c; a++) {
            waitingTimeSJF[a] = 0;
            for (b=0; b<a; b++)
                waitingTimeSJF[a] += burstTimeSJF[b];
            totalSJF += waitingTimeSJF[a];
        }
    }


    // DISPLAY OF SJF
    public static void SJF_Display () {
        //Calculating Average waiting time
        waitAvgSJF = (float) totalSJF / c ;
        totalSJF = 0;

        System.out.print("\n-----------------------------------------------------------------------------------------------");
        System.out.println("\nShortest Job First Algorithm Results:");
        System.out.print("-----------------------------------------------------------------------------------------------");
        System.out.print("\nProcess\t       Burst Time\t        Waiting Time\t        Turnaround Time\n");
        System.out.print("-----------------------------------------------------------------------------------------------");

        for (a=0; a<c; a++) {
            tatSJF[a] = burstTimeSJF[a] + waitingTimeSJF[a]; //Calculating Turnaround Time
            totalSJF += tatSJF[a];
            System.out.println("\n" + "P" + processSJF[a] +"\t\t\t   "
                    + 	burstTimeSJF[a]  + "\t\t\t\t\t    "
                    + waitingTimeSJF[a]  + "\t\t\t \t\t     "
                    + tatSJF[a]);
        }

        //Calculation of Average Turnaround Time
        tatAvgSJF = (float) totalSJF / c;
        System.out.println("\nAverage Waiting Time = " + waitAvgSJF + " seconds");
        System.out.println("\nAverage Turnaround Time = " + tatAvgSJF + " seconds");
        System.out.println("-----------------------------------------------------------------------------------------------\n\n");
    }


    // LOGIC OF RR
    public static void RR_Logic () {
        Scanner s=new Scanner(System.in);

        System.out.println("\n");
        System.out.println("--- Round Robin Scheduling Algorithm: ---");

        // Input number of process
        System.out.print("Enter the number of process: ");
        n = s.nextInt();

        burstTime_RR = new int[n];
        waitingTime_RR = new int[n];
        completionTime_RR = new int[n];
        tat_RR = new int[n];
        remainingTime_RR = new int[n];
        partialTime_RR =new int[n];

        System.out.println("\nEnter the burst time for each process:");
        for (i=0; i<=n-1; i++)  {
            System.out.print("\tP" + (i+1) + ":  ");
            burstTime_RR[i] = s.nextInt();
            remainingTime_RR[i] = burstTime_RR[i];
        }

        //Input Quantum Time
        System.out.print("Enter the quantum time: ");
        quantumTime_RR = s.nextInt();
        while(true)  {
            for (i=0, count_RR=0; i<n; i++)  {
                temp_RR = quantumTime_RR;
                if (remainingTime_RR[i] == 0)  {
                    count_RR++;
                    continue;
                }

                if (remainingTime_RR[i] > quantumTime_RR)  {
                    remainingTime_RR[i] = remainingTime_RR[i] - quantumTime_RR;
                    partialTime_RR[i] = burstTime_RR[i] - remainingTime_RR[i];
                }


                else if (remainingTime_RR[i] >= 0)  {
                    temp_RR = remainingTime_RR[i];
                    remainingTime_RR[i] = 0;
                }

                sq = sq+temp_RR;
                completionTime_RR[i] = sq;
                waitingTime_RR[i] = completionTime_RR[i] - burstTime_RR[i];
                tat_RR[i] = completionTime_RR[i] - partialTime_RR[i];

            }

            if (n == count_RR)
                break;
        }
    }


    // DISPLAY OF RR
    public static void RR_Display ( ) {
        System.out.print("\n-----------------------------------------------------------------------------------------------");
        System.out.println("\nRobin Robin Scheduling Algorithm Results:");
        System.out.print("-----------------------------------------------------------------------------------------------");
        System.out.print("\nProcess\t       Burst Time\t        Waiting Time\t        Turnaround Time\n");
        System.out.print("-----------------------------------------------------------------------------------------------");

        for(i=0; i<n; i++)  {
            awt=awt+waitingTime_RR[i];
            atat=atat+ tat_RR[i];
            System.out.print("\n "+ "P"+(i+1) +"\t\t\t   "
                    + burstTime_RR[i] + "\t\t\t\t\t    "
                    + waitingTime_RR[i]  + "\t\t\t \t\t     "
                    + tat_RR[i] + "\n" );
        }

        awt=awt/n;
        atat=atat/n;

        System.out.println("\nAverage Waiting Time = "+awt + " seconds");
        System.out.println("\nAverage Turnaround Time = "+atat  + " seconds");
        System.out.println("-----------------------------------------------------------------------------------------------\n\n");
    }

    public static void FinalAWT ( ) {
        awtFinal1 = avg_waitFCFS + waitAvgSJF + awt;
        awtFinal2 = awtFinal1/3;

        attFinal1 = avg_TTFCFS + tatAvgSJF + atat;
        attFinal2 = attFinal1/3;


        System.out.print("\n-----------------------------------------------------------------------------------------------");
        System.out.println("\nBatch Queue Algorithm:");
        System.out.println("\nAverage Waiting Time = "+ awtFinal2 + " seconds");
        System.out.println("\nAverage Turnaround Time = "+ attFinal2  + " seconds");
        System.out.print("-----------------------------------------------------------------------------------------------");
    }




    public static void main(String[] args){
        group_Info ();
        FCFS_Logic ();
        SJF_Logic();
        RR_Logic ();
        FCFS_Display ();
        SJF_Display();
        RR_Display ();
        FinalAWT ();
    }
}
