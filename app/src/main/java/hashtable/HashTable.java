package hashtable;

import java.util.Arrays;
import java.util.List;

public class HashTable {

    String[] table;
    int size;

    public HashTable(){
        table = new String[11];
        size = 0;
    }

    public int hash(String input){
        char[] inputArr = input.toCharArray();
        int a = 31;

        //hash code w polynomial
        int hashCode = 0;
        int i = 1;
        for(char x : inputArr){
            hashCode += (((int)x * Math.pow(a,i)) % 1000000007) % table.length;
            i ++;
        }

        //compression function w linear probing for collision
        int x = 1;
        int arraySpot = (hashCode + x) % table.length;
        while(table[arraySpot] !=  null) {
            x++;
            arraySpot = (hashCode + x) % table.length;

        }

        return arraySpot;
    }
    

    public void resizeTable(){
        String[] tableCopy = table.clone(); 
        int tableLength = (table.length * 2 + 1);
        table = new String[tableLength];
        for(int i = 0 ; i < tableCopy.length; i++){
            if(tableCopy[i] != null){
                insert(tableCopy[i]);
                size--;
            }
        }
    }

    public void insert(String input){
        table[hash(input)] = input;
        size++;
        if(size > table.length/2){
            resizeTable();
        }
    }

    public int size(){
        return size;
    }

    public boolean contains(String word){
        List<String> temp = Arrays.asList(table);
        return temp.contains(word);
    }

    public void printItems(){
        int i;
        for(i = 0; i < table.length; i++){
            System.out.println(table[i]);
        }
    }
}
