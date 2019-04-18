public class Car {
	String stock;		int stkInt = 0;
	String status;		int preInt = 2;
	String year;		int yerInt = 4;
	String make;		int makInt = 5;
	String model;		int modInt = 6;
	String age;			int ageInt = 10;
	String color;		int clrInt = 12;
	int pics;			int picInt = 30;
	
	/**
	 * Basic Constructor.
	 */
	Car(String[] car) {
		if(car.length > 33) {
			int dif = car.length - 32;
			fixPics(car[car.length - dif]);
		} else {
			fixPics(car[picInt]);
		}
		stock = car[stkInt];
		if(car[preInt].equals("false"))
			status = "N";
		else {
			if(pics == 1)
				status = "CS";
			else
				status = "U";
		}
		year = car[yerInt];
		make = car[makInt];
		model = car[modInt];
		age = car[ageInt];
		color = car[clrInt];
		fixYear();
		fixMake();
		fixModel();
		fixColor();
		//System.out.println(this.toString());
	}
	
	/**
	 * Concats "Make" Strings to 4 character long Strings.
	 */
	private void fixMake(){
		switch(make){
			case "Chevrolet": make = "CHEV"; break;
			case "Acura": make = "ACUR"; break;
			case "Audi": make = "AUDI"; break;
			case "BMW": make = "BMW"; break;
			case "Buick": make = "BUIC"; break;
			case "Cadillac": make = "CADI"; break;
			case "Chrysler": make = "CHRY"; break;
			case "Dodge": make = "DODG"; break;
			case "Fiat": make = "FIAT"; break;
			case "Ford": make = "FORD"; break;
			case "GM": make = "GM"; break;
			case "GMC": make = "GMC"; break;
			case "Honda": make = "HOND"; break;
			case "Hyundai": make = "HYUN"; break;
			case "Jeep": make = "JEEP"; break;
			case "Kia": make = "KIA"; break;
			case "Mazda": make = "MAZD"; break;
			case "Mercedes-Benz": make = "BENZ"; break;
			case "Mini": make = "MINI"; break;
			case "Mitsubishi": make = "MITS"; break;
			case "Scion": make = "SCIO"; break;
			case "Nissan": make = "NISS"; break;
			case "Ram": make = "RAMT"; break;
			case "Subaru": make = "SUBA"; break;
			case "Suzuki": make = "SUZU"; break;
			case "Toyota": make = "TOYO"; break;
			case "Volkswagen": make = "VW"; break;
			case "Harley Davidson": make = "HLDV"; break;
			case "Yamaha": make = "YAMA"; break;
			default: break;
		}
	}
	
	public int getPics(){
		return pics;
	}
	
	private void fixModel(){
		switch(model) {
			case "Silverado 1500": model="SILV1500"; break;
			case "Silverado 2500HD": model="SILV2500"; break;
			case "Silverado 3500HD": model="SILV3500"; break;
			case "SILVERADO 1500": model="SILV1500"; break;
			case "SILVERADO 2500": model="SILV2500"; break;
			case "SILVERADO 3500": model="SILV3500"; break;
			case "Monte Carlo": model="MntCrlo"; break;
			case "PT Cruiser": model="PTCruiser"; break;
			case "Town & Country": model="T&C"; break;
			case "Challenger": model="Challngr"; break;
			case "Grand Caravan": model="GCaravan"; break;
			case "Ram 1500": model="RAM1500"; break;
			case "Ram 2500": model="RAM2500"; break;
			case "Ram 3500": model="RAM3500"; break;
			case "Sierra 1500": model="SIER1500"; break;
			case "Sierra 2500HD": model="SIER2500"; break;
			case "Sierra 3500HD": model="SIER3500"; break;
			case "Santa Fe": model="SantaFe"; break;
			case "Grand Cherokee": model="GCheroke"; break;
			case "Highlander": model = "Highlndr"; break;
			case "Super Duty F-250 SRW": model = "F-250"; break;
			case "Super Duty F-250": model = "F-250"; break;
			case "Super Duty F-350 SRW": model = "F-350"; break;
			case "Super Duty F-350": model = "F-350"; break;
			//case "": model = ""; break;
			default: String[] a = model.split(" "); model = a[0]; break;
		}
	}
	
	private void fixYear(){
		year = year.substring(2);
	}
	
	private void fixPics(String carInt){
		System.out.println(carInt);
		try{
			pics = Integer.parseInt(carInt);
		} catch (Exception NumberFormatException) {
			pics = 0;
		}
	}
	
	private void fixColor(){
		switch(color) {
			case "Black": color = "BLK"; break;
			case "Blue": color = "BLU"; break;
			case "Brown": color = "BRN"; break;
			case "Copper": color = "BRN"; break;
			case "Gold": color = "GLD"; break;
			case "Gray": color = "GRY"; break;
			case "Green": color = "GRN"; break;
			case "Maroon": color = "MRN"; break;
			case "Orange": color = "ORG"; break;
			case "Purple": color = "PRP"; break;
			case "Red": color = "RED"; break;
			case "Silver": color = "SLV"; break;
			case "Tan": color = "TAN"; break;
			case "Teal": color = "BLU"; break;
			case "White": color = "WHT"; break;
			case "Yellow": color = "YEL"; break;
		}
	}
	
	public String toString() {
		return status + "," + stock +"," + year +"," + make +"," + model +"," + age +"," + color +"," + pics;
	}
	
	public boolean cdkCompare(CDK cdkItem) {
		if(this.stock.equals(cdkItem.getStock())) {
			if(this.status.equals("N")) {
				if(!cdkItem.getStatus().equals("S"))
					this.status = "DELETE";
			} else {
				if(cdkItem.getLot().equals("LOT"))
					this.status = "U";
				else if(cdkItem.getLot().equals("SERVICE") && !this.status.equals("CS"))
					this.status = "B";
				else if(cdkItem.getLot().equals("SERVICE") && this.status.equals("CS"))
					this.status = "CS";
				else if(cdkItem.getLot().equals("ABS") || cdkItem.getLot().equals("MANHEIM") || cdkItem.getLot().equals("SOLD") || cdkItem.getLot().equals("ADESA") || cdkItem.getLot().equals("WHSLE") || cdkItem.getColor().equals("AUCTION"))
					this.status = "DELETE";
				else
					this.status = cdkItem.getLot();
			}
			//System.out.println(this.stock + ": " + this.status);
			return true;
		}
		else
			return false;
	}
	
	public boolean delete(){
		if(this.status == "DELETE")
			return true;
		else
			return false;
	}
}
