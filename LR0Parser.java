/*
S->AA
A->aA/b
*/



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class LR0Parser {
    static String temp,input,temp2,temp3;
    static int i,j,k,prev;
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc=new Scanner(System.in);

    static String table[][]=
        {
            {"s3","s4",null,"2","1"},
            {null,null,"accept",null,null},
                {"s3","s4",null,"5",null},
                {"s3","s4",null,"6",null},
                {"r3","r3","r3",null,null},
                {"r1","r1","r1",null,null},
                {"r2","r2","r2",null,null},
        };

    static String prod[][]=
            {
                    {null,null},
                    {"S","AA"},
                    {"A","aA"},
                    {"A","b"}
            };

    public static void main(String[] args)
    {
        int ptr=0;
        System.out.println("enter input string");
        input=sc.nextLine();
        Stack<Character> st=new Stack<Character>();
        st.push('0');
        while(true)
        {
            System.out.println(st);
            i=Character.getNumericValue(st.peek());
            try {
                switch (input.charAt(ptr)) {
                    case 'a':
                        j = 0;
                        break;
                    case 'b':
                        j = 1;
                        break;
                    case '$':
                        j = 2;
                        break;
                    default:
                        System.out.println("wrong input, check it");
                        System.exit(0);
                }
            }
            catch (Exception e)
            {
                System.out.println();
            }
            temp=table[i][j];
            if(temp==null)
            {
                System.out.println("parsing error");System.exit(0);
            }

            switch (temp.charAt(0))
            {
                case 'a': System.out.println("sucessful");System.exit(0);
                case 's': st.push(input.charAt(ptr));
                ptr++;
                st.push(temp.charAt(1));
                break;
                case 'r': temp2=prod[Character.getNumericValue(temp.charAt(1))][1];
                for(k=0;k<2*temp2.length();k++)
                {
                    st.pop();
                }
                prev=Character.getNumericValue(st.peek());
                st.push((prod[Character.getNumericValue(temp.charAt(1))][0]).charAt(0));
                switch (st.peek())
                {
                    case 'A': j=3;break;
                    case 'S': j=4;break;
                }
                temp3=table[prev][j];
                st.push(temp3.charAt(0));

            }
        }

    }
}
