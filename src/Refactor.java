import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * This is the main class for the "CDKGlobal Holistic Inventory Refactor Program". AKA CHIRP.
 * The purpose of this program is to consolodate information that is found both online in the CDKGlobal web app and the
 * CDK Program used within the Kingman Chevrolet-Buick Dealership. This tool is primarily used by the employee in charge 
 * of maintianing the dealership's web inventory. Every vehicle must have a photograph on the website if they are on the 
 * physical lot. Information regarding a car's photo status is found on the CDKGlobal app. However, information about the
 * car's physical status is found on the CDK app, maintained by the New and Used car managers. This app takes exported data
 * from both programs and produces two lists: one containing the photo count and physical status of every car the dealership
 * owns, and one containing the physical status of all cars with 1 picture or less.
 *
 * This program runs on the assumption that it is receiving input from a windows bat file. This file will be enclosed here:<br>
 * <code> @echo on<br/>
 * setlocal EnableDelayedExpansion<br/>
 * <br/>
 * set "params=!cmdcmdline!"<br/>
 * set "params=!params:~0,-1!"<br/>
 * set "params=!params:*" =!"<br/>
 * set count=0<br/>
 * <br/>
 * for %%G IN (!params!) do (<br/>
 *   set /a count+=1<br/>
 *   set "item_!count!=%%~G"<br/>
 * )<br/>
 * <br/>
 * for /L %%n in (1,1,!count!) DO (<br/>
 *   set "list=!list!"!item_%%n!" "<br/>
 * )<br/>
 * <br/>
 * java Refactor !list!</code><br/>
 *
 * @author Tyler Cole (@superblah12)
 * @version 2.0
 * @since 2017-09-28
 *
 */

public class Refactor {
	public static void main(String[] args ){
		List<Car> cars = new ArrayList<Car>();
		List<CDK> cdk = new ArrayList<CDK>();
		String webInventory = "";
		String cdkInventory = "";
		String dest = "";
		
		for(String file : args){
			String[] fold = file.split("\\\\");
			//
			if(dest.equals("")) {
				for(int i = 0; i < fold.length-1; i++)
					dest = dest + fold[i] + "\\";
			}
			if(fold[fold.length-1].equals("vehicles.xls"))
				webInventory = file;
			else
				cdkInventory = file;
		}
		
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		String tabSplitBy = "\t";
		
		try {
			br = new BufferedReader(new FileReader(webInventory));
            while ((line = br.readLine()) != null) {
                // use tab as separator
                String[] car = line.split(tabSplitBy);
				if(!car[0].equals("stock") && !car[0].equals("")) {
					cars.add(new Car(car));
				}
			}
		} catch(Exception e) {}
		
		try {
			br = new BufferedReader(new FileReader(cdkInventory));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cdkItem = line.split(csvSplitBy);
				if(!cdkItem[0].equals("Stock No.") && !cdkItem[0].equals("")) {
					cdk.add(new CDK(cdkItem));
				}
			}
				
		} catch(Exception e) {}
		
		for(int i = 0; i < cdk.size(); i++) {
			boolean breakPoint = true;
			int x = 0;
			while(breakPoint && !cars.get(x).cdkCompare(cdk.get(i))){
				x++;
				if(x == cars.size())
					breakPoint = false;
			}
		}
		
		try{
			FileWriter writer = new FileWriter(new File(dest + "MissingPics.csv"));
			StringBuilder sb = new StringBuilder();
			sb.append("STATUS,STOCK,YR,MAKE,MODEL,AGE,CLR,P\n");
			for(Car listCar : cars){
				if(listCar.getPics() < 15)
					sb.append(listCar.toString() + "\n");
			}
			writer.write(sb.toString());
			writer.close();
		} catch(Exception e) {}
		
		try{
			FileWriter writer = new FileWriter(new File(dest + "FullInventory.csv"));
			StringBuilder sb = new StringBuilder();
			sb.append("NU,STOCK,YR,MAKE,MODEL,AGE,CLR,P\n");
			for(Car listCar : cars){
				sb.append(listCar.toString() + "\n");
			}
			writer.write(sb.toString());
			writer.close();
		} catch(Exception e) {}
	}
}
