import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class PageRankMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		
		String line = value.toString();
		String[] strs = line.split("\\s+");
		
		String sourcePage = strs[0];
		String targetPages = "";
		//number of outlinks
		int nLinks = strs.length - 2;
		// the page rank of the source page
		Float pr = Float.parseFloat(strs[strs.length - 1]);
		String targetPr = String.format("%.6f", pr/nLinks);
		
		for (int i = 1; i < strs.length - 1; i++) {
				targetPages += strs[i] + " ";
				context.write(new Text(strs[i]), new Text(sourcePage + " " + targetPr));
		}
		
		context.write(new Text(sourcePage), new Text(targetPages));
	}
}