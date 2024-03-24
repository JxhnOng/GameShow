
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
    static int ruleCount=0;
    static int[] songs= {1,2,3,4,5,6,7,8,9,10};
    static int[] answers= {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
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
                    //todo
                    //view high schore method
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
        System.out.println("3. If you answer correctly within 20 seconds, you will earn a point. Otherwise you earn none.");
        System.out.println("Good luck and have fun!\n");
        ruleCount++;
        menu();

    }
    public static void game() {
        
        System.out.println("Hello Welcome to Name That Tune!");
        if(ruleCount==0){
            try {
                Thread.sleep(1000);
                System.out.println("The rules are simple:");
                Thread.sleep(1000);
                System.out.println("1. A 10 second clip of a song will be played.");
                Thread.sleep(3000);
                System.out.println("2. You will have 20 seconds to think and decide which song is right from 4 options.");
                Thread.sleep(4000);
                System.out.println(
                        "3. If you answer correctly within 20 seconds, you will earn a point. Otherwise you earn none.");
                Thread.sleep(2000);
                System.out.println("Good luck and have fun!\n");
                ruleCount++;
    
                menu();
    
            } catch (InterruptedException e) {
                System.out.println("Error: ");
                e.printStackTrace();
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Input, please enter an integer");
            }
        }
        
        else{
            System.out.println("Starting in 3 seconds!");

            //array
            try {
                Thread.sleep(3000);
                System.out.println("Song #1: Playing.");
                randomizer();
                for(int i = 0; i <songs.length;i++){
                    System.out.println("Song #"+songs[i]+ "answer: "+answers[i]);
                }
                menu();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InputMismatchException e){
                System.out.println("Error: invalid input.");
                in.nextLine();
            }
        }
        
    }

    public static void saveScore() {

    }
    public static void randomizer(){
        int n = songs.length;
        for (int i = 0; i < n; i++) {
            // Generate a random index between i and n - 1
            int randomNum = (int) (Math.random() * (n - i)) + i;
            // switch around the numbers with cases.
            int temp = songs[i];
            songs[i] = songs[randomNum];
            answers[i] = answers[randomNum];
            songs[randomNum] = temp;
            answers[randomNum] = temp;
        }

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