import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurlTest {
    public static void main(String[] args) {
        System.out.println("□ □ □ □ □ 結果 □ □ □ □ □");
        System.out.println(TrimString(Get()));
        //SaveFile(TrimString(Get()));
    }
    // Getメソッド
    public static String Get(){
        String urlText = "https://example.com"; // 本来はコンソールから読み込む
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null; // 結果（バイト）を格納する変数
        BufferedReader bufferedReader = null; // 結果を読み込み格納する

        try {
            // try:必ず実行する処理

            URL url = new URL(urlText); // 接続するURLを指定しインスタンス生成
            // コネクションを取得（URLが参照するリモート・オブジェクトへの接続を表すインスタンスを取得）
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET"); // GETメソッドをセット
            httpURLConnection.connect(); // コネクト

            // 応答されたコードがHTTP_OK(200)なら結果を読み込む
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream(); // ストリームを取得
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // ストリームから文字列読み取り
                StringBuilder output = new StringBuilder(); // 結果を格納する
                String resultText; // readLine()の際に格納する

                // ストリングビルダーに最後の文字まで格納する
                while ((resultText = bufferedReader.readLine()) != null){
                    output.append(resultText);
                }
                return output.toString();
            }
        } catch (IOException e){
            // catch:例外処理
            System.out.println(e.getMessage()); // エラーメッセージを表示
        } finally {
            // 例外してもしなくても実行される
            try {
                if(bufferedReader != null){
                    bufferedReader.close(); // バッファリーダーを閉じる
                }
                if(httpURLConnection != null){
                    httpURLConnection.disconnect(); // 接続を切る
                }
            } catch (IOException e){
                System.out.println(e.getMessage()); // エラーメッセージを表示
            }
        }
        return null;
    }

    // 文字列整形メソッド
    public static String TrimString(String s)
    {
        StringBuilder stringBuilder = new StringBuilder(s);
        List<Integer> numList = new ArrayList<>();
        List<Integer> conmaList = new ArrayList<>();
        List<Integer> kakkoList = new ArrayList<>();
        List<Integer> kakkoList2 = new ArrayList<>();
        int insertCount = 0;

        for(int i=0;i<stringBuilder.length();i++){
            if (stringBuilder.charAt(i) == '>') {
                numList.add(i);
            }
        }
        for(int j=0;j < numList.size();j++){
            stringBuilder.insert(numList.get(j)+1+insertCount,"\n");
            insertCount++;
        }

        insertCount = 0;

        for(int i=0;i<stringBuilder.length();i++){
            if (stringBuilder.charAt(i) == ';') {
                conmaList.add(i);
            }
        }
        for(int j=0;j < conmaList.size();j++){
            stringBuilder.insert(conmaList.get(j)+1+insertCount,"\n");
            insertCount++;
        }

        insertCount = 0;

        for(int i=0;i<stringBuilder.length();i++){
            if (stringBuilder.charAt(i) == '}') {
                kakkoList.add(i);
            }
        }
        for(int j=0;j < kakkoList.size();j++){
            stringBuilder.insert(kakkoList.get(j)+1+insertCount,"\n");
            insertCount++;
        }

        insertCount = 0;

        for(int i=0;i<stringBuilder.length();i++){
            if (stringBuilder.charAt(i) == '{') {
                kakkoList2.add(i);
            }
        }
        for(int j=0;j < kakkoList2.size();j++){
            stringBuilder.insert(kakkoList2.get(j)+1+insertCount,"\n");
            insertCount++;
        }

        return stringBuilder.toString();
    }

    // ファイル出力メソッド
    public static void SaveFile(String s){
        String fileName = "file"; // 本来はコンソールから読み込む
        try {
            FileWriter fileWriter = new FileWriter(fileName); // fileNameの名前のファイルを作成
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e){
            System.out.println(e.getMessage()); // エラーメッセージを表示
        }
    }
}
