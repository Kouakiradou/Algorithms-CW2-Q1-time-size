import java.util.ArrayList;

public class Quicksort
{
    public int partition(ArrayList<Integer> arr, int p, int q)
    {
        int pivot = arr.get(p);
        int i = p;
        int temp;
        for (int j = p + 1; j <= q; j++)
        {
            if (arr.get(j) <= pivot)
            {
                i++;
                temp = arr.get(j);
                arr.set(j, arr.get(i));
                arr.set(i, temp);
            }
        }
        temp = arr.get(p);
        arr.set(p, arr.get(i));
        arr.set(i, temp);
        return i;
    }

    public void quicksort(ArrayList<Integer> arr, int p, int q)
    {
        if (p < q)
        {
            int i = partition(arr, p, q);
            quicksort(arr, p, i - 1);
            quicksort(arr, i + 1, q);
        }
    }
}
