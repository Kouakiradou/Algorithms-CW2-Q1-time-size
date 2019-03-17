import java.util.ArrayList;
import java.util.Collections;


public class Main
{
    public ArrayList<ArrayList<Integer>> qs_size_10 = new ArrayList<>();
    public ArrayList<ArrayList<Long>> qs_time_10 = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> hs_size_10 = new ArrayList<>();
    public ArrayList<ArrayList<Long>> hs_time_10 = new ArrayList<>();

    public ArrayList<Integer> generateRandom(int size)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i <= size; i++)
        {
            arr.add(i);
        }
        Collections.shuffle(arr);
        return arr;
    }

    public void run()
    {
        ArrayList<Integer> qs_numberSize = new ArrayList<>();
        ArrayList<Long> qs_timeUsed = new ArrayList<>();
        ArrayList<Integer> hs_numberSize = new ArrayList<>();
        ArrayList<Long> hs_timeUsed = new ArrayList<>();
        Quicksort quicksorter = new Quicksort();
        Heapsort heapsorter = new Heapsort();
        for (int size = 100000; size <= 10000000; size = size + 100000)
        {
            ArrayList<Integer> qs_arr = new Main().generateRandom(size);
            ArrayList<Integer> hs_arr = new ArrayList<>(qs_arr);
            long starttime = System.nanoTime();
            quicksorter.quicksort(qs_arr, 0, qs_arr.size() - 1);
            long endtime = System.nanoTime();
            qs_numberSize.add(size);
            qs_timeUsed.add((endtime - starttime) / 1000000);

            starttime = System.nanoTime();
            heapsorter.heapsort(hs_arr);
            endtime = System.nanoTime();
            hs_numberSize.add(size);
            hs_timeUsed.add((endtime - starttime) / 1000000);
            System.out.println(size);
        }
        qs_size_10.add(qs_numberSize);
        qs_time_10.add(qs_timeUsed);
        hs_size_10.add(hs_numberSize);
        hs_time_10.add(hs_timeUsed);
    }

    public static void main(String[] args) throws Exception
    {
        Main obj = new Main();
        for (int i = 0; i < 10; i++)
        {
            obj.run();
            System.out.println(i);
        }
        for (int i = 0; i < 10; i++)
        {
            new PlotCharts().plot(i, obj.qs_size_10.get(i), obj.qs_time_10.get(i), obj.hs_size_10.get(i), obj.hs_time_10.get(i));
        }
        ArrayList<Integer> qs_averageSize = new ArrayList<>();
        ArrayList<Long> qs_averageTime = new ArrayList<>();
        ArrayList<Integer> hs_averageSize = new ArrayList<>();
        ArrayList<Long> hs_averageTime = new ArrayList<>();
        for (int i = 0; i < obj.qs_size_10.get(0).size(); i++)
        {
            int size = 0;
            long time = 0;
            for (int j = 0; j < 10; j++)
            {
                size += obj.qs_size_10.get(j).get(i);
                time += obj.qs_time_10.get(j).get(i);
            }
            qs_averageSize.add(size / 10);
            qs_averageTime.add(time / 10);
        }
        for (int i = 0; i < obj.hs_size_10.get(0).size(); i++)
        {
            int size = 0;
            long time = 0;
            for (int j = 0; j < 10; j++)
            {
                size += obj.hs_size_10.get(j).get(i);
                time += obj.hs_time_10.get(j).get(i);
            }
            hs_averageSize.add(size / 10);
            hs_averageTime.add(time / 10);
        }
        new PlotCharts().plot(10, qs_averageSize, qs_averageTime, hs_averageSize, hs_averageTime);
    }

}
