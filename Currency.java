import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

public class Currency{
	Scanner sc=new Scanner(System.in);
    ArrayList<String> arr=new ArrayList<String>(3);
	public void add() {
	    for(int i=0;i<4;i++) {
	    	 arr.add(sc.nextLine());
	     }
	}
	public void view() {
	     System.out.println(arr);
	}
	public void convert() throws IOException, InterruptedException {
	    System.out.println("From");
	    String from=sc.nextLine();
	    System.out.println("To");
	    String to=sc.nextLine();
	    System.out.println("You want to convert Currency from"+" "+from+" "+"to"+to);
	    int amount =10;
	    String endpoint="convert";
	    String geturl="http://api.exchangeratesapi.io/v1/latest"+"?access_key=" + accesskey +"&from=" + from + "&to=" + to + "&amount=" + amount;
	   /* URI url = URI.create(geturl);
	    HttpRequest request=HttpRequest.newBuilder().GET().uri(url).build();
	    HttpClient client = HttpClient.newBuilder().build();
	    HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
	    System.out.println(response.statusCode());
	    //System.out.println(response.body());*/
	    URL url = new URL(geturl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Set the request method to GET
        connection.setRequestMethod("GET");
        // Read the response from the API
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {

            response.append(line);

        }
        reader.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        // Access the conversion result
        double conversionResult = jsonResponse.getJSONObject("rates").getDouble(from);
        // Display the conversion result
        System.out.println("Conversion Result: " + conversionResult);
        System.out.println(amount+from+" = "+amount/conversionResult + to);
        // Close the connection
        connection.disconnect();

    }  
	    
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	 Scanner sc=new Scanner(System.in);
	 Demo d=new Demo();
	 while(true) {
     System.out.println("Enter choice 1/:Add List 2/:View List 3/:Update List 4/: Convert Currency");
     int choice=sc.nextInt();
     switch(choice) {
     case 1:
    	 System.out.println("Add your Favorite Currency List");
    	 d.add();
    	 break;
     case 2:
    	 System.out.println("View your Favorite Currency List");
    	 d.view();
    	 break;
     case 3:
    	 System.out.println("Update your Favorite Currency List");
    	 break;
	 case 4:
    	 System.out.println("Select the Currency You Want To Convert");
    	 System.out.println(d.arr);
    	 d.convert();
    	 break;
     }
	}
	}
}
	
