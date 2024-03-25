
import java.io.InputStream;


import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class GameShow {
    static Scanner in = new Scanner(System.in);
    static int ruleCount=0;
    static int[] songs= {1,2,3,4,5,6,7,8,9,10};
    //1 my name is w
    //2 ridin dirty w
    //3 sound of the police w
    //4 Still dre w
    //5 yeah
    //6 cha cha slide w
    //7 hotline bling w
    //8 it was a good day w
    //9 gods plan w
    //10 in da club w
    static int[] answers= {2,1,3,4,3,4,2,3,1,2};

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
        int score =0;
        
        if(ruleCount==0){
            try {
                System.out.println("The rules are simple:");
                System.out.println("1. A 10 second clip of a song will be played. This will only play once.");
                System.out.println("2. You will have to think and decide which song is right from 4 options.");
                System.out.println("3. If you answer correctly, you will earn a point. Otherwise you earn none.");
                System.out.println("Good luck and have fun!\n");
                ruleCount++;
                game();
            }
             catch (Exception e) {
                System.out.println("Error: Invalid Input, please enter an integer");
            }
        }
        else{
            randomizer();
            System.out.println("Go!");
            
            for(int songCount=0;songCount<songs.length;songCount++){
                int guess;
                try {
                    System.out.println("Song #"+ (songCount+1)+": Playing. Please wait... :" +songCount);
                    music(songs[(songCount+1)]);
                    //Thread.sleep(10000);
                    
                    if(songs[songCount] == 1 ){ //1my name is
                        System.out.println("1) Slim Shady \t 2) My name is \n3) Answer \t 4) Answer");
                    }
                    else if(songs[songCount] == 2 ){//2ridin dirty
                        System.out.println("1) Ridin' Dirty          2) My name is \n3) Answer \t 4) Answer");
                    }
                    else if(songs[songCount] == 3 ){//3 sound of the police
                        System.out.println("1) Slim Shady \t 2) My name is \n3) Sound of The Police \t 4) Answer");
                    }
                    else if(songs[songCount] == 4 ){//4 Still dre
                        System.out.println("1) Slim Shady \t 2) My name is \n3) Guess Who's Back \t 4) Still Dre");
                    }
                    else if(songs[songCount] == 5 ){//5 yeah
                        System.out.println("1) Slim Shady \t 2) My name is \n3) Yeah! \t 4) Answer");
                    }
                    else if(songs[songCount] == 6 ){//6 cha cha slide
                        System.out.println("1) Slim Shady \t 2) My name is \n3) Answer \t 4) Cha Cha Slide");
                    }
                    else if(songs[songCount] == 7 ){//7 hotline bling
                        System.out.println("1) You Used To Call Me \t 2) Hotline Bling \n3) Cellphone \t 4) Answer");
                    }
                    else if(songs[songCount] == 8 ){//8 it was a good day
                        System.out.println("1) Slim Shady \t 2) My name is \n3) It Was a Good Day \t 4) Answer");
                    }
                    else if(songs[songCount] == 9 ){//9 gods plan
                        System.out.println("1) Gods Plan \t 2) My name is \n3) Answer \t 4) Answer");
                    }
                    else if(songs[songCount] == 10 ){//10 in da club
                        System.out.println("1) It's Yo Birthday \t 2) In Da Club \n3) We Gonna Party \t 4) Birthday");
                    }
                    else{
                        System.out.println("Error?");
                    }
                     //Forces the user to listen to the song before inputting to avoid song overlap.
                     while(true){
                        System.out.print("Guess: ");
                        guess = in.nextInt();
                        if(guess == answers[songCount]){
                            System.out.println("Correct...\n");
                            score++;
                            break;
                        }
                        else if(guess > 0 && guess < 5){
                            System.out.println("Continue...\n");
                            break;
                        }
                        else{
                            System.out.println("That is not an option, please select a proper option.");
                        }
                    }
                   
                } 
                catch (Exception e){
                    System.out.println("Error: invalid input.");
                    in.nextLine();
                }
            }
                System.out.println("Final Score: "+score+"/10");
            }
    }

    public static void saveScore(int score) {

    }
    public static void randomizer(){
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
            InputStream music = GameShow.class.getResourceAsStream("./song" + songNum + ".wav"); // Format "./song(number).wav"
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