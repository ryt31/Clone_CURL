import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpMethods {
    // Getメソッド
    public static String GET(String urltext){
        String urlText = urltext; // urlを指定
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
            System.out.println("エラー：終了"); // エラー箇所を表示
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
                System.out.println("エラー：終了"); // エラー箇所を表示
            }
        }
        return null;
    }

    // Get-vメソッド
    public static String GETV(String urltext){
        String urlText = urltext; // urlを指定
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
            TextFile.PrintHeader(httpURLConnection); // ヘッダーをプリント
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
            System.out.println("エラー：終了");
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
                System.out.println("エラー：終了");
            }
        }
        return outputResultText.toString();
    }

    // Postメソッド
    public static String POST(String urltext){
        String urlText = urltext; // urlを読み込む
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
            System.out.println("エラー：終了");
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
                System.out.println("エラー：終了");
            }
        }
        return null;
    }

    // Post-vメソッド
    public static String POSTV(String urltext){
        String urlText = urltext; // urlを読み込む
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
            TextFile.PrintHeader(httpURLConnection); // ヘッダーをプリント
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
            System.out.println("エラー：終了");
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
                System.out.println("エラー：終了");
            }
        }
        return null;
    }

    // POST-dメソッド
    public static String POSTData(String urltext,String params){
        String urlText = urltext; // urlを指定
        String param = params; // パラメータ指定
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
            System.out.println("エラー：終了");
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
                System.out.println("エラー：終了");
            }
        }
        return result.toString();
    }

    // POST-d-vメソッド
    public static String POSTDataV(String urltext,String params){
        String urlText = urltext; // urlを指定
        String param = params; // パラメータ指定
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
            System.out.println("エラー：終了");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (httpURLConnection != null) {
                    // コネクションを切断
                    httpURLConnection.disconnect();
                }
                TextFile.PrintHeader(httpURLConnection);
            }catch (IOException e){
                System.out.println("エラー：終了");
            }
        }
        return result.toString();
    }
}
