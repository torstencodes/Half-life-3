import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String user) {
        super(user);
        // TODO Auto-generated constructor stub
    }

    public boolean setRole(final String userN, final int num) throws IOException{
        boolean success = false;
        try {
            
            File input = new File("userlogin.csv");
            File temp = new File("templogin.csv");
        
            Scanner reader = new Scanner(input);
            FileWriter write = new FileWriter(temp, true);
            
            String dummy;
            
            while ((dummy = reader.nextLine()) != null) {
                String[] commInfo = dummy.split(",");
                if (commInfo[0].equals(userN)) {
                    success = true;
                    dummy = commInfo[0] + "," + commInfo[1] + "," + num;
                    write.append(dummy);
                } else {
                    write.append(dummy);
                }
            }
            write.close();
            reader.close();
            temp.renameTo(input);
        } catch (IOException e) {           
            // TODO Auto-generated catch block
            e.printStackTrace();
            return success;
        }
               
        return success;
    }
    
    public boolean addGame(final String gameTitle, final String imgPath) {
        try {
            int id;
            File games = new File("gameList.csv");
            Scanner reader = new Scanner(games);
            FileWriter write = new FileWriter(games);
            String dummy;
            for (id = 0; (dummy = reader.nextLine()) != null; id++) { }
            write.append(id + ",false," + gameTitle + "," + imgPath);
            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeGame(final Game g) {
        boolean success = false;
        try {       
            File input = new File("gameList.csv");
            File temp = new File("tempGames.csv");
        
            Scanner reader = new Scanner(input);
            FileWriter write = new FileWriter(temp, true);
            
            String dummy;
            
            while ((dummy = reader.nextLine()) != null) {
                String[] commInfo = dummy.split(",");
                if (Integer.parseInt(commInfo[0]) == g.getGameId()) {
                    success = true;
                    continue;
                } else {
                    write.append(dummy);
                }
            }
            write.close();
            reader.close();
            temp.renameTo(input);
        } catch (IOException e) {           
            // TODO Auto-generated catch block
            e.printStackTrace();
            return success;
        }
                
        return success;
    }
    
    public boolean showGame(Game g) {
        boolean success = false;
        try {       
            File input = new File("gameList.csv");
            File temp = new File("tempGames.csv");
        
            Scanner reader = new Scanner(input);
            FileWriter write = new FileWriter(temp, true);
            
            String dummy;
            
            while ((dummy = reader.nextLine()) != null) {
                String[] commInfo = dummy.split(",");
                if (Integer.parseInt(commInfo[0]) == g.getGameId()) {
                    write.write(commInfo[0] + ",true,");
                    for (int i = 1; i < commInfo.length; i++) {
                        write.write(commInfo[i]);
                    }
                    write.write("\n");
                    success = true;
                    continue;
                } else {
                    write.append(dummy);
                }
            }
            write.close();
            reader.close();
            temp.renameTo(input);
        } catch (IOException e) {           
            // TODO Auto-generated catch block
            e.printStackTrace();
            return success;
        }
                
        return success;
    }
    
    public boolean hideGame(Game g) {
        boolean success = false;
        try {       
            File input = new File("gameList.csv");
            File temp = new File("tempGames.csv");
        
            Scanner reader = new Scanner(input);
            FileWriter write = new FileWriter(temp, true);
            
            String dummy;
            
            while ((dummy = reader.nextLine()) != null) {
                String[] commInfo = dummy.split(",");
                if (Integer.parseInt(commInfo[0]) == g.getGameId()) {
                    write.write(commInfo[0] + ",false,");
                    for (int i = 1; i < commInfo.length; i++) {
                        write.write(commInfo[i]);
                    }
                    write.write("\n");
                    success = true;
                    continue;
                } else {
                    write.append(dummy);
                }
            }
            write.close();
            reader.close();
            temp.renameTo(input);
        } catch (IOException e) {           
            // TODO Auto-generated catch block
            e.printStackTrace();
            return success;
        }
                
        return success;
    }
}
