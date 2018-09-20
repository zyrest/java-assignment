package cying.util;

import java.io.*;

/**
 * Created by samson on 17-4-23.
 */
public class MysqlUtil {
    public static void backup(String mysqlPath, String backupFile) throws IOException {
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s " +
                "-hlocalhost -P%d %s -r \"%s\"";

        String command = String.format(commandFormat, mysqlPath, DButil.user, DButil.password,
                    DButil.port, DButil.database, backupFile);
        Runtime.getRuntime().exec(command);
    }

    public static void recover(String mysqlPath, String recoverFile) {
        try {
            String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s %s";
            String command = String.format(commandFormat, mysqlPath,
                        DButil.user, DButil.password, DButil.database);

            Process process = Runtime.getRuntime().exec(command);
            OutputStream out = process.getOutputStream();

            String inStr;
            StringBuffer buffer = new StringBuffer();
            String outStr;

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(recoverFile), "utf8"));

            while ( (inStr = reader.readLine()) != null) {
                buffer.append(inStr + "\r\n");
            }
            outStr = buffer.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            writer.flush();
            out.close();
            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
