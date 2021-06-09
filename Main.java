import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        M = scan.nextInt();
        N = scan.nextInt();
        chars = new char[N + 1];
        selected = new int[M + 1];
        String[] tokens = scan.nextLine().split(" ");
        for (int i = 1; i <= N; i++) {
            chars[i] = tokens[i - 1].charAt(0);
        }
        // input 문자 오름차순 정렬
        Arrays.sort(chars, 1, N + 1);
    }

    static int N, M;
    static char[] chars;
    static int[] selected;

    // 모음 Validator
    static boolean isVowel(char x) {
        return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u';
    }

    static void rec_func(int k) {
        // 문자열 완성 시,
        if (k == M + 1) { 
            int vowel = 0, consonant = 0;
          
            // 모음, 자음 개수 충족 확인
            for(int i = 1; i < M + 1; i++) {
                if(isVowel(chars[selected[i]])) vowel++;
                else consonant++;  
                
                // 충족할 경우, 나열된 문자열 출력
                if(vowel > 0 && consonant > 1) {
                    for(int j = 1; j < M + 1; j++) 
                        sb.append(chars[selected[j]]);
                    sb.append('\n');
                    break;
                }
                    
            }
            
        } else {
            // 전에 확인한 index의 다음 인덱스부터 확인
            for(int i = selected[k - 1] + 1; i < N + 1; i++) {
                selected[k] = i;
                // 다음 자리에 대해 재귀호출
                rec_func(k + 1);
                selected[k] = 0;
            }
        }
    }

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println(sb.toString());
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
