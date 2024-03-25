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
    static int ruleCount = 0;
    static int[] songs = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    // 1 my name is 2
    // 2 ridin dirty 4
    // 3 sound of the police 3
    // 4 Still dre
    // 5 yeah
    // 6 cha cha slide
    // 7 hotline bling 1
    // 8 it was a good day 5
    // 9 gods plan 6
    // 10 in da club
    static int[] answers = { 2, 1, 3, 4, 3, 4, 2, 3, 1, 2 };

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        try {
            initializeFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int choice;
        while (true) {
            try {
                System.out.println("Name That Tune\n");
                System.out.println("1) Play");
                System.out.println("2) Highscore");
                System.out.println("3) Rules");
                System.out.println("4) Quit");
                System.out.print("Select Option: ");
                choice = in.nextInt();
                if (choice == 1) {

                    game();
                } else if (choice == 2) {
                    highscore();
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

    public static void rules() {
        System.out.println("The rules are simple:");
        System.out.println("1. A 10 second clip of a song will be played.");
        System.out.println("2. You will have 20 seconds to think and decide which song is right from 4 options.");
        System.out.println(
                "3. If you answer correctly within 20 seconds, you will earn a point. Otherwise you earn none.");
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
        if (ruleCount == 0) {
            try {
                System.out.println("The rules are simple:");
                System.out.println("1. A 10 second clip of a song will be played. This will only play once.");
                System.out.println("2. You will have to think and decide which song is right from 4 options.");
                System.out.println("3. If you answer correctly, you will earn a point. Otherwise you earn none.");
                System.out.println("Good luck and have fun!\n");
                ruleCount++;
                game();
            } catch (Exception e) {
                System.out.println("Error: Invalid Input, please enter an integer");
            }
        } else {
            randomizer();
            System.out.println("Go!");

            for (int songCount = 0; songCount < songs.length; songCount++) {

                try {
                    System.out.println("Song #" + (songCount + 1) + ": Playing. Please wait... :");
                    music(songs[songCount]);
                    Thread.sleep(10000);

                    if (songs[songCount] == 0) { // 1 my name is
                        System.out.println("1) Slim Shady \t 2) My Name is \n3) What Is My Name \t 4) What's my name");
                    } else if (songs[songCount] == 1) {// 2 ridin dirty
                        System.out.println("1) Ridin' Dirty  2) They See Me Rollin' \n3) Answer \t 4) They Hatin'");
                    } else if (songs[songCount] == 2) {// 3 sound of the police
                        System.out
                                .println("1) Police \t 2) The Beast \n3) Sound of The Police \t 4) Sound of The Beast");
                    } else if (songs[songCount] == 3) {// 4 Still dre
                        System.out.println(
                                "1) Snoopdog and Dre \t 2) It's Still Dre Day \n3) Guess Who's Back \t 4) Still Dre");
                    } else if (songs[songCount] == 4) {// 5 yeah
                        System.out.println("1) Usher \t 2) In The Club \n3) Yeah! \t 4) A Town");
                    } else if (songs[songCount] == 5) {// 6 cha cha slide
                        System.out.println("1) Funky \t 2) Clap Your Hands \n3) Criss Cross \t 4) Cha Cha Slide");
                    } else if (songs[songCount] == 6) {// 7 hotline bling
                        System.out
                                .println("1) You Used To Call Me \t 2) Hotline Bling \n3) Cellphone \t 4) You Used To");
                    } else if (songs[songCount] == 7) {// 8 it was a good day
                        System.out.println(
                                "1) Just Wakin' Up \t 2) It Was a Good Morning \n3) It Was a Good Day \t 4) Today Seems Kinda Odd");
                    } else if (songs[songCount] == 8) {// 9 gods plan
                        System.out.println("1) Gods Plan \t 2) Bad Things \n3) My Plan \t 4) They Wish");
                    } else if (songs[songCount] == 9) {// 10 in da club
                        System.out.println("1) It's Yo Birthday \t 2) In Da Club \n3) We Gonna Party \t 4) Birthday");
                    } else {
                        System.out.println("Error?");
                    }

                    while (true) {
                        System.out.print("Guess: ");
                        guess = in.nextInt();
                        if (guess == answers[songCount]) {
                            System.out.println("Continue...\n");
                            score++;

                            break;
                        } else if (guess > 0 && guess < 5) {
                            System.out.println("Continue...\n");

                            break;
                        } else {
                            System.out.println("That is not an option, please select a proper option.");
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    in.nextLine();
                }
            }

            while (a == 0) {
                try {
                    System.out.println("Final Score: " + score + "/10");
                    System.out.println("Would you like to save your score?");
                    System.out.println("1) Yes");
                    System.out.println("2) No");
                    toSave = in.nextInt();
                    if (toSave == 1) {
                        while (true) {
                            System.out.println("Enter your name: ");
                            name = in.nextLine();
                            if (name.isEmpty()) {
                                System.out.println("Please Enter a proper name.");
                            } else {
                                saveScore(name, score);
                                break;
                            }

                        }

                        a++;
                        // save method
                    } else if (toSave == 2) {
                        a++;
                        menu();
                    } else {
                        System.out.println("Please select a proper option");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    in.nextLine();
                }
            }

        }
    }

    public static void saveScore(String name, int score) {
        try {
            File file = new File("highscores.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(file, true)); // Append mode
            writer.println(name.toLowerCase() + "," + score);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving high score: " + e.getMessage());
        }
    }

    public static void highscore() {
        try {
            File file = new File("highscores.txt");
            Scanner reader = new Scanner(file);
            System.out.println("High Scores:");
            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(",");
                if (data.length == 2) {
                    String name = data[0].trim().toLowerCase();
                    int score = Integer.parseInt(data[1].trim());
                    System.out.println(name + ": " + score);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No high scores available.");
        }
    }

    public static void initializeFile() throws FileNotFoundException {
        // if
        try {
            File file = new File("./highscore.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.write("");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
            ;
        }
    }

    public static void randomizer() {
        int n = songs.length;
        for (int i = 0; i < n; i++) {
            // Generate a random index between i and n - 1
            int randomNum = (int) (Math.random() * (n - i)) + i;
            // switch around the numbers with cases.
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
            InputStream music = GameShow.class.getResourceAsStream("./song_" + song + ".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(music);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
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