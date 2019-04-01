/*
grammer is

G->E$
E->TK
K->+TK/epsilon
T->FH
H->*FH/epsilon
F->(E)/a
 */



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class LLParser {
    public static void main(String args[])throws Exception
    {
        try {
            int i = 0, j = 0, k = 0;
            String temp = "";
            String table[][] =
                    {
                            {"E$", null, null, "E$", null, null},
                            {"TK", null, null, "TK", null, null},
                            {null, "+TK", null, null, "", ""},
                            {"FH", null, null, "FH", null, null},
                            {null, "", "*FH", null, "", ""},
                            {"a", null, null, "(E)", null, null},
                    };
            Stack<Character> st = new Stack<Character>();
            st.push('G');
            System.out.println("enter input string");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            int ptr = 0;
            while (true) {
                if (st.peek() == input.charAt(ptr)) {
                    if (st.peek() == '$') {
                        System.out.println("sucessfull");
                        break;
                    } else {
                        ptr++;
                        st.pop();
                        continue;
                    }
                }
                switch (st.peek()) {
                    case 'G':
                        i = 0;
                        break;
                    case 'E':
                        i = 1;
                        break;
                    case 'K':
                        i = 2;
                        break;
                    case 'T':
                        i = 3;
                        break;
                    case 'H':
                        i = 4;
                        break;
                    case 'F':
                        i = 5;
                        break;
                }
                switch (input.charAt(ptr)) {
                    case 'a':
                        j = 0;
                        break;
                    case '+':
                        j = 1;
                        break;
                    case '*':
                        j = 2;
                        break;
                    case '(':
                        j = 3;
                        break;
                    case ')':
                        j = 4;
                        break;
                    case '$':
                        j = 5;
                        break;
                     default:System.out.println("input has other character ,out of terminals");
                     System.exit(0);
                }
                if (table[i][j] == null) {
                    System.out.println("error!!!");
                    System.exit(0);
                }
                if (table[i][j] == "") {
                    st.pop();
                    continue;
                } else {
                    temp = table[i][j];
                    st.pop();
                    for (k = temp.length() - 1; k >= 0; k--) {
                        st.push(temp.charAt(k));
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("error!!!");
        }
    }
}
