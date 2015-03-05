import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class PageRankReducer extends Reducer<Text, Text, Text, Text>{

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException{
		
		Float newPr = 0.0f;
		String keyStr = key.toString();
		
		for (Text value: values) {
			String[] strs = value.toString().split("\\s+");
			
			//if last value for the key is a Letter
			if (strs[strs.length - 1].matches("[A-Z]")) {
				
				//add the target pages to the key string
				keyStr += " " + value.toString();
			} else {
				newPr += Float.parseFloat(strs[strs.length - 1]);
			}
		}
		
		context.write(new Text(keyStr), new Text(String.format("%.6f", newPr)));
	}
}