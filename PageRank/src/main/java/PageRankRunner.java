import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

public class PageRankRunner 
{
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: WordCount <input path> <output path");
			System.exit(-1);
		}
		
		Job job = new Job();
		System.out.println("start job");
		
		job.setJarByClass(PageRankRunner.class);
		job.setJobName("One Pass Page Rank");
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setMapperClass(PageRankMapper.class);
		job.setReducerClass(PageRankReducer.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));		
		
		
		boolean status = job.waitForCompletion(true);
		
		System.out.println("end job");

		if (status) {
			System.exit(0);
		} else {
			System.exit(1);

		}
		
		
	}
}
