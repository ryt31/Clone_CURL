import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurlTest {

    public static void main(String[] args) {
        while (true) {
            // コマンドリストを格納
            List<String> commandList = CommandRead();
            // コマンドがcurlで始まっていれば
            if (commandList.get(0).equals("curl")) {
                commandList.remove(0); // リストからcurlを削除

                String url = SerachHttp(commandList); // URLを探索

                if(commandList.size() == 0){
                    // リストが空の時オプションなしのGETメソッド（ボディ）を実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ GET");
                    System.out.println(TrimString(GET(url)));

                }else if(commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-d") && commandList.contains("-o") && commandList.contains("-v")){
                    // リストに-X POST -d -o file -vオプションが含まれているならPOSTDataVメソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -d -o -v");
                    System.out.println(TrimString(POSTDataV(url,SerachParam(commandList))));
                    OutPutFile(TrimString(POSTData(url,SerachParam(commandList))),commandList);

                }else if(commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-d") && commandList.contains("-o")){
                    // リストに-X POST -d -oオプションが含まれているならPOSTDataメソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -d -o");
                    System.out.println(TrimString(POSTData(url,SerachParam(commandList))));
                    OutPutFile(TrimString(POSTData(url,SerachParam(commandList))),commandList);

                }else if(commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-d")){
                    // リストに-X POST -dオプションが含まれているならPOSTDataメソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -d");
                    System.out.println(TrimString(POSTData(url,SerachParam(commandList))));

                }else if(commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-o") && commandList.contains("-v")){
                    // リストに-X POST -o file -vオプションが含まれているならPOST -o -vメソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -o -v");
                    System.out.println(TrimString(POSTV(url)));
                    OutPutFile(TrimString(POST(url)),commandList);

                }else if(commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-v")){
                    // リストに-X POST -vオプションが含まれているならPOST -vメソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -v");
                    System.out.println(TrimString(POSTV(url)));

                }else if(commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-o")){
                    // リストに-X POST -oオプションが含まれているならPOST -oメソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -o");
                    System.out.println(TrimString(POST(url)));
                    OutPutFile(TrimString(POST(url)),commandList);

                }else if(commandList.contains("-X") && commandList.contains("POST")){
                    // リストに-X POSTオプションが含まれているならPOST メソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST");
                    System.out.println(TrimString(POST(url)));

                }else if(commandList.contains("-v") && commandList.contains("-o")){
                    // リストに-oオプションが含まれているならGET -o（ボディ）かつファイルにて保存 メソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ GET -o file -v");
                    System.out.println(TrimString(GETV(url)));
                    OutPutFile(TrimString(GET(url)),commandList);

                }else if(commandList.contains("-v")) {
                    // リストに-vオプションが含まれているならGET -v（ヘッダ+ボディ） メソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ GET -v");
                    System.out.println(TrimString(GETV(url)));

                }else if(commandList.contains("-o")){
                    // リストに-oオプションが含まれているならGET -o（ボディ）かつファイルにて保存 メソッドを実行
                    System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ GET -o");
                    System.out.println(TrimString(GET(url)));
                    OutPutFile(TrimString(GET(url)),commandList);
                }

                else if(url == ""){
                    System.out.println("エラー：URLが正しく入力されていません");
                }

            } else if(commandList.get(0).equals("quit")){
                System.out.println("終了");
                break;
            } else{
                System.out.println("エラー：コマンドを正しく入力してください");
            }
        }
    }

    // コマンドからURLを探すメソッド
    private static String SerachHttp(List<String> commandList){
        String url = ""; // URL

        for (int i=0;i<commandList.size();i++){
            // コマンドリストの要素にhttpから始まるものがあるなら
            if(commandList.get(i).startsWith("http")){
                url = commandList.get(i); // 要素を取り出しURLに代入
                commandList.remove(i); // リストから削除
            }
        }
        return  url;
    }

    // コマンドからファイル名を探すメソッド
    private static String SerachFileName(List<String> commandList){
        String fileName = ""; // URL

        for (int i=0;i<commandList.size();i++){
            // コマンドリストの要素にhttpから始まるものがあるなら
            if(commandList.get(i).equals("-o")){
                fileName = commandList.get(i+1); // 要素を取り出しURLに代入
            }
        }
        return  fileName;
    }

    // コマンドからキーを探すメソッド
    private static String SerachParam(List<String> commandList){
        String param = ""; // URL

        for (int i=0;i<commandList.size();i++){
            // コマンドリストの要素にhttpから始まるものがあるなら
            if(commandList.get(i).equals("-d")){
                param = commandList.get(i+1); // 要素を取り出しURLに代入
            }
        }
        // ストリングビルダーに
        StringBuilder newParam = new StringBuilder(param);
        newParam.deleteCharAt(0); // 最初のの文字を削除
        newParam.deleteCharAt(newParam.length()-1); // 最後の文字を削除
        return  newParam.toString();
    }

    // コンソールからコマンドを取得しリストに格納し、それを返すメソッド
    public static List<String> CommandRead(){
        // コマンドのリスト
        List<String> commandList = new ArrayList<>();
        // コンソールからコマンドを読み込む
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.print("コマンドを入力してください:");
            String str = bufferedReader.readLine(); // 1行読み込む
            String[] array = str.split(" "); // スペースで文字列を分解し配列化
            // コマンドリストに要素を追加
            for(int i=0;i < array.length;i++){
                commandList.add(array[i]);
            }
        } catch (IOException e){
            e.printStackTrace(); // エラー箇所を示す
        }
        System.out.println(commandList);
        return commandList;
    }

    // Getメソッド
    public static String GET(String urltext){
        String urlText = urltext; // 本来はコンソールから読み込む
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
    public static String GETV(String urltext){
        String urlText = urltext; // 本来はコンソールから読み込む
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
    public static String POSTData(String urltext,String params){
        String urlText = urltext;
        String param = params;
        System.out.println(param);
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

    // POST-d-vメソッド
    public static String POSTDataV(String urltext,String params){
        String urlText = urltext;
        String param = params;
        HttpURLConnection httpURLConnection = null;
        InputStream inputResultText = null; // 結果（バイト）
        BufferedReader bufferedReader = null;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlText); // 接続するURLを指定したインスタンスを生成
            // コネクションを取得（URLが参照するリモート・オブジェクトへの接続を表すインスタンスを取得）
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true); // 出力を可能にする
            PrintHeader(httpURLConnection);
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
    public static void OutPutFile(String resultText,List<String> commandList){

        String fileName = SerachFileName(commandList); // 本来はコンソールから読み込む

        try {
            FileWriter fileWriter = new FileWriter(fileName); // fileNameの名前のファイルを作成
            fileWriter.write(resultText); // 書き込み
            fileWriter.close(); // ファイルを閉じる
        } catch (IOException e){
            System.out.println(e.getMessage()); // エラーメッセージを表示
        }
    }
}
