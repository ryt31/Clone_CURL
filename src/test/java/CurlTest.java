import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurlTest {

    public static void main(String[] args) {
        System.out.println("□ □ □ □ □ 結果 □ □ □ □ □");
        System.out.println(TrimString(Get()));
        //OutputFile(TrimString(Get()));
    }

    // Getメソッド
    public static String Get(){
        String urlText = "https://example.com"; // 本来はコンソールから読み込む
        HttpURLConnection httpURLConnection = null;
        InputStream inputResultText = null; // 結果（バイト）を格納する変数
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
                inputResultText = httpURLConnection.getInputStream(); // ストリームを取得
                bufferedReader = new BufferedReader(new InputStreamReader(inputResultText)); // ストリームから文字列読み取り
                StringBuilder outputResultText = new StringBuilder(); // 結果を格納する
                String resultText; // readLine()の際に格納する

                // ストリングビルダーに最後の文字まで格納する
                while ((resultText = bufferedReader.readLine()) != null){
                    outputResultText.append(resultText);
                }
                return outputResultText.toString();
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
    // 改行したいkeyを選択し、改行メソッドを実行
    // 整形した文字列をStringで返す
    public static String TrimString(String resultText)
    {
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
