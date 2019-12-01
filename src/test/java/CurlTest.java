import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurlTest {

    public static void main(String[] args) {
        System.out.println("□ □ □ □ □ 結果 □ □ □ □ □");
        System.out.println(TrimString(POSTd()));
        //OutputFile(TrimString(Get()));
    }

    // Getメソッド
    public static String GET(){
        String urlText = "https://example.com"; // 本来はコンソールから読み込む
        HttpURLConnection httpURLConnection = null;
        InputStream inputResultText = null; // 結果（バイト）を格納する変数
        BufferedReader bufferedReader = null; // 結果を読み込み格納する
        StringBuilder outputResultText = new StringBuilder(); // 結果を格納する

        try {
            // try:必ず実行する処理
            URL url = new URL(urlText); // 接続するURLを指定しインスタンス生成
            // コネクションを取得（URLが参照するリモート・オブジェクトへの接続を表すインスタンスを取得）
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET"); // GETメソッドをセット
            httpURLConnection.connect(); // コネクト

            // 応答されたコードがHTTP_OK(200)なら結果を読み込む
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputResultText = httpURLConnection.getInputStream(); // ストリームを取得
                bufferedReader = new BufferedReader(new InputStreamReader(inputResultText)); // ストリームから文字列読み取り
                String resultText; // readLine()の際に格納する

                // ストリングビルダーに最後の文字まで格納する
                while ((resultText = bufferedReader.readLine()) != null){
                    outputResultText.append(resultText);
                }
                return outputResultText.toString();
            }
        } catch (IOException e){
            // catch:例外処理
            e.printStackTrace(); // エラー箇所を表示
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
                e.printStackTrace(); // エラー箇所を表示
            }
        }
        return null;
    }

    // Get-vメソッド
    public static String GETv(){
        String urlText = "https://example.com"; // 本来はコンソールから読み込む
        HttpURLConnection httpURLConnection = null;
        InputStream inputResultText = null; // 結果（バイト）を格納する変数
        BufferedReader bufferedReader = null; // 結果を読み込み格納する
        StringBuilder outputResultText = new StringBuilder(); // 結果を格納する

        try {
            // try:必ず実行する処理
            URL url = new URL(urlText); // 接続するURLを指定しインスタンス生成
            // コネクションを取得（URLが参照するリモート・オブジェクトへの接続を表すインスタンスを取得）
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET"); // GETメソッドをセット
            PrintHeader(httpURLConnection); // ヘッダーをプリント
            httpURLConnection.connect(); // コネクト

            // 応答されたコードがHTTP_OK(200)なら結果を読み込む
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputResultText = httpURLConnection.getInputStream(); // ストリームを取得
                bufferedReader = new BufferedReader(new InputStreamReader(inputResultText)); // ストリームから文字列読み取り
                String resultText; // readLine()の際に格納する
                // ストリングビルダーに最後の文字まで格納する
                while ((resultText = bufferedReader.readLine()) != null){
                    outputResultText.append(resultText);
                }
            }
        } catch (IOException e){
            // catch:例外処理
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return outputResultText.toString();
    }

    // Postメソッド
    public static String POST(){
        String urlText = "http://httpbin.org/post"; // 本来はコンソールから読み込む
        HttpURLConnection httpURLConnection = null;
        InputStream inputResultText = null; // 結果（バイト）を格納する変数
        BufferedReader bufferedReader = null; // 結果を読み込み格納する
        StringBuilder outputResultText = new StringBuilder(); // 結果を格納する

        try {
            // try:必ず実行する処理
            URL url = new URL(urlText); // 接続するURLを指定しインスタンス生成
            // コネクションを取得（URLが参照するリモート・オブジェクトへの接続を表すインスタンスを取得）
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST"); // POSTメソッドをセット
            httpURLConnection.connect(); // コネクト

            // 応答されたコードがHTTP_OK(200)なら結果を読み込む
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputResultText = httpURLConnection.getInputStream(); // ストリームを取得
                bufferedReader = new BufferedReader(new InputStreamReader(inputResultText)); // ストリームから文字列読み取り
                String resultText; // readLine()の際に格納する

                // ストリングビルダーに最後の文字まで格納する
                while ((resultText = bufferedReader.readLine()) != null){
                    outputResultText.append(resultText);
                }
                return outputResultText.toString();
            }
        } catch (IOException e){
            // catch:例外処理
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return null;
    }

    // POST-dメソッド
    public static String POSTd(){
        String urlText = "https://example.com/";
        String param = "key=value";
        HttpURLConnection httpURLConnection = null;
        InputStream inputResultText = null; // 結果（バイト）
        BufferedReader bufferedReader = null;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlText); // 接続するURLを指定したインスタンスを生成
            // コネクションを取得（URLが参照するリモート・オブジェクトへの接続を表すインスタンスを取得）
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true); // 出力を可能にする
            httpURLConnection.setRequestMethod("POST"); // POSTメソッドをセット

            // この接続に出力するストリーム
            OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
            out.write(param); // 書き込み
            out.close(); // 書き込み終了

            httpURLConnection.connect(); // コネクト

            // 応答されたコードがHTTP_OK(200)なら結果を読み込む
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputResultText = httpURLConnection.getInputStream(); // ストリームを取得
                bufferedReader = new BufferedReader(new InputStreamReader(inputResultText)); // ストリームを読み取り文字列を読み取る
                String line; // readLine()の際に格納

                // テキストを読み込み
                while((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }

            }else { // コードがHTTP_OKじゃないならエラー結果を読み取る
                // エラー表示を読み込み
                inputResultText = httpURLConnection.getErrorStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputResultText));
                String line;

                // テキストを読み込み
                while((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (httpURLConnection != null) {
                    // コネクションを切断
                    httpURLConnection.disconnect();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    // ヘッダーをプリントするメソッド
    private static void PrintHeader(HttpURLConnection httpURLConnection){
        // ヘッダーのキーのリスト
        List<String> keyKist = new ArrayList<>(httpURLConnection.getHeaderFields().keySet());
        for(int i=0;i < keyKist.size();i++){
            System.out.print(keyKist.get(i) + ":"); // ヘッダーのキーをプリント
            System.out.println(httpURLConnection.getHeaderFields().get(keyKist.get(i))); // ヘッダーの値をプリント
        }
        System.out.println(); // 改行
    }

    // 文字列整形メソッド
    // 改行したいkeyを選択し、改行メソッドを実行
    // 整形した文字列をStringで返す
    public static String TrimString(String resultText) {
        StringBuilder trimedResultText = new StringBuilder(resultText);

        InsertNewLine(trimedResultText,'>');
        InsertNewLine(trimedResultText,';');
        InsertNewLine(trimedResultText,'{');
        InsertNewLine(trimedResultText,'}');

        return trimedResultText.toString();
    }

    // 改行挿入メソッド
    // keyを選択し、発見したらそれが何番目にあるかリストに格納する
    // keyの後に改行コードを挿入し改行する
    private static void InsertNewLine(StringBuilder resultText,char keyOfNewLine){
        List<Integer> indexList = new ArrayList<>(); // keyが何番目にあるか格納するリスト
        int insertcount = 0; // 改行コードを挿入した回数

        for(int i=0;i<resultText.length();i++){
            // keyを発見するとリストに追加
            if(resultText.charAt(i) == keyOfNewLine){
                indexList.add(i);
            }
        }

        for (int j=0;j<indexList.size();j++){
            // keyの後に改行コードを挿入
            resultText.insert(indexList.get(j) + 1 + insertcount,"\n");
            insertcount++; // 挿入回数をインクリメント
        }
    }

    // ファイル出力メソッド
    public static void OutPutFile(String resultText){

        String fileName = "file"; // 本来はコンソールから読み込む

        try {
            FileWriter fileWriter = new FileWriter(fileName); // fileNameの名前のファイルを作成
            fileWriter.write(resultText); // 書き込み
            fileWriter.close(); // ファイルを閉じる
        } catch (IOException e){
            System.out.println(e.getMessage()); // エラーメッセージを表示
        }
    }
}
