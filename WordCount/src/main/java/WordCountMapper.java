package wordCount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class WordCountMapper 
		extends Mapper<LongWritable, Text, Text, IntWritable>{

	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] strs = line.split("[,\\s]+");
		
		for (String str : strs) {
			if (str.matches(".*Dec.*")) {
				context.write(new Text("Dec"), new IntWritable(1));
			} else if (str.matches(".*hackathon.*")) {
				context.write(new Text("hackathon"), new IntWritable(1));
			} else if (str.matches(".*Chicago.*")) {
				context.write(new Text("Chicago"), new IntWritable(1));
			} else if (str.matches(".*Java.*")) {
				context.write(new Text("Java"), new IntWritable(1));
			}
		}
	}
}
