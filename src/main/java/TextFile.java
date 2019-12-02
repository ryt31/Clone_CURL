import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class TextFile {

    // ヘッダーをプリントするメソッド
    public static void PrintHeader(HttpURLConnection httpURLConnection){
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

        try {
            InsertNewLine(trimedResultText,'>');
            InsertNewLine(trimedResultText,';');
            InsertNewLine(trimedResultText,'{');
            InsertNewLine(trimedResultText,'}');

        }catch (NullPointerException e){
            System.out.println("ファイルにするものが見当たりません");
        }

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

        String fileName = CommandRead.SerachFileName(commandList); // フィル名

        try {
            FileWriter fileWriter = new FileWriter(fileName); // fileNameの名前のファイルを作成
            fileWriter.write(resultText); // 書き込み
            fileWriter.close(); // ファイルを閉じる
        } catch (IOException e){
            System.out.println(e.getMessage()); // エラーメッセージを表示
        }
    }
}
