import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class POTD2197 {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        Stack<Integer> st = new Stack<>();
        
        for (int num : nums) {
            st.push(num);
            // Merge as long as the top two numbers are non-coprime
            while (st.size() >= 2) {
                int b = st.pop();
                int a = st.pop();
                int g = gcd(a, b);
                if (g > 1) {
                    int lcm = a / g * b;
                    st.push(lcm);
                } else {
                    st.push(a);
                    st.push(b);
                    break;
                }
            }
        }

        return new ArrayList<>(st);
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}