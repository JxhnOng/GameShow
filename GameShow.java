
import java.io.InputStream;
import java.util.InputMismatchException;
//import java.util.InputMismatchException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class GameShow {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

    }

    public static void menu() {
        int choice;
        while (true) {
            try {
                System.out.println("Name That Tune\n");
                System.out.println("1) Play");
                System.out.println("2) Highscore");
                System.out.println("3) Quit");
                choice = in.nextInt();
                if (choice == 1) {
                    game();
                } else if (choice == 2) {
                    //todo
                    //view high schore method
                } else if (choice == 3) {
                    System.out.println("Thanks for Playing! ");
                    System.exit(0);
                } else {
                    System.out.println("Error please enter an integer.");
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

    public static void game() {
        System.out.println("Hello Welcome to Name That Tune!");
        try {
            Thread.sleep(2000);
            System.out.println("The rules are simple:");
            Thread.sleep(3000);
            System.out.println("1. A 10 second clip of a song will be played.");
            Thread.sleep(3000);
            System.out.println("2. You will have 20 seconds to think and decide which song is right from 4 options.");
            Thread.sleep(4000);
            System.out.println(
                    "3. If you answer correctly within 20 seconds, you will earn a point. Otherwise you earn none.");
            Thread.sleep(2000);
            System.out.println("Good luck and have fun!");

            menu();

        } catch (InterruptedException e) {
            System.out.println("Error: ");
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid Input, please enter an integer");
        }
    }

    public static void saveScore() {

    }

    public static void music(int songNum) {
        try {
            InputStream music = GameShow.class.getResourceAsStream("./song" + songNum + ".wav"); // Format "./music.wav"
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
            // clip.loop(Clip.LOOP_CONTINUOUSLY); // Music never stops
        }

        catch (Exception e) {
            System.out.println("Music file not found\n"); // If the music doesn't work.
        }

    }

}