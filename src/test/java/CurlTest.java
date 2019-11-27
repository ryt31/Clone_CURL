import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurlTest {
    public static void main(String[] args) {
        Get();
    }
    // Getメソッド
    public static void Get(){
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
                System.out.println("□ □ □ □ □ Getした結果 □ □ □ □ □");
                System.out.println(output.toString()); // 結果を表示
            }
        } catch (IOException e){
            // catch:例外処理
            System.out.println(e.getMessage()); // エラーメッセージを表示
        } finally {
            // 例外してもしなくても実行される
            try {
                if(bufferedReader != null){
                    bufferedReader.close(); // バッファレーダーを閉じる
                }
                if(httpURLConnection != null){
                    httpURLConnection.disconnect(); // 接続を切る
                }
            } catch (IOException e){
                System.out.println(e.getMessage()); // エラーメッセージを表示
            }
        }
    }
}
