//Patrick Fleet
// COMP 3715 project part 3
//CODE MAY NOT WORK, BECAUSE DATABASE DOESN'T EXIST
package classifier;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CarClassifier {
    public static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    public static final String DB_URL="jdbc:mysql://localhost:3306/patrick_fleet";
    public static final String USER="root";
    public static final String PASS="pass";
    
    public static void main(String [] args)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet carInfo=null;
		
	try{             
            Class.forName(JDBC_DRIVER);
        }
        catch(ClassNotFoundException c){
            System.out.println("Class not found");
        }

        //STEP 3: Open a connection
        try
        {
            System.out.println("Connecting to database...");
        
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

        //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
        }
        catch (SQLException s){
            
        }
        try{
            String sql;
            sql="SELECT * FROM carTransact";
            String classifiedSat;
            
            carInfo=stmt.executeQuery(sql);
        }
        catch (SQLException e){
            Logger.getLogger(CarClassifier.class.getName()).log(Level.SEVERE, null, e);
        }
            double numCorrect=0;//number of times rule was correct
            double numWrong=0;
            int numLowSat=0;
            int numMedSat=0;
            int numHighSat=0;
            int numVeryHighSat=0;
            int numClassLowSat=0;
            int numClassMedSat=0;
            int numClassHighSat=0;
            int numClassVeryHighSat=0;
            int numLowRecalled=0;// number of objects of actual low satifaction that were classified as having low satisfaction
            int numMedRecalled=0;
            int numHighRecalled=0;
            int numVeryHighRecalled=0;
            int numLowPrec=0;//number of objects classified as low satisfaction that had low satisfaction
            int numMedPrec=0;
            int numHighPrec=0;
            int numVeryHighPrec=0;
            String usersGen="";
            int thisAge;
            int thisPrice;
            String thisMake="";
            String thisModel="";
            String thisType="";
            int thisMainCost;
            String thisColor="";
            String tSize="";
            int thisDoors;
            int thisSeats;
            String thisSafety="";
            String thisSatisfaction="";
            String classifiedSat;
            try{
		while (carInfo.next()){
                    
                
                    usersGen=carInfo.getString("gender");
                    thisAge=carInfo.getInt("age");
                    thisPrice=carInfo.getInt("carprice");
                    thisMake=carInfo.getString("make");
                    thisModel=carInfo.getString("model");
                    thisType=carInfo.getString("type");
                    thisMainCost=carInfo.getInt("carmaintenenceCost");
                    thisColor=carInfo.getString("color");
                    tSize=carInfo.getString("trunkSize");
                    thisDoors=carInfo.getInt("numdoors");
                    thisSeats=carInfo.getInt("numseats");
                    thisSafety=carInfo.getString("safety");
                    thisSatisfaction=carInfo.getString("satisfaction");





            //here: use rules generated to fill in classifiedSat variable
                    if (thisMainCost>=1739 && "small".equals(tSize) && "low".equals(thisSafety) && thisMainCost>=1452 && thisPrice>=12628)
                            classifiedSat="low";
                    else if (thisMainCost>=1538 && "small".equals(tSize) && "medium".equals(thisSafety))
                            classifiedSat="low";
                    else if (thisMainCost>=2032 && "low".equals(thisSafety) && thisMainCost>=1538)
                            classifiedSat="low";
                    else if (thisMainCost>=1240 && "low".equals(thisSafety) && "blue".equals(thisColor))
                            classifiedSat="low";
                    else if (thisMainCost<=950 && "high".equals(thisSafety))
                            classifiedSat="veryhigh";
                    else if (thisMainCost<=930 && "red".equals(thisColor))
                            classifiedSat="veryhigh";
                    else if (thisMainCost<=633 && "large".equals(tSize))
                            classifiedSat="veryhigh";
                    else if (thisMainCost<=446 && "medium".equals(tSize))
                            classifiedSat="veryhigh";
                    else if (thisMainCost<=1579 && "high".equals(thisSafety))
                            classifiedSat="high";
                    else if (thisMainCost<=1211 && "midsize".equals(thisType))
                            classifiedSat="high";
                    else
                            classifiedSat="medium";
			
			
                    if (classifiedSat.equals(thisSatisfaction))
                    {
                        numCorrect=numCorrect+1;
                        
                        switch (thisSatisfaction) {
                            case "low":
                                numLowRecalled++;
                                numLowSat++;
                                break;
                            case "medium":
                                numMedRecalled++;
                                numMedSat++;
                                break;
                            case "high":
                                numHighRecalled++;
                                numHighSat++;
                                break;
                            case "veryhigh":
                                numVeryHighRecalled++;
                                numVeryHighSat++;
                                break;
                        }
                        switch (classifiedSat) {
                            case "low":
                                numLowPrec++;
                                numClassLowSat++;
                                break;
                            case "medium":
                                numMedPrec++;
                                numClassMedSat++;
                                break;
                            case "high":
                                numHighPrec++;
                                numClassHighSat++;
                                break;
                            case "veryhigh":
                                numVeryHighPrec++;
                                numClassVeryHighSat++;
                                break;
                        }
                    }		
		
                    else if (!thisSatisfaction.equals(classifiedSat))
                    {
				numWrong++;
                                switch (thisSatisfaction) {
                                    case "low":
                                        numLowSat++;
                                        break;
                                    case "medium":
                                        numMedSat++;
                                        break;
                                    case "high":
                                        numHighSat++;
                                        break;
                                    case "veryhigh":
                                        numVeryHighSat++;
                                        break;
                                }
                                switch (classifiedSat) {
                                    case "low":
                                        numClassLowSat++;
                                        break;
                                    case "medium":
                                        numClassMedSat++;
                                        break;
                                    case "high":
                                        numClassHighSat++;
                                        break;
                                    case "veryhigh":
                                        numClassVeryHighSat++;
                                        break;
                                }
                    }
			
		    System.out.println("For a "+thisMake+" "+thisModel+" "+thisType);//primary key
                    
                    System.out.println("Actual satisfaction: "+thisSatisfaction);
                    System.out.println("Classified satisfaction: "+classifiedSat);
                    System.out.println("");
		}
                
                
		carInfo.close();
		stmt.close();
		conn.close();
             }
             catch (SQLException s){
                  System.out.println("SQL Exception caught.");
             }
             double recallLow=0;//percentage of low recalled
             double recallMedium=0;
             double recallHigh=0;
             double recallVeryHigh=0;
             double precLow=0;
             double precMedium=0;
             double precHigh=0;
             double precVeryHigh=0;
             if (numLowSat!=0)// to avoid divide by 0 error, if it is equal to 0, then it's 0, which was set above
                 recallLow=((double)numLowRecalled/(double)numLowSat)*100.0;//in percent
             if (numMedSat!=0)
                 recallMedium=((double)numMedRecalled/(double)numMedSat)*100.0;//in percent             
             if (numHighSat!=0)
                 recallHigh=((double)numHighRecalled/(double)numHighSat)*100.0;//in percent             
             if (numVeryHighSat!=0)
                 recallVeryHigh=((double)numVeryHighRecalled/(double)numVeryHighSat)*100.0;//in percent
             if (numClassLowSat!=0)
                precLow=((double)numLowPrec/(double)numClassLowSat)*100.0;
             if (numClassMedSat!=0)
                precMedium=((double)numMedPrec/(double)numClassMedSat)*100.0;
             if (numClassHighSat!=0)
                precHigh=((double)numHighPrec/(double)numClassHighSat)*100.0;
             if (numClassVeryHighSat!=0)
                precVeryHigh=((double)numVeryHighPrec/(double)numClassVeryHighSat)*100.0;
             double accuracy=(numCorrect/(numCorrect+numWrong))*100.0;
             
             
             if (numCorrect+numWrong==0)//no data in database
                 System.out.println("Accuracy not available, no data.");
             else{
                 System.out.println("Overall accuracy: "+accuracy+"%");
                 System.out.println(numCorrect+"/"+(numCorrect+numWrong));
             }
             System.out.println("Recall of actual low satisfaction by rules: "+recallLow+"%");
             System.out.println("Recall of actual medium satisfaction by rules: "+recallMedium+"%");
             System.out.println("Recall of actual high satisfaction by rules: "+recallHigh+"%");
             System.out.println("Recall of actual very high satisfaction by rules: "+recallVeryHigh+"%");
             System.out.println("Precision of classified low satisfaction according to data: "+precLow+"%");
             System.out.println("Precision of classified medium satisfaction according to data: "+precMedium+"%");
             System.out.println("Precision of classified high satisfaction according to data: "+precHigh+"%");
             System.out.println("Precision of classified very high satisfaction according to data: "+precVeryHigh+"%");

    }
}
