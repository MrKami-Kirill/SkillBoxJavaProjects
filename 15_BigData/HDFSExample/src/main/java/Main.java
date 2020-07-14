import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;

public class Main
{
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws Exception
    {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");

        FileSystem hdfs = FileSystem.get(
            new URI("hdfs://c419e905afca:8020"), configuration
        );
        Path file = new Path("hdfs://c419e905afca:8020/test/file.txt");

        if (hdfs.exists(file)) {
            hdfs.delete(file, true);
        }

        OutputStream os = hdfs.create(file);
        BufferedWriter br = new BufferedWriter(
            new OutputStreamWriter(os, "UTF-8")
        );

        StringBuilder builder = new StringBuilder();
        builder.append("Personally I suppose that summer is the best time for holiday and rest. This season is the warmest one. The nature is really beautiful and the people have various opportunities for their rest. They can travel and visit another cities and countries, continents and islands. They can go hiking and have a terrific time in the forest, in the mountains, on the lake, on the river or on the sea. The people can also go out with their friends and relatives and have fun in the open air. The nature is beautiful in summer. The grass and the leaves on the trees are fresh and green, there are many different flowers everywhere. The weather is mostly very nice. Of course, it can rain sometimes, but some people like rainy days. So do I. When it rains I like to read books and to listen to the patter of rain. Our family has a country house in a village near our city. In summer my grandmother lives there and I usually enjoy my summer holidays there. I have many friends in the village and we do many things together. We swim in the river or in the lake, go fishing, ride a bicycle, play many different games, go mushrooming and so on. In the evening I usually read books or watch TV and video. At the end of August I come back to the city. On the 1st of September begins the new school year. Next year I will put my school graduating exams. I am going to enter the university, that`s why I have also to put the entering exams. I will read up for examinations in our country house – far away from the scurry and the scramble of city life.");

//        for(int i = 0; i < 10_000; i++) {
//            br.write(getRandomWord() + " ");
//        }

        br.write(builder.toString());

        br.flush();
        br.close();
        hdfs.close();
    }

    private static String getRandomWord()
    {
        StringBuilder builder = new StringBuilder();
        int length = 2 + (int) Math.round(10 * Math.random());
        int symbolsCount = symbols.length();
        for(int i = 0; i < length; i++) {
            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
        }
        return builder.toString();
    }
}
