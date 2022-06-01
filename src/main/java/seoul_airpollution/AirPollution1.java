package seoul_airpollution;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AirPollution1 extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new AirPollution1(), args);
    }

    public int run(String[] args) throws Exception {

        // Job 설정
        Job job = Job.getInstance(getConf());
        job.setJarByClass(AirPollution1.class);

        // Mapper, Reducer 클래스 설정
        job.setMapperClass(AirPollution1Mapper.class);
        job.setReducerClass(AirPollution1Reducer.class);

        // Map 출력 key, value 유형 설정
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FloatWritable.class);

        // Reducer 출력 key, value 유형 설정
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 입출력 포맷 설정
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        // 입출력 경로 설정
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[0]).suffix("1.out"));

        job.waitForCompletion(true);

        return 0;
    }

}
