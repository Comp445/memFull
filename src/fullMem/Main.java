package fullMem;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		try {//open input file to stream
            Stream<String> lines = Files.lines(Paths.get("input.txt"));//opens our inputfile
            System.out.println("<!-----Read all lines as a Stream-----!>");
            //using Java 8 stream and options
           Map<String,Double> query =  lines
            .map(word->word.substring(18,27)+";"+ word.substring(241,250)) 
            .sorted((w1,w2)->w1.compareTo(w2))
            
            .collect(groupingBy(word->word.substring(0,9),summingDouble(word->Double.parseDouble(word.substring(10,19)) )))
            .entrySet().stream()
            //.sorted((w1,w2)->w1.getValue().compareTo(w2.getValue()))
            .sorted((w1,w2)->w1.getKey().substring(0,9).compareTo(w2.getKey().substring(0, 9)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                  (e1, e2) -> e2, LinkedHashMap::new));
            		;
//           Map<String,Double>q2 = query.entrySet().stream()
//        		   .sorted((w1,w2)->w1.getKey().compareTo(w2.getKey()))
//                   .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                           (e1, e2) -> e2, LinkedHashMap::new));
           //print map
            	       query.entrySet().stream()
                       .sorted((w1,w2)->w1.getValue().compareTo(w2.getValue()))
                       
                       .forEach(e->System.out.println(e));
           
			

            //System.out.println(query);
            lines.close(); //close input file
        } catch(IOException io) {
           io.printStackTrace();
        }

	}

}
