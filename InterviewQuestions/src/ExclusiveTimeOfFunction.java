import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ExclusiveTimeOfFunction {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int n;
        int logsNum;
        List<String> logs = new ArrayList<>();
        n = input.nextInt();
        logsNum = input.nextInt();
        for (int i = 0; i < logsNum; i++) {
            logs.add(input.next());
        }
        Solution solution = new Solution();
        for (int i : solution.exclusiveTime(n, logs)) {
            System.out.println(i);
        }
    }
}

class Solution {
    public int[] exclusiveTime(int n, List<String> logs)
    {
        Stack<Integer> callStack = new Stack<>(); // Using stack for open function calls
        int lastLogTimeStamp = -1; // Using for 
        int[] ans = new int[n];
        for (String log : logs)
        {
            String[] str = log.split(":"); // Split the string to function id, start and end of the function call
            int funcId = Integer.parseInt(str[0]);
            boolean isStart = "start".equals(str[1]);
            int timeStamp = Integer.parseInt(str[2]);

            if (!isStart) timeStamp += 1;


            if (!callStack.empty()) // It means we are in a fucntion and we have to update the answer
            {
                int currentFunc = callStack.peek();
                ans[currentFunc] += timeStamp - lastLogTimeStamp; // Updating current function answer from last log time
            }

            if (isStart) // A new function called -> we have to add its id to callStack
            {
                callStack.push(funcId);
            }
            else // The function we were in is ended -> we have to remove its id from callStack
            {
                callStack.pop();
            }

            lastLogTimeStamp = timeStamp;
        }
        return ans;
    }
}