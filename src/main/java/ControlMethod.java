import java.util.List;

public class ControlMethod {

    // コマンドラインから読み取った文字からどのメソッドを実行するか区別する
    // quitがコマンドに打たれたらフラグを切替、返す（終了：true）
    public static boolean ControlMethod(List<String> commandList, boolean isEnd) {
        // コマンドがcurlで始まっていれば
        if (commandList.get(0).equals("curl")) {
            commandList.remove(0); // リストからcurlを削除

            String url = CommandRead.SerachHttp(commandList); // URLを探索

            if (commandList.size() == 0) {
                // リストが空の時オプションなしのGETメソッド（ボディ）を実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ GET");
                System.out.println(TextFile.TrimString(HttpMethods.GET(url)));

            } else if (commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-d") && commandList.contains("-o") && commandList.contains("-v")) {
                // リストに-X POST -d -o file -vオプションが含まれているならPOSTDataVメソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -d -o -v");
                System.out.println(TextFile.TrimString(HttpMethods.POSTDataV(url, CommandRead.SerachParam(commandList))));
                TextFile.OutPutFile(TextFile.TrimString(HttpMethods.POSTData(url, CommandRead.SerachParam(commandList))), commandList);

            } else if (commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-d") && commandList.contains("-o")) {
                // リストに-X POST -d -oオプションが含まれているならPOSTDataメソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -d -o");
                System.out.println(TextFile.TrimString(HttpMethods.POSTData(url, CommandRead.SerachParam(commandList))));
                TextFile.OutPutFile(TextFile.TrimString(HttpMethods.POSTData(url, CommandRead.SerachParam(commandList))), commandList);

            }else if (commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-d") && commandList.contains("-v")) {
                // リストに-X POST -d -oオプションが含まれているならPOSTDataメソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -d -v");
                System.out.println(TextFile.TrimString(HttpMethods.POSTDataV(url, CommandRead.SerachParam(commandList))));
                TextFile.OutPutFile(TextFile.TrimString(HttpMethods.POSTData(url, CommandRead.SerachParam(commandList))), commandList);

            } else if (commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-d")) {
                // リストに-X POST -dオプションが含まれているならPOSTDataメソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -d");
                System.out.println(TextFile.TrimString(HttpMethods.POSTData(url, CommandRead.SerachParam(commandList))));

            } else if (commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-o") && commandList.contains("-v")) {
                // リストに-X POST -o file -vオプションが含まれているならPOST -o -vメソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -o -v");
                System.out.println(TextFile.TrimString(HttpMethods.POSTV(url)));
                TextFile.OutPutFile(TextFile.TrimString(HttpMethods.POST(url)), commandList);

            } else if (commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-v")) {
                // リストに-X POST -vオプションが含まれているならPOST -vメソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -v");
                System.out.println(TextFile.TrimString(HttpMethods.POSTV(url)));

            } else if (commandList.contains("-X") && commandList.contains("POST") && commandList.contains("-o")) {
                // リストに-X POST -oオプションが含まれているならPOST -oメソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST -o");
                System.out.println(TextFile.TrimString(HttpMethods.POST(url)));
                TextFile.OutPutFile(TextFile.TrimString(HttpMethods.POST(url)), commandList);

            } else if (commandList.contains("-X") && commandList.contains("POST")) {
                // リストに-X POSTオプションが含まれているならPOST メソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ POST");
                System.out.println(TextFile.TrimString(HttpMethods.POST(url)));

            } else if (commandList.contains("-v") && commandList.contains("-o")) {
                // リストに-oオプションが含まれているならGET -o（ボディ）かつファイルにて保存 メソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ GET -o file -v");
                System.out.println(TextFile.TrimString(HttpMethods.GETV(url)));
                TextFile.OutPutFile(TextFile.TrimString(HttpMethods.GET(url)), commandList);

            } else if (commandList.contains("-v")) {
                // リストに-vオプションが含まれているならGET -v（ヘッダ+ボディ） メソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ GET -v");
                System.out.println(TextFile.TrimString(HttpMethods.GETV(url)));

            } else if (commandList.contains("-o")) {
                // リストに-oオプションが含まれているならGET -o（ボディ）かつファイルにて保存 メソッドを実行
                System.out.println("□ □ □ □ □ 結果 □ □ □ □ □ GET -o");
                System.out.println(TextFile.TrimString(HttpMethods.GET(url)));
                TextFile.OutPutFile(TextFile.TrimString(HttpMethods.GET(url)), commandList);

            } else if (url == "") {
                System.out.println("エラー：URLが正しく入力されていません");
            }

        } else if (commandList.get(0).equals("quit")) {
            System.out.println("終了");
            isEnd = true;

        } else {
            System.out.println("エラー：コマンドを正しく入力してください");
        }
        return isEnd;
    }
}
