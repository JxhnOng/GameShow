//Author: John Ong
//Date: March 25, 2024
//Description: Game show coding assignment. Game: Name That tune
//Challenge Features added: Music, Save data.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class GameShow {
    static Scanner in = new Scanner(System.in);
    //Variables that will be used throughout the entire program
    static int ruleCount = 0;
    static int[] songs = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static int[] answers = { 2, 1, 3, 4, 3, 4, 2, 3, 1, 2 };
    /*  Song File # | Song Answer | Answer #
     0 | My name is | 2
     1 | Ridin' dirty | 1
     2 | Sound of da police | 3
     3 | Still Dre | 4
     4 | Yeah! | 3
     5 | cha cha slide | 4
     6 | hotline bling | 2
     7 | it was a good day | 3
     8 | gods plan | 1
     9 | In da club | 2
    */
    public static void main(String[] args) {
        menu();
    }

    public static void menu() { //Menu
        int choice;
        try {
            initializeFile(); // Create scores file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Welcome to: \nName That Tune\n");
                System.out.println("1) Play");
                System.out.println("2) Scores");
                System.out.println("3) Rules");
                System.out.println("4) Quit");
                System.out.print("Select Option: ");
                choice = in.nextInt();
                if (choice == 1) {
                    game();
                } else if (choice == 2) {
                    score();
                } else if (choice == 3) {
                    rules();
                } else if (choice == 4) {
                    System.out.println("Thanks for Playing! ");
                    System.exit(0);
                } else {
                    System.out.println("Error: Please select a proper option.");
                    in.nextLine();
                    menu();
                }
            }
            catch (Exception e) {
                System.out.println("Error: Invalid Input.");
                in.nextLine();
            }
        }

    }

    public static void rules() { //Rule page
        System.out.println("The rules are simple:");
        System.out.println("1. A 10 second clip of a song will be played.");
        System.out.println("2. You will have 20 seconds to think and decide which song is right from 4 options.");
        System.out.println("3. If you answer correctly within 20 seconds, you will earn a point. Otherwise you earn none.");
        System.out.println("Good luck and have fun!\n");
        ruleCount++;
        menu();
    }

    public static void game() {
        int score = 0;
        int guess;
        int toSave;
        int a = 0;
        String name;
        if (ruleCount == 0) { //If user hasn't read the rules, this will force them to read it.
            try {
                System.out.println("\nThe rules are simple:");
                Thread.sleep(1000);
                System.out.println("1. A 10 second clip of a song will be played. This will only play once.");
                Thread.sleep(1000);
                System.out.println("2. You will have to think and decide which song is right from 4 options.");
                Thread.sleep(1000);
                System.out.println("3. If you answer correctly, you will earn a point. Otherwise you earn none.");
                Thread.sleep(1000);
                System.out.println("Good luck and have fun!\n");
                Thread.sleep(10000);
                ruleCount++;
                game();
            } catch (Exception e) {
                System.out.println("Error: Invalid Input, please enter an integer");
            }
        } else { //If user has't read the rules, it wont print it.
            randomizer();
            System.out.println("Start!");
            for (int songCount = 0; songCount < songs.length; songCount++) {
                try {
                    System.out.println("Song #" + (songCount + 1) + ": Playing. Please wait... :");
                    music(songs[songCount]);
                    Thread.sleep(10000);//Wait for song to play to avoid overlap.
                    //Song answer options
                    if (songs[songCount] == 0) { //  my name is
                        System.out.println("1) Slim Shady \t 2) My Name is \n3) What Is My Name \t 4) What's my name");
                    } else if (songs[songCount] == 1) {//  ridin dirty
                        System.out.println("1) Ridin' Dirty  2) They See Me Rollin' \n3) Answer \t 4) They Hatin'");
                    } else if (songs[songCount] == 2) {//  sound of da police
                        System.out
                                .println("1) Police \t 2) The Beast \n3) Sound of Da Police \t 4) Sound of The Beast");
                    } else if (songs[songCount] == 3) {//  Still dre
                        System.out.println(
                                "1) Snoopdog and Dre \t 2) It's Still Dre Day \n3) Guess Who's Back \t 4) Still Dre");
                    } else if (songs[songCount] == 4) {//  yeah
                        System.out.println("1) Usher \t 2) In The Club \n3) Yeah! \t 4) A Town");
                    } else if (songs[songCount] == 5) {//  cha cha slide
                        System.out.println("1) Funky \t 2) Clap Your Hands \n3) Criss Cross \t 4) Cha Cha Slide");
                    } else if (songs[songCount] == 6) {//  hotline bling
                        System.out
                                .println("1) You Used To Call Me \t 2) Hotline Bling \n3) Cellphone \t 4) You Used To");
                    } else if (songs[songCount] == 7) {//  it was a good day
                        System.out.println(
                                "1) Just Wakin' Up \t 2) It Was a Good Morning \n3) It Was a Good Day \t 4) Today Seems Kinda Odd");
                    } else if (songs[songCount] == 8) {//  gods plan
                        System.out.println("1) Gods Plan \t 2) Bad Things \n3) My Plan \t 4) They Wish");
                    } else if (songs[songCount] == 9) {//  in da club
                        System.out.println("1) It's Yo Birthday \t 2) In Da Club \n3) We Gonna Party \t 4) Birthday");
                    } else {
                        System.out.println("Error: You shouldn't be able to see this....");
                    }
                    while (true) {
                        System.out.print("Guess: ");
                        if(in.hasNextInt()){
                            guess = in.nextInt();
                            //Ensures the user does not input anything other than a number from 1-4
                            if (guess == answers[songCount]) {
                                System.out.println("Continue...\n");
                                score++;
                                break;
                            } else if (guess > 0 && guess < 5 && guess == (int)guess) {
                                System.out.println("Continue...\n");
                                break;
                            } else {
                                System.out.println("That is not an option, please select a proper option.");
                                
                            }
                        }else {
                            System.out.println("Error: Invalid input. Please enter an integer.");
                            in.nextLine(); // To avoid infinite loop
                        }
                        
                    }
                } catch (Exception e) {
                    System.out.println("Error: Invalid Input" );
                    in.nextLine();
                }
            }
            //Makes sure the user enters yes or no
            while (a == 0) {
                try {
                    System.out.println("Final Score: " + score + "/10");
                    if(score>5){
                        System.out.println("Status: You Win! Be proud of yourself!");
                    }
                    else if(score==0){
                        System.out.println("Status: You Lost... Horribly! Do NOT be proud of yourself!");
                    }
                    else{
                        System.out.println("Status: You Lost... Try harder next time...");
                    }
                    //Asks the user to save the score
                    System.out.println("\nWould you like to save your score?");
                    System.out.println("1) Yes");
                    System.out.println("2) No");
                    toSave = in.nextInt();
                    in.nextLine();
                    if (toSave == 1) {
                        while (true) {
                            System.out.println("Enter your name: ");
                            name = in.nextLine();

                            if ((name.trim()).isEmpty()) { //To ensure the user doesn't just enter an empty name
                                System.out.println("Please Enter a proper name.");
                            } else {
                                saveScore(name, score);
                                break;
                            }
                        }
                        a++;
                    } else if (toSave == 2) {
                        a++;
                        System.out.println("Score Discarded...\n");
                        menu();
                    } else {
                        System.out.println("Please select a proper option");
                    }
                } catch (Exception e) {
                    System.out.println("Error: Invalid Input");
                    in.nextLine();
                }
            }

        }
    }

    public static void saveScore(String name, int score) {
        try {
            File file = new File("./scores.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println(name.toLowerCase() + "," + score); //Write name and score to file
            writer.close();
            System.out.println("Score saved\n");
        } catch (IOException e) {
            System.out.println("Error occurred while saving score: " + e.getMessage());
        }
    }

    public static void score() {
        try {
            File file = new File("./scores.txt"); //Look for scores
            Scanner reader = new Scanner(file);
            System.out.println("\nName | Score");
            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(",");
                if (data.length == 2) {
                    String name = data[0].trim().toLowerCase(); // Sets to lowercase
                    int score = Integer.parseInt(data[1].trim());
                    System.out.println(name + ": " + score); // Print out file data
                }
            }
            System.out.println("Enter any key to Continue...");
            in.next();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No scores available.");
        }
    }

    public static void initializeFile() throws FileNotFoundException {
        try {
            File file = new File("./scores.txt");
            if (file.exists()) {
                if (file.length() == 0) { // Check if file is empty
                    PrintWriter writer = new PrintWriter(file);
                    writer.write(""); // Write empty string to file
                    writer.close();
                } else {
                    // File already exists. Skipping initialization
                }

            } else { // If file does not exist, create it
                PrintWriter writer = new PrintWriter(file);
                writer.write(""); // Write empty string to file
                writer.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void randomizer() {
        int n = songs.length;
        for (int i = 0; i < n; i++) {
            int randomNum = (int) (Math.random() * (n - i)) + i;
            // switch around the numbers for random song order.
            int temp = songs[i];
            int temp2 = answers[i];
            songs[i] = songs[randomNum];
            answers[i] = answers[randomNum];
            songs[randomNum] = temp;
            answers[randomNum] = temp2;
        }

    }

    public static void music(int songNum) {
        try {
            String song = Integer.toString(songNum);
            InputStream music = GameShow.class.getResourceAsStream("./song_" + song + ".wav"); //Looks for song file depending on what part it is on
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(music);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Volume adjustment
            if (clip != null || clip.isActive()) {
                clip.stop();
                clip.flush();
            }
            clip.start(); // Starts the music
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // If the music doesn't work.
        }
    }
}